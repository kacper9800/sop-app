import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivationKey, IActivationKey} from '../../../_model/activation-key.model';
import {ActivationKeyService} from '../../../_services/activation-key.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {FacultyService} from '../../../_services/organization-structure/faculty.service';
import {InstituteService} from '../../../_services/organization-structure/institute.service';
import {DepartmentService} from '../../../_services/organization-structure/department.service';
import {TranslateService} from '@ngx-translate/core';
import {ClrLoadingState} from '@clr/angular';
import {Faculty} from '../../../_model/organization-structure/faculty.model';
import {Institute} from '../../../_model/organization-structure/institute.model';
import {Department} from '../../../_model/organization-structure/department.model';
import {CollegeStructure} from '../../../_model/organization-structure/college-structure.model';
import {CollegeService} from '../../../_services/organization-structure/college.service';
import {DropdownItem} from '../../../_model/dropdown-item.model';
import {TokenService} from '../../../_helpers/token.service';
import {Observable} from "rxjs";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-add-edit-dialog-activation-keys',
  templateUrl: './add-edit-dialog-activation-keys.component.html',
  styleUrls: ['./add-edit-dialog-activation-keys.component.css']
})
export class AddEditDialogActivationKeysComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public activationKey: ActivationKey;
  public blockUI: boolean;
  public displayDialog: any;
  public activationKeyForm: FormGroup;

  public collegeStructure: CollegeStructure;
  public collegeStructuresLevels: DropdownItem[];

  public faculties: Faculty[] = [];
  public institutes: Institute[] = [];
  public departments: Department[] = [];

  public dialogTitle: string;
  public validateBtnState: any;
  public selectedLevel: number;
  demo: any;

  constructor(private activationKeyService: ActivationKeyService,
              private facultyService: FacultyService,
              private instituteService: InstituteService,
              private departmentService: DepartmentService,
              private formBuilder: FormBuilder,
              private translateService: TranslateService,
              private collegeService: CollegeService,
              private tokenService: TokenService) {
  }

  ngOnInit() {
    this.prepareForm();
    this.loadCollegeStructureData();
    this.prepareDropdownsOptions();
  }

  private prepareForm(activationKey?: ActivationKey): void {
    this.activationKeyForm = this.formBuilder.group({
      level: new FormControl({value: null, disabled: false}, Validators.required),
      token: new FormControl({
        value: activationKey ? activationKey.value : this.tokenService.generateToken(),
        disabled: true
      }, Validators.required),
      expirationStartDate: new FormControl({
        value: activationKey ? activationKey.expirationDate : null,
        disabled: false
      }, Validators.required),
      expirationEndDate: new FormControl({
        value: activationKey ? activationKey.expirationDate : null,
        disabled: false
      }, Validators.required),
      numberOfUses: new FormControl({
        value: activationKey ? activationKey.remainingUses : null,
        disabled: false
      }, Validators.required),
      facultyId: new FormControl({
        value: activationKey ? activationKey.facultyId : null,
        disabled: false
      }, Validators.required),
      instituteId: new FormControl({
        value: activationKey ? activationKey.instituteId : null,
        disabled: false
      }, Validators.required),
      departmentId: new FormControl({
        value: activationKey ? activationKey.departmentId : null,
        disabled: false
      }, Validators.required)
    });
  }

  private prepareDropdownsOptions() {
    this.collegeStructuresLevels = [
      {
        value: null,
        label: this.translateService.instant('collegeStructure.dialog.chooseStructureLevel')
      },
      {value: '1', label: this.translateService.instant('common.faculty')},
      {value: '2', label: this.translateService.instant('common.institute')},
      {value: '3', label: this.translateService.instant('common.department')}
    ];
    this.faculties = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseFaculty'),
      institutes: null
    }];
    this.institutes = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseInstitute'),
      departments: null
    }];
    this.departments = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseDepartment'),
    }];

  }

  private loadCollegeStructureData() {
    this.collegeService.getCollegeStructure().subscribe(
      (res: CollegeStructure) => this.onSuccessLoadCollegeStructure(res),
      (res) => this.onErrorLoadCollegeStructure(res)
    );
  }

  private onSuccessLoadCollegeStructure(res: CollegeStructure) {
    this.collegeStructure = res;
    if (this.collegeStructure.faculties != null) {
      this.collegeStructure.faculties.forEach(faculty => this.faculties.push(faculty));
    }
  }

  private onErrorLoadCollegeStructure(res: any) {
    this.blockUI = false;
  }

  public showNewActivationKeyDialog() {
    this.blockUI = true;
    this.displayDialog = true;
    this.dialogTitle = this.translateService.instant('activationKeys.dialog.titleNew');
  }

  public showEditActivationKeyDialog(value: string) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('activationKeys.dialog.titleEdit');
    this.activationKeyService.getActivationKeyForValue(value).subscribe(
      (res: HttpResponse<IActivationKey>) => this.onSuccessLoadActivationKey(res.body),
      (res) => this.onErrorLoadActivationKey(res)
    );
  }

  private onSuccessLoadActivationKey(res: IActivationKey) {
    this.activationKey = res;
    this.displayDialog = true;
  }

  private onErrorLoadActivationKey(res: any) {

  }

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;

  }

  public onCollegeStructureLevelChange() {
    const level = this.activationKeyForm.get('level').value;
    this.selectedLevel = Number(level);
    this.activationKeyForm.get('instituteId').disable();
    this.activationKeyForm.get('departmentId').disable();
  }

  public onFacultyChange() {
    const facultyId = this.activationKeyForm.get('facultyId').value;
    this.institutes = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseInstitute'),
      departments: null
    }];
    const institutes = this.collegeStructure.faculties.find(faculty => faculty.id === Number(facultyId)).institutes;
    if (institutes.length !== 0) {
      institutes.forEach(institute => this.institutes.push(institute));
      this.activationKeyForm.get('instituteId').enable();
    } else { // jeśli nie ma zdefiniowanych instytutów
      this.institutes = [{
        id: null,
        name: this.translateService.instant('collegeStructure.dialog.instituteNotFound'),
        departments: null
      }];
      this.departments = [{
        id: null,
        name: this.translateService.instant('collegeStructure.dialog.departmentNotFound'),
      }];
      this.activationKeyForm.get('instituteId').disable();
      this.activationKeyForm.get('departmentId').disable();
    }
  }

  public onInstituteChange() {
    const instituteId = this.activationKeyForm.get('instituteId').value;
    this.departments = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseDepartment'),
    }];
    console.log(this.institutes);
    const departments = this.institutes.find(institute => institute.id === Number(instituteId)).departments;
    if (departments.length !== 0) {
      departments.forEach(department => this.departments.push(department));
      this.activationKeyForm.get('departmentId').enable();
    } else { // jeśli nie ma zdefiniowanych katedr
      this.departments = [{
        id: null,
        name: this.translateService.instant('collegeStructure.dialog.departmentNotFound'),
      }];
      this.activationKeyForm.get('departmentId').disable();
    }
  }
}
