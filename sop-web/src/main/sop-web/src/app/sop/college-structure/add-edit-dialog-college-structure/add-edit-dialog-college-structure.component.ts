import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {DropdownItem} from '../../../_model/dropdown-item.model';
import {CollegeStructure} from '../../../_model/organization-structure/college-structure.model';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from '../../../_services/organization-structure/college.service';
import {Institute} from '../../../_model/organization-structure/institute.model';
import {Faculty} from '../../../_model/organization-structure/faculty.model';
import {CollegeStructureToSave} from '../../../_model/organization-structure/structure-to-save.model';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-add-edit-dialog-college-structure',
  templateUrl: './add-edit-dialog-college-structure.component.html',
  styleUrls: ['./add-edit-dialog-college-structure.component.css'],
  providers: [MessageService]
})
export class AddEditDialogCollegeStructureComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();


  public blockUI: boolean;
  public displayDialog: any;

  public collegeStructure: CollegeStructure;
  public collegeStructureForm: FormGroup;

  public collegeStructuresLevels: DropdownItem[];
  public faculties: Faculty[] = [];
  public institutes: Institute[] = [];

  public dialogTitle: string;
  public validateBtnState: any;
  public addNew: boolean;
  private structureToSave: CollegeStructureToSave;

  constructor(private translateService: TranslateService,
              private formBuilder: FormBuilder,
              private collegeService: CollegeService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.loadCollegeStructureData();
    this.prepareDropdownsOptions();
    this.prepareForm();
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

  }

  private prepareForm() {
    this.collegeStructureForm = this.formBuilder.group({
      level: new FormControl({value: null, disabled: false}, Validators.required),
      facultyId: new FormControl({value: null, disabled: false}, Validators.required),
      facultyName: new FormControl({value: null, disabled: false}, Validators.required),
      instituteId: new FormControl({value: null, disabled: false}, Validators.required),
      instituteName: new FormControl({value: null, disabled: false}, Validators.required),
      departmentName: new FormControl({value: null, disabled: false}, Validators.required)
    });
    this.blockUI = false;
  }

  public showNewCollegeStructureDialog(addNew: boolean) {
    this.addNew = addNew;
    this.blockUI = true;
    this.displayDialog = true;
    this.dialogTitle = this.translateService.instant('collegeStructure.dialog.headerNew');
  }

  showEditCollegeStrucutreDialog(id: number) {
    // ToDo strzał do bazy po obiekt, wyświetlenie go w dialogu
  }

  public isSaveButtonDisabled(): boolean {
    if (this.collegeStructureForm.get('level').value === '0') {
      return true;
    } else if (this.collegeStructureForm.get('level').value === '1') {
      if (this.collegeStructureForm.get('facultyName').value != null) {
        return false;
      }
      return true;
    } else if (this.collegeStructureForm.get('level').value === '2') {
      if (this.collegeStructureForm.get('facultyId').value != null) {
        return false;
      }

    } else if (this.collegeStructureForm.get('level').value === '3') {

    }
  }

  public onCollegeStructureLevelChange() {
    this.setDisabledControls(this.collegeStructureForm.get('level').value);
  }

  public onChangeFaculty() {
    this.blockUI = true;
    const facultyId = this.collegeStructureForm.get('facultyId').value;
    this.institutes = [{
      id: null,
      name: this.translateService.instant('collegeStructure.dialog.chooseInstitute'),
      departments: null
    }];
    this.collegeStructure.faculties.find(faculty => faculty.id === Number(facultyId)).institutes.forEach(
      institute => this.institutes.push(institute)
    );
    this.blockUI = false;
  }

  private setDisabledControls(level: string) {
    if (level === null) {
      this.collegeStructureForm.get('level').setValidators([Validators.required]);
      this.collegeStructureForm.get('facultyId').disable();
      this.collegeStructureForm.get('facultyId').setValidators([]);
      this.collegeStructureForm.get('facultyName').enable();
      this.collegeStructureForm.get('facultyName').setValidators([Validators.required]);
      this.collegeStructureForm.get('instituteId').disable();
      this.collegeStructureForm.get('instituteId').setValidators([]);
      this.collegeStructureForm.get('instituteName').disable();
      this.collegeStructureForm.get('instituteName').setValidators([]);
      this.collegeStructureForm.get('departmentName').disable();
      this.collegeStructureForm.get('departmentName').setValidators([]);
    } else if (level === '1') {
      this.collegeStructureForm.get('level').setValidators([]);
      this.collegeStructureForm.get('facultyId').disable();
      this.collegeStructureForm.get('facultyId').setValidators([]);
      this.collegeStructureForm.get('facultyName').enable();
      this.collegeStructureForm.get('facultyName').setValidators([Validators.required]);
      this.collegeStructureForm.get('instituteId').disable();
      this.collegeStructureForm.get('instituteId').setValidators([]);
      this.collegeStructureForm.get('instituteName').disable();
      this.collegeStructureForm.get('instituteName').setValidators([]);
      this.collegeStructureForm.get('departmentName').disable();
      this.collegeStructureForm.get('departmentName').setValidators([]);
    } else if (level === '2') {
      this.collegeStructureForm.get('level').setValidators([]);
      this.collegeStructureForm.get('facultyId').enable();
      this.collegeStructureForm.get('facultyId').setValidators([Validators.required]);
      this.collegeStructureForm.get('facultyName').disable();
      this.collegeStructureForm.get('facultyName').setValidators([]);
      this.collegeStructureForm.get('instituteId').disable();
      this.collegeStructureForm.get('instituteId').setValidators([]);
      this.collegeStructureForm.get('instituteName').enable();
      this.collegeStructureForm.get('instituteName').setValidators([Validators.required]);
      this.collegeStructureForm.get('departmentName').disable();
      this.collegeStructureForm.get('departmentName').setValidators([]);
    } else if (level === '3') {
      this.collegeStructureForm.get('level').setValidators([]);
      this.collegeStructureForm.get('facultyId').enable();
      this.collegeStructureForm.get('facultyId').setValidators([Validators.required]);
      this.collegeStructureForm.get('facultyName').disable();
      this.collegeStructureForm.get('facultyName').setValidators([]);
      this.collegeStructureForm.get('instituteId').enable();
      this.collegeStructureForm.get('instituteId').setValidators([Validators.required]);
      this.collegeStructureForm.get('instituteName').disable();
      this.collegeStructureForm.get('instituteName').setValidators([]);
      this.collegeStructureForm.get('departmentName').enable();
      this.collegeStructureForm.get('departmentName').setValidators([Validators.required]);
    }
  }


  public onSubmit(): void {
    this.blockUI = true;
    const chosenLevel = this.collegeStructureForm.get('level').value;
    this.structureToSave = new CollegeStructureToSave();
    this.structureToSave.level = chosenLevel;
    console.log(this.collegeStructure);
    this.structureToSave.collegeId = this.collegeStructure.id;
    if (chosenLevel === '0') {
      this.messageService.add({detail: '', severity: '', summary: ''});
    } else if (chosenLevel === '1') { // If adding new faculty
      this.structureToSave.parentId = this.collegeStructure.id;
      this.structureToSave.structureName = this.collegeStructureForm.get('facultyName').value;
    } else if (chosenLevel === '2') { // If adding new institute
      this.structureToSave.parentId = this.collegeStructureForm.get('facultyId').value;
      this.structureToSave.structureName = this.collegeStructureForm.get('instituteName').value;
    } else if (chosenLevel === '3') { // If adding new department
      this.structureToSave.parentId = this.collegeStructureForm.get('instituteId').value;
      this.structureToSave.structureName = this.collegeStructureForm.get('departmentName').value;
    }
    this.collegeService.createNewStructure(this.structureToSave).subscribe(
      (res: number) => this.onSuccessCreateStructure(res),
      (res) => this.onErrorCreateStructure(res)
    );
  }

  private onSuccessCreateStructure(res: number) {
    this.closeDialogWithSaveEmitter.emit(true);
    this.blockUI = false;
  }

  private onErrorCreateStructure(res) {
    this.closeDialogWithSaveEmitter.emit(false);
    this.blockUI = false;
  }
}
