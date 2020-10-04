import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
// tslint:disable-next-line:max-line-length
import {ActivationKey, IActivationKey} from '../../_model/activation-key.model';
import {AddEditDialogActivationKeysComponent} from './add-edit-dialog-activation-keys/add-edit-dialog-activation-keys.component';
import {ActivationKeyService} from '../../_services/activation-key.service';
import {HttpResponse} from '@angular/common/http';
import {TranslateService} from '@ngx-translate/core';
import {AddEditDialogCollegesComponent} from "../colleges/add-edit-dialog-colleges/add-edit-dialog-colleges.component";
import {ExportTableComponent} from "../export-table/export-table.component";


@Component({
  selector: 'app-activation-keys',
  templateUrl: './activation-keys.component.html',
  styleUrls: ['./activation-keys.component.css']
})
export class ActivationKeysComponent implements OnInit {
  public activationKeys: ActivationKey[] = [];
  public columns: any[];
  public selectedActivationKeys = [];

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;

  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              private activationKeyService: ActivationKeyService) {
  }

  ngOnInit() {
    this.loadActivationKeys();
    this.prepareColumns();
  }

  private loadActivationKeys(): void {
    this.activationKeyService.getAllActivationKeysForCompany().subscribe(
      (res: HttpResponse<IActivationKey[]>) => this.onSuccessLoadActivationKeys(res.body),
      () => this.onErrorLoadActivationKeys());
  }

  private onSuccessLoadActivationKeys(res: IActivationKey[]): void {
    this.activationKeys = [];
    res.forEach(activationKey => {
      const key = new ActivationKey();
      key.value = activationKey.value;
      key.expirationDateStart = activationKey.expirationDateStart;
      key.expirationDateEnd = activationKey.expirationDateEnd;
      key.active = activationKey.active;
      key.remainingUses = activationKey.remainingUses;
      if (activationKey.createdById === null || activationKey.createdById === undefined) {
        key.createdById = null;
        key.createdByName = '---';
      } else {
        key.createdById = activationKey.createdById;
        key.createdByName = activationKey.createdByName;
      }
      if (activationKey.collegeId === null || activationKey.collegeId === undefined) {
        key.collegeId = null;
        key.collegeName = '---';
      } else {
        key.collegeId = activationKey.collegeId;
        key.collegeName = activationKey.collegeName;
      }
      if (activationKey.facultyId === null || activationKey.facultyId  === undefined) {
        key.facultyId = null;
        key.facultyName = '---';
      } else {
        key.facultyId = activationKey.facultyId;
        key.facultyName = activationKey.facultyName;
      }
      if (activationKey.instituteId === null || activationKey.instituteId === undefined) {
        key.instituteId = null;
        key.instituteName = '---';
      } else {
        key.instituteId = activationKey.instituteId;
        key.instituteName = activationKey.instituteName;
      }
      if (activationKey.departmentId === null || activationKey.departmentId === undefined) {
        key.departmentId = null;
        key.departmentName = '---';
      } else {
        key.departmentId = activationKey.departmentId;
        key.departmentName = activationKey.departmentName;
      }
      this.activationKeys.push(key);
    });
  }

  private onErrorLoadActivationKeys(): void {
    this.activationKeys = [];
  }

  private prepareColumns() {
    this.columns = [
      {label: 'activationKeys.tableColumns.value', fieldName: 'value'},
      {label: 'activationKeys.tableColumns.expirationDateStart', fieldName: 'expirationDateStart'},
      {label: 'activationKeys.tableColumns.expirationDateEnd', fieldName: 'expirationDateEnd'},
      {label: 'activationKeys.tableColumns.remainingUses', fieldName: 'remainingUses'},
      {label: 'activationKeys.tableColumns.createdBy', fieldName: 'createdBy'},
      {label: 'common.facultyName', fieldName: 'facultyName'},
      {label: 'common.instituteName', fieldName: 'instituteName'},
      {label: 'common.departmentName', fieldName: 'departmentName'},
      {label: 'common.active', fieldName: 'active'},
      {label: 'common.deleted', fieldName: 'deleted'},
      {label: 'common.actions', fieldName: 'actions'}
    ];
  }

  public showAddNewDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogActivationKeysComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewActivationKeyDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadActivationKeys()
    );
  }

  public showEditDialog(value: string): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogActivationKeysComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditActivationKeyDialog(value);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadActivationKeys();
    });
  }

  public showConfirmDeleteDialog() {
    // ToDo
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.activationKeys);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedActivationKeys);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadActivationKeys()
    );
  }

  public copyKeyValue(value: string) {
    // this.clipboard.copy(value);
    // this.messageService.add({
    //   severity: 'info',
    //   summary: this.translateService.instant('toast.info'),
    //   detail: this.translateService.instant('toast.infoCopiedToClipboard'),
    // });
  }
}
