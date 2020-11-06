import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CollegeService} from '../../../_services/organization-structure/college.service';
import {TokenService} from '../../../_helpers/token.service';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {ClrLoadingState} from '@clr/angular';
import {Direction, IDirection} from '../../../_model/direction.model';
import {DirectionsService} from '../../../_services/directions.service';
import {DictionariesService} from '../../../_services/dictionaries.service';
import {IInstitute} from '../../../_model/organization-structure/institute.model';
import {InstituteService} from '../../../_services/organization-structure/institute.service';
import {IDictionary} from '../../../_model/dictionary.model';

@Component({
  selector: 'app-add-edit-dialog-directions',
  templateUrl: './add-edit-dialog-directions.component.html',
  styleUrls: ['./add-edit-dialog-directions.component.css']
})
export class AddEditDialogDirectionsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public direction: Direction;
  public blockUI: boolean;
  public displayDialog: any;
  public directionForm: FormGroup;
  private directionToSave: Direction;
  public studyModes: IDictionary[] = [];
  public institutes: IInstitute[] = [];


  public dialogTitle: string;
  public validateBtnState: any;

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

  private prepareForm(direction: Direction): void {
    this.directionForm = this.formBuilder.group({
      name: new FormControl({
        value: direction ? direction.name : null,
        disabled: false
      }, Validators.required),
      description: new FormControl({
        value: direction ? direction.startExpirationDate : null,
        disabled: false
      }),
      expirationDateStart: new FormControl({
        value: direction ? direction.startExpirationDate : null,
        disabled: false
      }),
      expirationDateEnd: new FormControl({
        value: direction ? direction.endExpirationDate : null,
        disabled: false
      }),
      studyMode: new FormControl({
        value: direction ? direction.studyMode : null,
        disabled: false
      }, Validators.required),
      instituteId: new FormControl({
        value: direction ? direction.instituteId : null,
        disabled: false
      }, Validators.required)
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

  public showNewDirectionDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('directions.dialog.titleNew');
    this.displayDialog = true;
    this.prepareForm(null);
  }

  public showEditDirectionDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleEdit');
    this.directionsService.getDirectionForId(id).subscribe(
      (res: HttpResponse<IDirection>) => this.onSuccessLoadDirection(res.body),
      (res) => this.onErrorLoadDirection(res)
    );
  }

  private onSuccessLoadDirection(res: IDirection) {
    this.direction = res;
    this.displayDialog = true;
    this.blockUI = false;
  }

  private onErrorLoadDirection(res: any) {
    console.log(res);
    this.blockUI = false;
  }

  private collectFormData() {
    this.directionToSave = new Direction();
    this.directionToSave.name = this.directionForm.get('name').value;
    this.directionToSave.description = this.directionForm.get('description').value;
    this.directionToSave.startExpirationDate = this.prepareDateObject(this.directionForm.get('expirationDateStart').value);
    this.directionToSave.endExpirationDate = this.prepareDateObject(this.directionForm.get('expirationDateEnd').value);
    this.directionToSave.studyMode = this.directionForm.get('studyMode').value;
    this.directionToSave.instituteId = this.directionForm.get('instituteId').value;
    this.directionToSave.active = true;
  }

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.collectFormData();
    this.directionsService.createDirection(this.directionToSave).subscribe(
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
}
