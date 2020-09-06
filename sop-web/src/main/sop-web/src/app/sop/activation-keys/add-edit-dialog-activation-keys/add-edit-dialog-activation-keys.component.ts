import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivationKey, IActivationKey} from '../../../_model/activation-key.model';
import {ActivationKeyService} from '../../../_services/activation-key.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {DropdownItem} from '../../../_model/dropdown-item.model';
import {FacultyService} from '../../../_services/organization-structure/faculty.service';
import {InstituteService} from '../../../_services/organization-structure/institute.service';
import {DepartmentService} from '../../../_services/organization-structure/department.service';
import {TranslateService} from '@ngx-translate/core';
import {ClrLoadingState} from '@clr/angular';

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

  public faculties: DropdownItem[];
  public institutes: DropdownItem[];
  public departments: DropdownItem[];
  public dialogTitle: string;
  public validateBtnState: any;

  constructor(private activationKeyService: ActivationKeyService,
              private facultyService: FacultyService,
              private instituteService: InstituteService,
              private departmentService: DepartmentService,
              private formBuilder: FormBuilder,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.prepareForm();
  }

  private prepareForm(activationKey?: ActivationKey): void {
    this.activationKeyForm = this.formBuilder.group({
      token: new FormControl({value: activationKey ? activationKey.value : this.generateToken(),
        disabled: true
      }, Validators.required),
      facultyId: new FormControl({value: activationKey ? activationKey.facultyId : null,
        disabled: false}, Validators.required),
      instituteId: new FormControl({value: activationKey ? activationKey.instituteId : null,
        disabled: false}, Validators.required),
      departmentId: new FormControl({value: activationKey ? activationKey.departmentId : null,
        disabled: false}, Validators.required),
      expirationDate: new FormControl({value: activationKey ? activationKey.expirationDate : null,
        disabled: false}, Validators.required),
      remainingUses: new FormControl({value: activationKey ? activationKey.remainingUses : null,
        disabled: false}, Validators.required),
    });
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
      (res: IActivationKey) => this.onSuccessLoadActivationKey(res),
      (res) => this.onErrorLoadActivationKey(res)
    );
  }

  private onSuccessLoadActivationKey(res: IActivationKey) {
    this.activationKey = res;
    this.displayDialog = true;
  }

  private onErrorLoadActivationKey(res: any) {

  }

  public generateToken(): string {
    const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
    const lengthOfCode = 25;
    let text = '';
    for (let i = 0; i < lengthOfCode; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
    return text;
  }

  onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;

  }
}
