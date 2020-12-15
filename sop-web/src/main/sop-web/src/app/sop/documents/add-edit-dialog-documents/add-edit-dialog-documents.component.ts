import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Direction, IDirection} from '../../../_model/direction.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {IDictionary} from '../../../_model/dictionary.model';
import {IInstitute} from '../../../_model/organization-structure/institute.model';
import {CollegeService} from '../../../_services/organization-structure/college.service';
import {TokenService} from '../../../_helpers/token.service';
import {TranslateService} from '@ngx-translate/core';
import {InstituteService} from '../../../_services/organization-structure/institute.service';
import {DictionariesService} from '../../../_services/dictionaries.service';
import {DirectionsService} from '../../../_services/directions.service';
import {HttpResponse} from '@angular/common/http';
import {ClrLoadingState} from '@clr/angular';
import {Document} from '../../../_model/document.model';
import {requiredFileType} from "../../../common/file-upload/file-upload.component";

@Component({
  selector: 'app-add-edit-dialog-documents',
  templateUrl: './add-edit-dialog-documents.component.html',
  styleUrls: ['./add-edit-dialog-documents.component.css']
})
export class AddEditDialogDocumentsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public document: Document;
  public blockUI: boolean;
  public displayDialog: any;
  public documentForm: FormGroup;
  private documentToSave: Document;
  public studyModes: IDictionary[] = [];
  public institutes: IInstitute[] = [];


  public dialogTitle: string;
  public validateBtnState: any;
  public uploadedDocuments: Document[] = [];
  public progress: any;

  constructor(private collegeService: CollegeService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private translateService: TranslateService,
              private instituteService: InstituteService,
              private dictionariesService: DictionariesService,
              private directionsService: DirectionsService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadInstitutes();
    this.loadStudyModes();
  }

  private prepareForm(document: Document): void {
    this.documentForm = this.formBuilder.group({
      name: new FormControl({value: document ? document.name : null, disabled: false}, Validators.required),
      description: new FormControl({value: document ? document.description : null, disabled: false}),
      createDate: new FormControl({value: document ? document.createDate : null, disabled: false}),
      editDate: new FormControl({value: document ? document.editDate : null, disabled: false}),
      documentData: new FormControl({value: document ? document.data : null, disabled: false},
        Validators.compose([Validators.required, requiredFileType('docx')]))
    });
  }


  private loadInstitutes() {
    this.instituteService.getAllInstitutesForCollege().subscribe(
      (res) => this.onSuccessLoadInstitutes(res),
      (error) => this.onErrorLoadInstitutes(error)
    );
  }

  private onSuccessLoadInstitutes(response) {
    this.institutes = [];
    this.institutes.push({
      id: null,
      name: this.translateService.instant('directions.chooseInstitute')
    });
    response.forEach(institute => {
      this.institutes.push({id: institute.id, name: institute.name});
    });
    this.blockUI = false;
  }

  private onErrorLoadInstitutes(error: any) {

  }

  private loadStudyModes() {
    this.dictionariesService.getStudyModes().subscribe(
      (res: any) => this.onSuccessLoadStudyModes(res),
      (error) => this.onErrorLoadStudyModes(error)
    );
  }

  private onSuccessLoadStudyModes(res: IDictionary[]) {
    this.studyModes = [];
    this.studyModes.push({label: 'chooseStudyMode', value: null});
    res.forEach(studyMode => {
      this.studyModes.push({label: studyMode.value, value: studyMode.value});
    });
    this.blockUI = false;
  }

  private onErrorLoadStudyModes(error: any) {
    this.blockUI = false;
  }

  public showNewDocumentDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('documents.dialog.titleNew');
    this.displayDialog = true;
    this.prepareForm(null);
  }

  public showEditDocumentDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleEdit');
    this.directionsService.getDirectionForId(id).subscribe(
      (res: IDirection) => this.onSuccessLoadDirection(res),
      (res) => this.onErrorLoadDirection(res)
    );
  }

  private onSuccessLoadDirection(res: IDirection) {
    this.document = res;
    this.displayDialog = true;
    this.blockUI = false;
  }

  private onErrorLoadDirection(res: any) {
    console.log(res);
    this.blockUI = false;
  }

  private collectFormData() {
    this.documentToSave = new Document();
    this.documentToSave.name = this.documentForm.get('name').value;
    this.documentToSave.description = this.documentForm.get('description').value;
    this.documentToSave.createDate = this.prepareDateObject(this.documentForm.get('createDate').value);
    this.documentToSave.editDate = this.prepareDateObject(this.documentForm.get('editDate').value);
  }

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.collectFormData();
    this.directionsService.createDirection(this.documentToSave).subscribe(
      (res: number) => this.onSuccessCreateDirection(res),
      (error) => this.onErrorCreateDirection(error)
    );
  }

  private onSuccessCreateDirection(res) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.SUCCESS;
    this.displayDialog = false;
    this.closeDialogWithSaveEmitter.emit();
  }

  private onErrorCreateDirection(error: any) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.ERROR;
    this.closeDialogWithSaveEmitter.emit();
  }

  private prepareDateObject(date: string): Date {
    if (date !== null && this.translateService.getBrowserLang() === 'pl') {
      const dateParts = date.split('.');
      const finalDate = dateParts[1] + '-' + dateParts[0] + '-' + dateParts[2];
      const convertedDate = new Date(finalDate);
      const userTimezoneOffset = convertedDate.getTimezoneOffset() * 60000;
      const dateWithoutTimezone = convertedDate.getTime() - userTimezoneOffset;
      return new Date(dateWithoutTimezone);
    } else if (date !== null && this.translateService.getBrowserLang() === 'en') {
      const dateParts = date.split('.');
      const finalDate = dateParts[1] + '-' + dateParts[0] + '-' + dateParts[2];
      const convertedDate = new Date(finalDate);
      const userTimezoneOffset = convertedDate.getTimezoneOffset() * 60000;
      const dateWithoutTimezone = convertedDate.getTime() - userTimezoneOffset;
      return new Date(dateWithoutTimezone);
    } else {
      return null;
    }
  }

  public onUpload($event: any) {


  }
}

