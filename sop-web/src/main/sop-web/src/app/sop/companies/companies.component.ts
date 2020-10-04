import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {Company, ICompany} from '../../_model/company.model';
import {CompanyService} from '../../_services/company.service';
import {AddEditDialogCompaniesComponent} from './add-edit-dialog-companies/add-edit-dialog-companies.component';
import {ExportTableComponent} from '../export-table/export-table.component';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {
  public companies: Company[] = [];
  public columns: any[];
  public selectedCompanies: any = [];
  public blockUI: boolean;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  @ViewChild('exportDialog', {read: ViewContainerRef, static: true})
  public exportDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              private companyService: CompanyService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.loadCompanies();
    this.prepareColumns();
  }

  private loadCompanies() {
    this.companyService.getAllCompanies().subscribe(
      (res: HttpResponse<ICompany[]>) => this.onSuccessLoadCompanies(res.body),
      (err) => this.onErrorLoadCompanies(err));
  }


  private onSuccessLoadCompanies(body: ICompany[]) {
    this.companies = [];
    this.companies = body;
    this.blockUI = false;
  }

  private onErrorLoadCompanies(err: any) {
    this.blockUI = false;
    console.log(err);
  }

  private prepareColumns() {
    this.columns = [
      {
        label: this.translateService.instant('common.id'),
        fieldName: 'id'
      },
      {
        label: this.translateService.instant('common.companyName'),
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
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCompaniesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCompanyDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadCompanies()
    );
  }

  public showEditDialog(id: number): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCompaniesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditCompanyDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadCompanies()
    );
  }

  public showConfirmDeleteDialog(): void {
    // ToDo
  }

  public showExportDialog(exportAll: boolean): void {
    this.exportDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ExportTableComponent);
    this.componentRef = this.exportDialog.createComponent(factory);
    if (exportAll) {
      this.componentRef.instance.showExportTableDialog(this.companies);
    } else {
      this.componentRef.instance.showExportTableDialog(this.selectedCompanies);
    }
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() =>
      this.loadCompanies()
    );
  }
}
