import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {Direction, IDirection} from '../../_model/direction.model';
import {UsersService} from '../../_services/users.service';
import {PrincipalService} from '../../_services/auth/principal.service';
import {TranslateService} from '@ngx-translate/core';
import {DirectionsService} from '../../_services/directions.service';
import {ExportTableComponent} from '../export-table/export-table.component';
import {AddEditDialogDirectionsComponent} from './add-edit-dialog-directions/add-edit-dialog-directions.component';
import {ConfirmDeleteDialogComponent} from '../../common/confirm-delete-dialog/confirm-delete-dialog.component';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-directions',
  templateUrl: './directions.component.html',
  styleUrls: ['./directions.component.css'],
  providers: [MessageService]
})
export class DirectionsComponent implements OnInit {
  public selectedDirections: Direction[] = [];
  public directions: Direction[] = [];
  public columns: any;
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  @ViewChild('confirmDeleteDialog', {read: ViewContainerRef, static: true})
  public confirmDeleteDialog: ViewContainerRef;

  private componentRef: any;

  public isAdmin: boolean;
  public isSuperAdmin: boolean;

  constructor(private resolver: ComponentFactoryResolver,
              private usersService: UsersService,
              private principalService: PrincipalService,
              private translateService: TranslateService,
              private directionsService: DirectionsService) {
  }

  ngOnInit(): void {
    this.blockUI = true;
    this.loadDirections();
    this.prepareColumns();
    this.isAdmin = this.principalService.isAdmin();
    this.isSuperAdmin = this.principalService.isSuperAdmin();
  }

  private loadDirections() {
    this.directionsService.getAllDirections().subscribe(
      (res: any) => this.onSuccessLoadDirections(res),
      (error) => this.onErrorLoadDirections(error)
    );
  }

  private onSuccessLoadDirections(res) {
    this.directions = [];
    this.directions = res;
    this.blockUI = false;
  }

  private onErrorLoadDirections(errror: any) {
    console.log('error');
    this.blockUI = false;
  }

  private prepareColumns() {
    this.columns = [
      {label: 'common.name', fieldName: 'name'},
      {label: 'common.description', fieldName: 'description'},
      {label: 'directions.studyMode', fieldName: 'studyMode'},
      {label: 'common.facultyName', fieldName: 'facultyName'},
      {label: 'common.instituteName', fieldName: 'instituteName'},
      {label: 'common.deleted', fieldName: 'removed'},
      {label: 'common.active', fieldName: 'active'},
      {label: 'common.actions', fieldName: 'actions'}
    ];
  }

  public showAddNewDialog() {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogDirectionsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewDirectionDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDirections()
    );
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.directions);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedDirections);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDirections()
    );
  }

  public showEditDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogDirectionsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditDirectionDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDirections()
    );
  }

  public showConfirmDeleteDialog(id: number) {
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
    this.componentRef.instance.prepareDirectionData(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadDirections();
    });
  }
}
