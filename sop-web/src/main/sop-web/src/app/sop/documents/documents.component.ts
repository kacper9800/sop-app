import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {Direction} from '../../_model/direction.model';
import {PrincipalService} from '../../_services/auth/principal.service';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {ExportTableComponent} from '../export-table/export-table.component';
import {InternDialogComponent} from '../interns/intern-dialog/intern-dialog.component';
import {Document, IDocument} from '../../_model/document.model';
import {DocumentsService} from '../../_services/documents.service';
import {AddEditDialogDocumentsComponent} from './add-edit-dialog-documents/add-edit-dialog-documents.component';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  public selectedDocuments: Document[] = [];
  public documents: Document[] = [];
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

  constructor(private resolver: ComponentFactoryResolver,
              private principalService: PrincipalService,
              private documentsService: DocumentsService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadDocuments();
    this.prepareColumns();
    this.isAdmin = this.principalService.isAdmin();
    this.isSuperAdmin = this.principalService.isSuperAdmin();
  }

  private loadDocuments(): void {
    this.documentsService.getAllDocuments().subscribe(
      (res: HttpResponse<IDocument[]>) => this.onSuccessLoadDocuments(res.body),
      (err) => this.onErrorLoadDocuments(err)
    );
  }

  private onSuccessLoadDocuments(res: IDocument[]): void {
    this.documents = [];
    this.documents = res;
    this.blockUI = false;
  }

  private onErrorLoadDocuments(err: any): void {
    this.blockUI = false;
  }

  private prepareColumns(): void {
    this.columns = [
      {label: 'documents.name', fieldName: 'name'},
      {label: 'documents.description', fieldName: 'description'},
      {label: 'documents.size', fieldName: 'size'},
      {label: 'documents.createDate', fieldName: 'createDate'},
      {label: 'documents.editDate', fieldName: 'editDate'},
      {label: 'documents.statusName', fieldName: 'statusName'},
      {label: 'common.active', fieldName: 'active'},
      {label: 'common.deleted', fieldName: 'deleted'},
      {label: 'common.actions', fieldName: 'actions'}
    ];
  }

  editIntern() {

  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.documents);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedDocuments);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDocuments()
    );
  }

  public showAddNewDialog() {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogDocumentsComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    this.componentRef.instance.showNewDocumentDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDocuments()
    );
  }

  public showEditDialog(id: number) {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogDocumentsComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    this.componentRef.instance.showEditDocumentDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDocuments()
    );
  }

  showConfirmDeleteDialog(id: number) {

  }

  public showInternsDialog(id: number) {
    this.internDialog.clear();
    const factory = this.resolver.resolveComponentFactory(InternDialogComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    this.componentRef.instance.showInternDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadDocuments()
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
