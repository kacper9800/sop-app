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
import {IInstitute, Institute} from '../../../_model/organization-structure/institute.model';
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

  public dialogTitle: string;
  public validateBtnState: any;

  constructor(private collegeService: CollegeService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private translateService: TranslateService,
              private instituteService: InstituteService,
              private dictionariesService: DictionariesService,
              private directionsSerivce: DirectionsService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.prepareForm();
    this.loadInstitutes();
    this.loadStudyModes();
  }

  private prepareForm(): void {
    this.directionForm = this.formBuilder.group({

      token: new FormControl({value: this.tokenService.generateToken(), disabled: true},
        Validators.required),
      numberOfUses: new FormControl({value: 1, disabled: true}, Validators.required),
      collegeId: new FormControl({value: null, disabled: false}, Validators.required)
    });
  }

  private loadInstitutes() {
    this.instituteService.getAllInstitutesForCollege().subscribe(
      (res: HttpResponse<IInstitute[]>) => this.onSuccessLoadInstitutes(res.body),
      (error) => this.onErrorLoadInstitutes(error)
    );
  }

  private onSuccessLoadInstitutes(body: IInstitute[]) {

  }

  private onErrorLoadInstitutes(error: any) {

  }

  private loadStudyModes() {
    this.dictionariesService.getStudyModes().subscribe(
      (res: HttpResponse<IDictionary[]>) => this.onSuccessLoadStudyModes(res.body),
      (error) => this.onErrorLoadStudyModes(error)
    );
  }

  private onSuccessLoadStudyModes(res: IDictionary[]) {

  }

  private onErrorLoadStudyModes(error: any) {

  }

  public showNewCollegeDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleNew');
    this.displayDialog = true;
  }

  public showEditCollegeDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('colleges.dialog.titleEdit');
    this.collegeService.getCollegeForId(id).subscribe(
      (res: HttpResponse<IDirection>) => this.onSuccessLoadCollege(res.body),
      (res) => this.onErrorLoadCollege(res)
    );
  }

  private onSuccessLoadCollege(res: IDirection) {
    this.direction = res;
    this.displayDialog = true;
    this.blockUI = false;
  }

  private onErrorLoadCollege(res: any) {
    console.log(res);
    this.blockUI = false;
  }

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.collectFormData();
    this.directionsSerivce.createDirection(this.directionToSave).subscribe(
      (res: number) => this.onSuccessActivateCollege(res),
      (error) => this.onErrorActivateCollege(error)
    );
  }

  private collectFormData() {
    this.directionToSave = new Direction();
    this.directionToSave.name = this.directionForm.get('name').value;
    this.directionToSave.instituteId = this.directionForm.get('token').value;
    this.directionToSave.active = true;
  }

  private onSuccessActivateCollege(res) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.SUCCESS;
    this.displayDialog = false;
  }

  private onErrorActivateCollege(error: any) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.ERROR;
    this.displayDialog = false;
  }

}
