import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {DropdownItem} from '../../../_model/dropdown-item.model';
import {CollegeStructure} from '../../../_model/organization-structure/college-structure.model';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from "../../../_services/organization-structure/college.service";
import {HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-add-edit-dialog-college-structure',
  templateUrl: './add-edit-dialog-college-structure.component.html',
  styleUrls: ['./add-edit-dialog-college-structure.component.css']
})
export class AddEditDialogCollegeStructureComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();

  public blockUI: boolean;
  public displayDialog: any;

  public collegeStructure: CollegeStructure;
  public collegeStructureForm: FormGroup;

  public collegeStructures: DropdownItem[];
  public faculties: DropdownItem[];
  public institutes: DropdownItem[];
  public departments: DropdownItem[];


  public dialogTitle: string;
  public validateBtnState: any;
  public addNew: boolean;

  constructor(private translateService: TranslateService,
              private formBuilder: FormBuilder,
              private collegeService: CollegeService) {
  }

  ngOnInit(): void {
    this.loadCollegeStructureData();
    this.prepareDropdownsOptions();
    this.prepareForm();
  }

  private prepareDropdownsOptions() {
    this.collegeStructures = [
      {value: null, label: this.translateService.instant('collegeStructure.dialog.chooseStructureLevel')},
      {value: '1', label: this.translateService.instant('common.faculty')},
      {value: '2', label: this.translateService.instant('common.institute')},
      {value: '3', label: this.translateService.instant('common.department')}
    ];
    this.faculties = [];

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
  }

  public showNewCollegeStructureDialog(addNew: boolean) {
    this.addNew = addNew;
    this.blockUI = true;
    this.displayDialog = true;
    this.dialogTitle = this.translateService.instant('collegeStructure.dialog.headerNew');
  }

  public onSubmit(): void {

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

  private loadCollegeStructureData() {
    this.collegeService.getCollegeStructure().subscribe(
      (res: CollegeStructure) => this.onSuccessLoadCollegeStructure(res),
      (res) => this.onErrorLoadCollegeStructure(res)
    );
  }

  private onSuccessLoadCollegeStructure(res: CollegeStructure) {

  }

  private onErrorLoadCollegeStructure(res: any) {

  }
}
