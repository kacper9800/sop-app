import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {CollegeStructure} from '../../_model/organization-structure/college-structure.model';
import {AddEditDialogCollegeStructureComponent} from './add-edit-dialog-college-structure/add-edit-dialog-college-structure.component';
import {MessageService} from 'primeng';
import {ConfirmDeleteDialogComponent} from '../../common/confirm-delete-dialog/confirm-delete-dialog.component';
import {CollegeStructureEnum} from "../../_enums/college-structure.enum";

@Component({
  selector: 'app-college-structure',
  templateUrl: './college-structure.component.html',
  styleUrls: ['./college-structure.component.css'],
  providers: [MessageService]
})
export class CollegeStructureComponent implements OnInit {

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;

  @ViewChild('confirmDeleteDialog', {read: ViewContainerRef, static: true})
  public confirmDeleteDialog: ViewContainerRef;

  private componentRef: any;

  collegeStructure: CollegeStructure = {id: null, faculties: [], collegeName: null};
  cols: any[];
  editModal: boolean;
  private blockUI: boolean;
  CollegeStructureEnum: CollegeStructureEnum;

  constructor(private resolver: ComponentFactoryResolver,
              private collegeService: CollegeService,
              private translateService: TranslateService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.cols = [
      {field: 'name', header: 'common.name'},
      {field: 'location', header: 'common.location'},
      {field: 'actions', header: 'common.actions'}
    ];
    this.loadCollegeStructure();
  }

  private loadCollegeStructure() {
    this.blockUI = true;
    this.collegeService.getCollegeStructure().subscribe(
      (res: CollegeStructure) => this.onSuccessLoadCollegeStructure(res),
      () => this.onErrorLoadCollegeStructure()
    );
  }

  private onSuccessLoadCollegeStructure(res: CollegeStructure) {
    this.collegeStructure = res;
    this.blockUI = false;
  }

  private onErrorLoadCollegeStructure() {
    this.blockUI = false;

  }
  public showAddNewDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegeStructureComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCollegeStructureDialog(true);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe((data: any) => {
      if (data === true) {
        this.componentRef.instance.displayDialog = false;
        this.messageService.add({
          severity: 'success', summary: this.translateService.instant('toast.success'),
          detail: this.translateService.instant('toast.defaultSuccessDetailAdd')
        });
        this.loadCollegeStructure();
      } else if (data === true) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error!',
          detail: 'Error'
        });
      }
    });
  }

  public showEditDialog(id: number, collegeStructure: CollegeStructureEnum): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegeStructureComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCollegeStructureDialog(id);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadCollegeStructure();
    });
  }

  public showConfirmDeleteDialog(id: number, collegeStructureLevel: number) {
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
    this.componentRef.instance.prepareCollegeStructureData(id, CollegeStructureEnum[collegeStructureLevel]);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe( () => {
      this.loadCollegeStructure();
    });
  }

  public refreshStack(): void {
    this.loadCollegeStructure();
  }
}
