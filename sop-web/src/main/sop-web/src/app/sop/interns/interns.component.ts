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
import {Class} from '../../_model/class.model';

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

  // ToDo
  public selectedClasses: Class[];
  public classes: Class[] = [
    {name: 'Informatyka 2017/2021'},
    {name: 'Informatyka 2018/2022'},
    {name: 'Informatyka 2019/2023'}
  ];

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
        label: 'common.id',
        fieldName: 'id'
      },
      {
        label: 'users.name',
        fieldName: 'name'
      },
      {
        label: 'users.lastName',
        fieldName: 'lastName'
      },
      {
        label: 'users.username',
        fieldName: 'username'
      },
      {
        label: 'common.active',
        fieldName: 'active'
      },
      {
        label: 'common.deleted',
        fieldName: 'deleted'
      },
      {
        label: 'common.actions',
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
