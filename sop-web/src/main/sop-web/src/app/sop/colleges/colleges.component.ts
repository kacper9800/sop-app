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

@Component({
  selector: 'app-colleges',
  templateUrl: './colleges.component.html',
  styleUrls: ['./colleges.component.css']
})
export class CollegesComponent implements OnInit {
  public colleges: College[] = [];
  public columns: any[];
  public selectedColleges: any;

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  private componentRef: any;

  constructor(private resolver: ComponentFactoryResolver,
              private collegeService: CollegeService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.loadColleges();
    this.prepareColumns();
  }

  private loadColleges() {
    this.collegeService.getAllAvailableColleges().subscribe(
      (res: HttpResponse<ICollege[]>) => this.onSuccessLoadColleges(res.body),
      (err) => this.onErrorLoadColleges(err));
  }


  private onSuccessLoadColleges(body: ICollege[]) {
    this.colleges = [];
    this.colleges = body;
  }

  private onErrorLoadColleges(err: any) {
    console.log(err);
  }

  private prepareColumns() {
    this.columns = [
      {
        label: this.translateService.instant('common.id'),
        fieldName: 'id'
      },
      {
        label: this.translateService.instant('common.collegeName'),
        fieldName: 'name'
      },
      {
        label: this.translateService.instant('common.actions'),
        fieldName: 'actions'
      },
      {
        label: this.translateService.instant('common.active'),
        fieldName: 'active'
      },
      {
        label: this.translateService.instant('common.removed'),
        fieldName: 'removed'
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

  public showConfirmDeleteDialog(): void {
    // ToDo
  }

  public onExportAll(): void {

  }

  public onExportSelected(): void {

  }
}
