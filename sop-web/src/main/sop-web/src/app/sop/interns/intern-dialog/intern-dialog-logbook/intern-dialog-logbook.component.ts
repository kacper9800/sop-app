import {Component, OnInit} from '@angular/core';
import {LogbookPost} from '../../../../_model/logbook-post.model';

@Component({
  selector: 'app-intern-dialog-logbook',
  templateUrl: './intern-dialog-logbook.component.html',
  styleUrls: ['./intern-dialog-logbook.component.css']
})
export class InternDialogLogbookComponent implements OnInit {
  public blockUI: boolean;
  public columns: any;
  public logbookPosts: LogbookPost[] = [];
  public selectedLogbookPosts: LogbookPost[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.prepareColumns();
    this.loadLogbookPosts();
  }

  public showExportDialog(b: boolean) {

  }

  private prepareColumns() {
    // if (this.principal.isSuperAdmin()) {
    this.columns = [
      {label: 'common.id', fieldName: 'id'},
      {label: 'interns.tasks', fieldName: 'task'},
      {label: 'common.date', fieldName: 'date'}
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

  private loadLogbookPosts() {
    this.selectedLogbookPosts = [
      {id: 1, name: 'Budowa szkieletu HTML w aplikacji', tasks: 'Budowa szkieletu HTML w aplikacji', date: new Date()},
      {id: 2, name: '', tasks: 'Przygotowanie struktury bazy danych', date: new Date()},
      {id: 3, name: '', tasks: 'Utworzenie encji', date: new Date()}
      ];
  }
}

