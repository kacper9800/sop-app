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
import {AddEditDialogLogbooksComponent} from './add-edit-dialog-logbooks/add-edit-dialog-logbooks.component';

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
    this.logbookService.getAllLogbooksForLoggedIntern().subscribe(
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
      { label: 'logbooks.name', fieldName: 'name'},
      { label: 'common.companyName', fieldName: 'companyName'},
      { label: 'requests.wizard.position', fieldName: 'position'},
      { label: 'logbooks.actualAmountOfHours', fieldName: 'actualAmountOfHours'},
      { label: 'logbooks.amountOfHours', fieldName: 'amountOfHours'},
      { label: 'common.active', fieldName: 'active'},
      { label: 'common.actions', fieldName: 'actions'}];
  }

  public showEditLogbookDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogLogbooksComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditLogbookPostsDialog(id);
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

