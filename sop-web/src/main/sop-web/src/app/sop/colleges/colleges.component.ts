import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {College, ICollege} from '../../_model/organization-structure/college.model';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {HttpResponse} from '@angular/common/http';
import {AddEditDialogCollegesComponent} from './add-edit-dialog-colleges/add-edit-dialog-colleges.component';
import {ExportTableComponent} from '../export-table/export-table.component';
import {PrincipalService} from '../../_services/auth/principal.service';
import {MessageService} from 'primeng/api';
import {ConfirmDeleteDialogComponent} from '../../common/confirm-delete-dialog/confirm-delete-dialog.component';

@Component({
  selector: 'app-colleges',
  templateUrl: './colleges.component.html',
  styleUrls: ['./colleges.component.css'],
  providers: [MessageService]
})
export class CollegesComponent implements OnInit {
  public colleges: College[] = [];
  public columns: any[];
  public selectedColleges: any = [];
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  @ViewChild('confirmDeleteDialog', {read: ViewContainerRef, static: true})
  public confirmDeleteDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              private principal: PrincipalService,
              private collegeService: CollegeService,
              private messageService: MessageService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadColleges();
    this.prepareColumns();
  }

  private loadColleges() {
    this.collegeService.getAllColleges().subscribe(
      (res: HttpResponse<ICollege[]>) => this.onSuccessLoadColleges(res),
      (err) => this.onErrorLoadColleges(err));
  }


  private onSuccessLoadColleges(body) {
    this.colleges = [];
    this.colleges = body;
    this.blockUI = false;
  }

  private onErrorLoadColleges(err: any) {
    this.blockUI = false;
  }

  private prepareColumns() {
    this.columns = [
      // {
      //   label: this.translateService.instant('common.id'),
      //   fieldName: 'id'
      // },
      {
        label: this.translateService.instant('common.collegeName'),
        fieldName: 'name'
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

  public showAddNewDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCollegeDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadColleges()
    );
  }

  public showEditDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditCollegeDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadColleges()
    );
  }

  public showConfirmDeleteDialog(collegeId: number): void {
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
    this.componentRef.instance.prepareCollegeData(collegeId);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe( () => {
      this.loadColleges();
    });
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.colleges);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedColleges);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadColleges()
    );
  }

  public activateAction(college: College): void {
    const newActiveStatus = !college.active;
    this.collegeService.changeActiveStatus(college.id, newActiveStatus).subscribe(
      (res) => this.onSuccessChangeActiveStatus(res),
      (error) => this.onErrorChangeActivateStatus(error)
    );
  }

  private onSuccessChangeActiveStatus(res) {
    // If college was active return false
    this.loadColleges();
    if (res) {
      this.messageService.add({
        severity: 'success',
        summary: this.translateService.instant('toast.success'),
        detail: this.translateService.instant('colleges.activate.successActivate')
      });
    } else {
      this.messageService.add({
        severity: 'success',
        summary: this.translateService.instant('toast.success'),
        detail: this.translateService.instant('colleges.activate.successDeactivate')
      });
    }
  }

  private onErrorChangeActivateStatus(error) {
    this.messageService.add({
      severity: 'error',
      summary: this.translateService.instant('toast.error'),
      detail: this.translateService.instant('colleges.activate.error')
    });
  }
}
