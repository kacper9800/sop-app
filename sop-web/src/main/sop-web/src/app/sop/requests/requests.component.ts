import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {PrincipalService} from '../../_services/auth/principal.service';
import {MessageService} from 'primeng/api';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {ConfirmDeleteDialogComponent} from '../../common/confirm-delete-dialog/confirm-delete-dialog.component';
import {ExportTableComponent} from '../export-table/export-table.component';
import {IRequest, Request} from '../../_model/request.model';
import {RequestsService} from '../../_services/requests.service';
import {AddEditDialogRequestsComponent} from './add-edit-dialog-requests/add-edit-dialog-requests.component';
import {AddDialogRequestsComponent} from './add-dialog-requests/add-dialog-requests.component';
import {ViewDialogRequestsComponent} from './view-dialog-requests/view-dialog-requests.component';
import {AnswerRequestDialogRequestsComponent} from './answer-request-dialog-requests/answer-request-dialog-requests.component';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css'],
  providers: [MessageService]
})
export class RequestsComponent implements OnInit {

  public requests: Request[] = [];
  public columns: any[];
  public selectedRequests: any = [];
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  @ViewChild('confirmDeleteDialog', {read: ViewContainerRef, static: true})
  public confirmDeleteDialog: ViewContainerRef;
  @ViewChild('addDialog', {read: ViewContainerRef, static: true})
  public addDialog: ViewContainerRef;
  @ViewChild('viewDialog', {read: ViewContainerRef, static: true})
  public viewDialog: ViewContainerRef;
  @ViewChild('answerRequestDialog', {read: ViewContainerRef, static: true})
  public answerRequestDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              public principal: PrincipalService,
              private requestService: RequestsService,
              private messageService: MessageService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadRequests();
    this.prepareColumns();
  }

  private loadRequests() {
    if (this.principal.isStudent()) {
      this.requestService.getAllRequestsForStudent().subscribe(
        (res: HttpResponse<IRequest[]>) => this.onSuccessLoadRequests(res),
        (err) => this.onErrorLoadRequests(err));
    } else if (this.principal.isModerator()) {
      this.requestService.getAllRequestsForInstitute().subscribe(
        (res: HttpResponse<IRequest[]>) => this.onSuccessLoadRequests(res),
        (err) => this.onErrorLoadRequests(err));
    } else if (this.principal.isDirector()) {
      this.requestService.getAllRequestsForDirector().subscribe(
        (res: HttpResponse<IRequest[]>) => this.onSuccessLoadRequests(res),
        (err) => this.onErrorLoadRequests(err));

    }

  }


  private onSuccessLoadRequests(body) {
    this.requests = [];
    this.requests = body;
    this.blockUI = false;
  }

  private onErrorLoadRequests(err: any) {
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
      }, {
        label: this.translateService.instant('common.collegeName'),
        fieldName: 'actualRequestStatusName'
      },
      {
        label: this.translateService.instant('common.actions'),
        fieldName: 'actions'
      }];
  }

  public showAddNewDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddDialogRequestsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewRequestDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadRequests()
    );
  }

  public showEditDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogRequestsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditRequestDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadRequests()
    );
  }

  public showConfirmDeleteDialog(requestId: number): void {
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
    this.componentRef.instance.prepareRequestData(requestId);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadRequests();
    });
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.requests);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedRequests);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadRequests()
    );
  }

  public showViewDialog(id: number) {
    this.viewDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ViewDialogRequestsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showRequestDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadRequests()
    );
  }

  public showAnswerRequestDialog(id: number) {
    this.viewDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AnswerRequestDialogRequestsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showAnswerRequestDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadRequests()
    );
  }

  public refreshRequests() {
    this.loadRequests();
  }
}
