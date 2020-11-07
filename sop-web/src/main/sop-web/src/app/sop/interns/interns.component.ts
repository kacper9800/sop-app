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
import {PrincipalService} from '../../_services/auth/principal.service';
import {InternDialogComponent} from './intern-dialog/intern-dialog.component';
import {Direction} from '../../_model/direction.model';
import {DirectionsService} from '../../_services/directions.service';

@Component({
  selector: 'app-interns',
  templateUrl: './interns.component.html',
  styleUrls: ['./interns.component.css']
})
export class InternsComponent implements OnInit {
  public selectedUsers: User[] = [];
  public users: User[] = [];
  public columns: any;
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  @ViewChild('internDialog', {read: ViewContainerRef, static: true})
  public internDialog: ViewContainerRef;
  private componentRef: any;

  public isAdmin: boolean;
  public isSuperAdmin: boolean;

  // ToDo
  public selectedDirections: Direction[] = [];
  public directions: Direction[] = [];

  constructor(private resolver: ComponentFactoryResolver,
              private usersService: UsersService,
              private directionsService: DirectionsService,
              private principalService: PrincipalService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadUsers();
    this.loadDirections();
    this.prepareColumns();
    this.isAdmin = this.principalService.isAdmin();
    this.isSuperAdmin = this.principalService.isSuperAdmin();
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
    this.blockUI = false;
  }

  private loadDirections() {
    this.directionsService.getAllDirections().subscribe(
      (res: any) => this.onSuccessLoadDirections(res),
      (error) => this.onErrorLoadDirections(error)
    );
  }

  private onSuccessLoadDirections(res) {
    this.directions = [];
    this.directions.push({id: null, name: this.translateService.instant('directions.chooseDirection')});
    res.forEach(direction => this.directions.push(direction));
    console.log(this.directions);
    this.blockUI = false;
  }

  private onErrorLoadDirections(errror: any) {
    console.log('error');
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

  public onSelectedClassesChange() {
    this.blockUI = true;
    const selectedClassId = [];
    this.selectedDirections.forEach(selectedClass => {
      selectedClassId.push(selectedClass.id);
    });
    this.usersService.loadInternsForClasses(selectedClassId).subscribe(
      (res: HttpResponse<User[]>) => this.onSuccessLoadUsers(res.body),
      (error) => this.onErrorLoadUsers(error)
    );
  }

  public showInternsDialog(id: number) {
    this.internDialog.clear();
    const factory = this.resolver.resolveComponentFactory(InternDialogComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    this.componentRef.instance.showInternDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadUsers()
    );
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
}
