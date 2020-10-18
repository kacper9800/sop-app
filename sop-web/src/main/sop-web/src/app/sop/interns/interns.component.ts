import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {UsersService} from '../../_services/users.service';
import {IUser, User} from '../../security/user';
import {HttpResponse} from '@angular/common/http';
import {ExportTableComponent} from '../export-table/export-table.component';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-interns',
  templateUrl: './interns.component.html',
  styleUrls: ['./interns.component.css']
})
export class InternsComponent implements OnInit {
  public selectedUsers: any = [];
  public columns: any;
  public users: User[] = [];
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              private usersService: UsersService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadUsers();
    this.prepareColumns();
  }

  private loadUsers() {
    this.usersService.getAllUsers().subscribe(
      (res: HttpResponse<IUser[]>) => this.onSuccessLoadUsers(res.body),
      (err) => this.onErrorLoadUsers(err)
    );
  }

  private onSuccessLoadUsers(res: IUser[]) {
    this.users = [];
    this.users = res;
    this.blockUI = false;
  }

  private onErrorLoadUsers(err: any) {
    console.log(err);
    this.blockUI = false;
  }

  private prepareColumns() {
    this.columns = [
      {
        label: this.translateService.instant('common.id'),
        fieldName: 'id'
      },
      {
        label: this.translateService.instant('users.name'),
        fieldName: 'name'
      },
      {
        label: this.translateService.instant('users.lastName'),
        fieldName: 'lastName'
      },
      {
        label: this.translateService.instant('users.username'),
        fieldName: 'username'
      },
      {
        label: this.translateService.instant('common.active'),
        fieldName: 'active'
      },
      {
        label: this.translateService.instant('common.deleted'),
        fieldName: 'deleted'
      },
      {
        label: this.translateService.instant('common.actions'),
        fieldName: 'actions'
      }];
  }

  editIntern() {

  }

  showAddNewDialog() {

  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.users);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedUsers);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadUsers()
    );
  }

  copyKeyValue(value: any) {

  }

  showEditDialog(value: any) {

  }

  showConfirmDeleteDialog() {

  }


}
