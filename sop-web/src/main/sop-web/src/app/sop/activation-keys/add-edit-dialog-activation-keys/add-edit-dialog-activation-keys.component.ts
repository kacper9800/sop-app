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
import {HttpResponse} from '@angular/common/http';
import {PrincipalService} from '../../../_services/auth/principal.service';
import {College, ICollege} from '../../../_model/organization-structure/college.model';
import {DirectionsService} from '../../../_services/directions.service';
import {Direction} from '../../../_model/direction.model';
import {RoleService} from "../../../_services/auth/role.service";

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
  public roles: any[] = [];

  public directions: Direction[];
  public colleges: College[] = [];
  public faculties: Faculty[] = [];
  public institutes: Institute[] = [];
  public departments: Department[] = [];
  public modes: any[] = [];
  public mode: number;

  public dialogTitle: string;
  public validateBtnState: any;
  public selectedLevel: number;
  private activationKeyToSave: ActivationKey;
  private editMode = false;
  private activationKeyValue: string;

  constructor(private activationKeyService: ActivationKeyService,
              private facultyService: FacultyService,
              private instituteService: InstituteService,
              private departmentService: DepartmentService,
              private formBuilder: FormBuilder,
              private translateService: TranslateService,
              private collegeService: CollegeService,
              private tokenService: TokenService,
              private principalService: PrincipalService,
              private roleService: RoleService,
              private directionsService: DirectionsService) {
  }

  ngOnInit() {
    if (this.editMode) {
      this.loadActivationKey(this.activationKeyValue);
    }
    this.prepareForm();
    this.loadCollegeStructureData();
    this.prepareDropdownsOptions();
    this.loadDirections();
    this.loadRoles();
    if (this.principalService.isSuperAdmin()) {
      this.loadColleges();
    }
    this.displayDialog = true;
  }

  private loadActivationKey(keyValue: string) {
    this.activationKeyService.getActivationKeyForValue(keyValue).subscribe(
      (res) => this.onSuccessLoadActivationKey(res),
      (res) => this.onErrorLoadActivationKey(res)
    );
  }

  private onSuccessLoadActivationKey(res) {
    this.editMode = true;
    this.prepareForm(res);
    this.displayDialog = true;
  }

  private onErrorLoadActivationKey(res: any) {
  }

  private prepareForm(activationKey?: ActivationKey): void {
    this.activationKeyForm = this.formBuilder.group({
      level: new FormControl({
        value: null,
        disabled: false
      }, Validators.required),
      token: new FormControl({
        value: activationKey ? activationKey.value : this.tokenService.generateToken(),
        disabled: true
      }, Validators.required),
      expirationDateStart: new FormControl({
        value: activationKey ? activationKey.startExpirationDate : null,
        disabled: false
      }, Validators.required),
      expirationDateEnd: new FormControl({
        value: activationKey ? activationKey.endExpirationDate : null,
        disabled: false
      }, Validators.required),
      numberOfUses: new FormControl({
        value: activationKey ? activationKey.numberOfUses : null,
        disabled: false
      }, Validators.required),
      role: new FormControl({
        value: activationKey ? activationKey.numberOfUses : null,
        disabled: false
      }, Validators.required),
      mode: new FormControl({
        value: activationKey ? activationKey.directionId ? 1 : 2 : null,
        disabled: false
      }, Validators.required),
      directionId: new FormControl({
        value: activationKey ? activationKey.directionId : null,
        disabled: false
      }),
      collegeId: new FormControl({
        value: activationKey ? activationKey.collegeId : null,
        disabled: false
      }),
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
      }, Validators.required),
    });
    if (activationKey) {
      this.setActivationKeyLevel(activationKey);
    }
  }

  private setActivationKeyLevel(activationKey: IActivationKey): void {
    if (activationKey.collegeId && activationKey.facultyId == null && activationKey.instituteId == null && activationKey.departmentId == null) {
      this.activationKeyForm.controls.level.setValue(0);
    } else if (activationKey.collegeId && activationKey.facultyId && activationKey.instituteId == null && activationKey.departmentId == null) {
      this.activationKeyForm.controls.level.setValue(1);
    } else if (activationKey.collegeId && activationKey.facultyId && activationKey.instituteId && activationKey.departmentId == null) {
      this.activationKeyForm.controls.level.setValue(2);
    } else if (activationKey.collegeId && activationKey.facultyId && activationKey.instituteId && activationKey.departmentId) {
      this.activationKeyForm.controls.level.setValue(3);
    }
  }

  private prepareDropdownsOptions() {
    if (this.principalService.isSuperAdmin()) {
      this.collegeStructuresLevels = [
        {
          value: null,
          label: this.translateService.instant('collegeStructure.dialog.chooseStructureLevel')
        },
        {value: '0', label: this.translateService.instant('common.college')},
        {value: '1', label: this.translateService.instant('common.faculty')},
        {value: '2', label: this.translateService.instant('common.institute')},
        {value: '3', label: this.translateService.instant('common.department')}
      ];
    } else if (this.principalService.isAdmin()) {
      this.collegeStructuresLevels = [
        {
          value: null,
          label: this.translateService.instant('collegeStructure.dialog.chooseStructureLevel')
        },
        {value: '1', label: this.translateService.instant('common.faculty')},
        {value: '2', label: this.translateService.instant('common.institute')},
        {value: '3', label: this.translateService.instant('common.department')}
      ];
    }

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

    this.modes = [
      {value: null, name: 'chooseMode'},
      {value: 1, name: 'direction'},
      {value: 2, name: 'collegeStructure'}
    ];

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

  private loadColleges() {
    this.collegeService.getAllAvailableColleges().subscribe(
      (res: HttpResponse<ICollege[]>) => this.onSuccessLoadColleges(res),
      (err) => this.onErrorLoadColleges(err)
    );
  }

  private onSuccessLoadColleges(res) {
    this.colleges = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseCollege'),
      active: false,
      removed: false
    }];
    if (res.length !== 0) {
      res.forEach(college => this.colleges.push(college));
      this.activationKeyForm.get('collegeId').enable();
    } else {
      this.departments = [{
        id: null,
        name: this.translateService.instant('collegeStructure.dialog.collegeNotFound'),
      }];
      this.activationKeyForm.get('collegeId').disable();
    }
    this.blockUI = false;
  }

  private onErrorLoadColleges(err: any) {
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
    this.editMode = true;
    this.activationKeyValue = value;
  }

  public onModeChange() {
    console.log(this.activationKeyForm);
    this.mode = this.activationKeyForm.get('mode').value;
    if (this.mode == 1) {
      this.activationKeyForm.get('directionId').enable();
      this.activationKeyForm.get('directionId').setValidators([Validators.required]);
      this.activationKeyForm.get('level').disable();
      this.activationKeyForm.get('level').setValidators([]);
      this.activationKeyForm.get('collegeId').disable();
      this.activationKeyForm.get('collegeId').setValidators([]);
      this.activationKeyForm.get('facultyId').disable();
      this.activationKeyForm.get('facultyId').setValidators([]);
      this.activationKeyForm.get('instituteId').disable();
      this.activationKeyForm.get('instituteId').setValidators([]);
      this.activationKeyForm.get('departmentId').disable();
      this.activationKeyForm.get('departmentId').setValidators([]);
    } else if (this.mode == 2) {
      this.activationKeyForm.get('directionId').disable();
      this.activationKeyForm.get('directionId').setValidators([]);
      this.activationKeyForm.get('level').setValidators([Validators.required]);
      this.activationKeyForm.get('level').enable();
      this.activationKeyForm.get('collegeId').enable();
      this.activationKeyForm.get('facultyId').enable();
      this.activationKeyForm.get('instituteId').enable();
      this.activationKeyForm.get('departmentId').enable();
    }
  }

  public onCollegeStructureLevelChange() {
    const level = this.activationKeyForm.get('level').value;
    this.selectedLevel = Number(level);
    if (this.selectedLevel === 0) { // College
      this.activationKeyForm.get('facultyId').disable();
      this.activationKeyForm.get('instituteId').disable();
      this.activationKeyForm.get('departmentId').disable();
      this.activationKeyForm.get('collegeId').setValidators([Validators.required]);
      this.activationKeyForm.get('numberOfUses').setValue(1);
      this.activationKeyForm.get('numberOfUses').disable();
    } else if (this.selectedLevel === 1) { // Faculty
      this.activationKeyForm.get('collegeId').disable();
      this.activationKeyForm.get('facultyId').enable();
      this.activationKeyForm.get('facultyId').setValidators([Validators.required]);
      this.activationKeyForm.get('instituteId').setValidators([]);
      this.activationKeyForm.get('departmentId').setValidators([]);
      this.activationKeyForm.get('numberOfUses').enable();
      this.activationKeyForm.get('numberOfUses').setValue(null);
      this.activationKeyForm.get('numberOfUses').setValidators([Validators.required]);
    } else if (this.selectedLevel === 2) { // Institute
      this.activationKeyForm.get('collegeId').disable();
      this.activationKeyForm.get('facultyId').enable();
      this.activationKeyForm.get('facultyId').setValidators([Validators.required]);
      this.activationKeyForm.get('instituteId').enable();
      this.activationKeyForm.get('instituteId').setValidators([Validators.required]);
      this.activationKeyForm.get('departmentId').setValidators([]);
      this.activationKeyForm.get('numberOfUses').enable();
      this.activationKeyForm.get('numberOfUses').setValue(null);
      this.activationKeyForm.get('numberOfUses').setValidators([Validators.required]);
    } else if (this.selectedLevel === 3) { // Department
      this.activationKeyForm.get('collegeId').disable();
      this.activationKeyForm.get('facultyId').enable();
      this.activationKeyForm.get('facultyId').setValidators([Validators.required]);
      this.activationKeyForm.get('instituteId').enable();
      this.activationKeyForm.get('instituteId').setValidators([Validators.required]);
      this.activationKeyForm.get('departmentId').enable();
      this.activationKeyForm.get('departmentId').setValidators([Validators.required]);
      this.activationKeyForm.get('numberOfUses').enable();
      this.activationKeyForm.get('numberOfUses').setValue(null);
      this.activationKeyForm.get('numberOfUses').setValidators([Validators.required]);
    }
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

  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.prepareActivationKey();
    if (this.activationKeyForm.get('mode').value == 1) {
      this.activationKeyService.createActivationKey(this.activationKeyToSave).subscribe(
        (res: HttpResponse<boolean>) => this.onSuccessCreateActivationKey(res),
        (err) => this.onErrorCreateActivationKey(err));
    } else if (this.activationKeyForm.get('mode').value == 2) {
      if (this.selectedLevel === 0) {
        this.activationKeyService.createActivationKeyForCollege(this.activationKeyToSave).subscribe(
          (res: HttpResponse<boolean>) => this.onSuccessCreateActivationKey(res),
          (err) => this.onErrorCreateActivationKey(err));
      } else {
        this.activationKeyService.createActivationKey(this.activationKeyToSave).subscribe(
          (res: HttpResponse<boolean>) => this.onSuccessCreateActivationKey(res),
          (err) => this.onErrorCreateActivationKey(err));
      }
    }
  }

  private prepareActivationKey(): void {
    this.activationKeyToSave = new ActivationKey();
    this.activationKeyToSave.value = this.activationKeyForm.get('token').value;
    this.activationKeyToSave.numberOfUses = this.activationKeyForm.get('numberOfUses').value;
    this.activationKeyToSave.directionId = this.activationKeyForm.get('directionId').value;
    this.activationKeyToSave.startExpirationDate = this.prepareDateObject(this.activationKeyForm.get('expirationDateStart').value);
    this.activationKeyToSave.endExpirationDate = this.prepareDateObject(this.activationKeyForm.get('expirationDateEnd').value);
    this.activationKeyToSave.role = this.activationKeyForm.get('role').value;
    if (this.selectedLevel === 0) {
      this.activationKeyToSave.collegeId = this.activationKeyForm.get('collegeId').value;
    } else if (this.selectedLevel === 1) {
      this.activationKeyToSave.facultyId = this.activationKeyForm.get('facultyId').value;
    } else if (this.selectedLevel === 2) {
      this.activationKeyToSave.instituteId = this.activationKeyForm.get('instituteId').value;
    } else if (this.selectedLevel === 3) {
      this.activationKeyToSave.departmentId = this.activationKeyForm.get('departmentId').value;
    }
  }

  private onSuccessCreateActivationKey(res: HttpResponse<boolean>): void {
    this.displayDialog = false;
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.SUCCESS;
    this.closeDialogWithSaveEmitter.emit();
  }

  private onErrorCreateActivationKey(err: any): void {
    this.validateBtnState = ClrLoadingState.ERROR;
    this.blockUI = false;
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

  private loadDirections() {
    this.directionsService.getAllDirections().subscribe(
      (res: any) => this.onSuccessLoadDirections(res),
      (error) => this.onErrorLoadDirections(error)
    );
  }

  private onSuccessLoadDirections(res) {
    this.directions = [];
    this.directions.push({
      id: null,
      name: this.translateService.instant('directions.chooseDirection')
    });
    res.forEach(direction => this.directions.push(direction));
    this.blockUI = false;
  }

  private onErrorLoadDirections(errror: any) {
    this.blockUI = false;
  }

  public getName(direction: Direction): string {
    let name;
    if (direction.startExpirationDate && direction.endExpirationDate && direction.studyMode) {
      name = direction.name + '/' + direction.startExpirationDate + ' -> ' + direction.endExpirationDate + '/' +
        this.translateService.instant('directions.' + direction.studyMode.toString());
    } else {
      name = direction.name;
    }
    return name;
  }

  public onDirectionChange() {
  }

  private loadRoles(): void {
    this.roleService.getAllRoles().subscribe(
      (res) => this.onSuccessLoadRoles(res),
      (error) => this.onErrorLoadRoles(error)
    );
  }

  private onSuccessLoadRoles(res): void {
    this.roles = [];
    this.roles.push({name: 'chooseRole', value: null});
    res.forEach(role => this.roles.push({name: role, value: role}));
  }

  private onErrorLoadRoles(error: any): void {

  }
}
