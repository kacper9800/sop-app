import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {MessageService} from 'primeng/api';
import {ILogbook, Logbook} from '../../_model/logbook.model';
import {PrincipalService} from '../../_services/auth/principal.service';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {AddEditDialogCollegesComponent} from '../colleges/add-edit-dialog-colleges/add-edit-dialog-colleges.component';
import {ConfirmDeleteDialogComponent} from '../../common/confirm-delete-dialog/confirm-delete-dialog.component';
import {ExportTableComponent} from '../export-table/export-table.component';
import {LogbookService} from '../../_services/logbook.service';

@Component({
  selector: 'app-logbook',
  templateUrl: './logbook.component.html',
  styleUrls: ['./logbook.component.css'],
  providers: [MessageService]
})

export class LogbookComponent implements OnInit {
  public logbooks: Logbook[] = [];
  public columns: any[];
  public selectedLogbook: any = [];
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
              private logbookService: LogbookService,
              private messageService: MessageService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadLogbooks();
    this.prepareColumns();
  }

  private loadLogbooks() {
    this.logbookService.getAllLogbooks().subscribe(
      (res: HttpResponse<ILogbook[]>) => this.onSuccessLoadLogbooks(res),
      (err) => this.onErrorLoadLogbooks(err));
  }


  private onSuccessLoadLogbooks(body) {
    this.logbooks = [];
    this.logbooks = body;
    this.blockUI = false;
  }

  private onErrorLoadLogbooks(err: any) {
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
        label: this.translateService.instant('common.directionName'),
        fieldName: 'directionName'
      },
      {
        label: this.translateService.instant('common.termNumber'),
        fieldName: 'term'
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
      this.loadLogbooks()
    );
  }

  public showEditDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditCollegeDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadLogbooks()
    );
  }

  public showConfirmDeleteDialog(collegeId: number): void {
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
    this.componentRef.instance.prepareCollegeData(collegeId);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadLogbooks();
    });
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.logbooks);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedLogbook);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadLogbooks()
    );
  }

  // public activateAction(college: College): void {
  //   const newActiveStatus = !college.active;
  //   this.logbookService.changeActiveStatus(college.id, newActiveStatus).subscribe(
  //     (res) => this.onSuccessChangeActiveStatus(res),
  //     (error) => this.onErrorChangeActivateStatus(error)
  //   );
  // }
  //
  // private onSuccessChangeActiveStatus(res) {
  //   // If college was active return false
  //   this.loadColleges();
  //   if (res) {
  //     this.messageService.add({
  //       severity: 'success',
  //       summary: this.translateService.instant('toast.success'),
  //       detail: this.translateService.instant('colleges.activate.successActivate')
  //     });
  //   } else {
  //     this.messageService.add({
  //       severity: 'success',
  //       summary: this.translateService.instant('toast.success'),
  //       detail: this.translateService.instant('colleges.activate.successDeactivate')
  //     });
  //   }
  // }
  //
  // private onErrorChangeActivateStatus(error) {
  //   this.messageService.add({
  //     severity: 'error',
  //     summary: this.translateService.instant('toast.error'),
  //     detail: this.translateService.instant('colleges.activate.error')
  //   });
  // }
}

