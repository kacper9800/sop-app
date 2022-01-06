import {
  Component,
  ComponentFactoryResolver,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LogbookService} from '../../../_services/logbook.service';
import {HttpResponse} from '@angular/common/http';
import {ILogbookPost, LogbookPost} from '../../../_model/logbook-post.model';
import {ExportTableComponent} from '../../export-table/export-table.component';
import {TranslateService} from '@ngx-translate/core';
import {PrincipalService} from '../../../_services/auth/principal.service';

@Component({
  selector: 'app-add-edit-dialog-logbooks',
  templateUrl: './add-edit-dialog-logbooks.component.html',
  styleUrls: ['./add-edit-dialog-logbooks.component.css']
})
export class AddEditDialogLogbooksComponent implements OnInit {


  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayDialog: any;
  public dialogTitle: any;
  public blockUI: boolean;
  public isEditingNow: boolean;

  private logbookId: number;
  private finalLogbookPost: LogbookPost;
  public logbookPostToSave: LogbookPost = new LogbookPost();
  public logbookPosts: LogbookPost[] = [];
  public selectedLogbookPosts: LogbookPost[] = [];
  public numberOfHours = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16];

  public logbookPostForm: FormGroup;

  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;

  private componentRef: any;
  public columns: any;

  constructor(private resolver: ComponentFactoryResolver,
              private logbookService: LogbookService,
              private formBuilder: FormBuilder,
              public principalService: PrincipalService,
              private translateService: TranslateService) {

  }

  ngOnInit(): void {
    this.prepareColumns();
  }

  public prepareColumns() {
    this.columns = [
      {label: this.translateService.instant('logbookPosts.description'), fieldName: 'description'},
      {label: this.translateService.instant('logbookPosts.date'), fieldName: 'date'},
      {
        label: this.translateService.instant('logbookPosts.amountOfHours'),
        fieldName: 'amountOfHours'
      }];
  }

  private loadLogbookPosts(logbookId: number) {
    this.logbookService.getAllLogbookPostsForLogbookId(logbookId).subscribe(
      (res) => this.onSuccessLoadLogbookPosts(res),
      (err) => this.onErrorLoadLogbookPosts(err)
    );
  }

  public showEditLogbookPostsDialog(id: number) {
    this.displayDialog = true;
    this.dialogTitle = 'Edycja dziennika praktyk';
    this.logbookId = id;
    this.loadLogbookPosts(id);

  }

  private onSuccessLoadLogbookPosts(res: ILogbookPost[]) {
    this.logbookPosts = [];
    this.selectedLogbookPosts = [];
    console.log(res);
    res.forEach(response => {
      response.isEditable = false;
      this.logbookPosts.push(response);
    });
    this.blockUI = false;

  }

  private onErrorLoadLogbookPosts(err: any) {
    this.logbookPosts = [];
    this.selectedLogbookPosts = [];
    this.blockUI = false;
  }

  public onSubmit() {
    this.blockUI = true;
    this.finalLogbookPost = new LogbookPost();
    this.finalLogbookPost.description = this.logbookPostToSave.description;
    this.finalLogbookPost.amountOfHours = this.logbookPostToSave.amountOfHours;
    this.finalLogbookPost.date = this.logbookPostToSave.date;
    this.finalLogbookPost.date = this.prepareDateObject(this.finalLogbookPost.date.toString());
    this.finalLogbookPost.logbookId = this.logbookId;
    this.logbookService.createNewLogbookPost(this.finalLogbookPost).subscribe(
      (res) => this.onSuccessCreateLogbookPost(res),
      (err) => this.onErrorCreateLogbookPost(err)
    );
  }

  private onSuccessCreateLogbookPost(res: HttpResponse<ILogbookPost>) {
    this.closeDialogWithSaveEmitter.emit();
    this.isEditingNow = false;
    this.blockUI = false;
    this.loadLogbookPosts(this.logbookId);
  }

  private onErrorCreateLogbookPost(err: any) {
    this.blockUI = false;
  }

  public addNewRow() {
    this.isEditingNow = true;
    const logbookPost = new LogbookPost();
    logbookPost.isEditable = true;
    logbookPost.date = new Date();
    logbookPost.description = '';
    logbookPost.amountOfHours = 0;
    console.log(logbookPost);
    this.logbookPosts.push(logbookPost);
    console.log(this.logbookPosts);
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.logbookPosts);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedLogbookPosts);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadLogbookPosts(this.logbookId)
    );
  }

  public isDataValid(): boolean {
    console.log(this.logbookPostToSave);
    if (this.logbookPostToSave.date != null &&
      this.logbookPostToSave.amountOfHours != null &&
      this.logbookPostToSave.description != null) {
      return true;
    } else {
      return false;
    }
  }


  private prepareDateObject(date: string): Date {
    if (date !== null && this.translateService.getBrowserLang() === 'pl') {
      const dateParts = date.split('.');
      const finalDate = dateParts[1] + '-' + dateParts[0] + '-' + dateParts[2];
      const convertedDate = new Date(finalDate);
      const userTimezoneOffset = convertedDate.getTimezoneOffset() * 60000;
      const dateWithoutTimezone = convertedDate.getTime() - userTimezoneOffset;
      return new Date(dateWithoutTimezone);
    } else if (date !== null && this.translateService.getBrowserLang() === 'en') {
      const dateParts = date.split('.');
      const finalDate = dateParts[1] + '-' + dateParts[0] + '-' + dateParts[2];
      const convertedDate = new Date(finalDate);
      const userTimezoneOffset = convertedDate.getTimezoneOffset() * 60000;
      const dateWithoutTimezone = convertedDate.getTime() - userTimezoneOffset;
      return new Date(dateWithoutTimezone);
    } else {
      return null;
    }
  }

}
