import {
  Component,
  ComponentFactoryResolver, EventEmitter, Input,
  OnInit, Output,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {ILogbook, Logbook} from '../../../../_model/logbook.model';
import {PrincipalService} from '../../../../_services/auth/principal.service';
import {LogbookService} from '../../../../_services/logbook.service';
import {AddEditDialogLogbooksComponent} from '../../../logbook/add-edit-dialog-logbooks/add-edit-dialog-logbooks.component';
import {ExportTableComponent} from '../../../export-table/export-table.component';

@Component({
  selector: 'app-intern-dialog-logbook',
  templateUrl: './intern-dialog-logbook.component.html',
  styleUrls: ['./intern-dialog-logbook.component.css']
})
export class InternDialogLogbookComponent implements OnInit {
  @Input()
  public internId: number;

  public logbooks: Logbook[] = [];
  public columns: any[];
  public selectedLogbook: any = [];
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              public principal: PrincipalService,
              private logbookService: LogbookService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadLogbooks();
    this.prepareColumns();
  }

  private loadLogbooks() {
    this.logbookService.getAllLogbooksForIntern(this.internId).subscribe(
      (res) => this.onSuccessLoadLogbooks(res),
      (err) => this.onErrorLoadLogbooks(err));
  }

  private onSuccessLoadLogbooks(body) {
    console.log(body);
    this.logbooks = [];
    this.logbooks = body;
    console.log(this.logbooks);
    this.blockUI = false;
  }

  private onErrorLoadLogbooks(err: any) {
    this.blockUI = false;
    console.log(err);
  }

  private prepareColumns() {
    this.columns = [
      {label: 'logbooks.name', fieldName: 'name'},
      {label: 'common.companyName', fieldName: 'companyName'},
      {label: 'requests.wizard.position', fieldName: 'position'},
      {label: 'common.active', fieldName: 'active'},
      {label: 'common.actions', fieldName: 'actions'}];
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

  showViewLogbookDialog(id: number) {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogLogbooksComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditLogbookPostsDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadLogbooks()
    );
  }
}

