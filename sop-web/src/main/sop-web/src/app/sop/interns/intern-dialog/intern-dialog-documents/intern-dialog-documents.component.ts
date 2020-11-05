import { Component, OnInit } from '@angular/core';
import { Document} from '../../../../_model/document.model';


@Component({
  selector: 'app-intern-dialog-documents',
  templateUrl: './intern-dialog-documents.component.html',
  styleUrls: ['./intern-dialog-documents.component.css']
})
export class InternDialogDocumentsComponent implements OnInit {
  public documents: Document[] = [];
  public selectedDocuments: Document[] = [];
  public blockUI: boolean;
  public columns: any;
  public isSuperAdmin: boolean;
  public isAdmin: boolean;

  constructor() {
  }

  ngOnInit(): void {
    this.loadDocuments();
    this.prepareColumns();

    this.documents = [
      {name: 'Umowa o praktyki', description: 'Umowa zawierana pomiedzy uczelnią, a pracodawcą'},
      {name: 'RODO', description: 'Zgoda na przetwarzanie danych'}
    ];
  }
  private loadDocuments() {

  }

  private prepareColumns() {
    // if (this.principal.isSuperAdmin()) {
    this.columns = [
      // {label: 'common.id', fieldName: 'id'},
      {label: 'common.name', fieldName: 'name'},
      {label: 'common.description', fieldName: 'desciption'},
      {label: 'common.size', fieldName: 'size'},
      {label: 'common.createDate', fieldName: 'createDate'},
      {label: 'common.editDate', fieldName: 'editDate'},
      {label: 'common.status', fieldName: 'statusName'},
      {label: 'common.actions', fieldName: 'actions'}
    ];
    // } else {
    //   this.columns = [
    //     {label: 'common.id', fieldName: 'id'},
    // {label: 'activationKeys.tableColumns.value', fieldName: 'value'},
    // {label: 'activationKeys.tableColumns.expirationDateStart', fieldName: 'expirationDateStart'},
    // {label: 'activationKeys.tableColumns.expirationDateEnd', fieldName: 'expirationDateEnd'},
    // {label: 'activationKeys.tableColumns.remainingUses', fieldName: 'numberOfUses'},
    // {label: 'activationKeys.tableColumns.createdBy', fieldName: 'createdBy'},
    // {label: 'common.facultyName', fieldName: 'facultyName'},
    // {label: 'common.instituteName', fieldName: 'instituteName'},
    // {label: 'common.departmentName', fieldName: 'departmentName'},
    // {label: 'common.active', fieldName: 'active'},
    // {label: 'common.actions', fieldName: 'actions'}
    // ];
    // }
  }

  public showAddNewDialog() {

  }

  public showExportDialog(b: boolean) {

  }

  public downloadDocument(id: number) {

  }

  showEditDialog(id: number) {

  }

  showConfirmDeleteDialog() {

  }
}
