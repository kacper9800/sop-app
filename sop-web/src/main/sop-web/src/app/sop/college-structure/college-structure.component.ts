import {
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {TreeNode} from 'primeng';
import {TranslateService} from '@ngx-translate/core';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {CollegeStructure} from '../../_model/organization-structure/college-structure.model';
import {AddEditDialogActivationKeysComponent} from '../activation-keys/add-edit-dialog-activation-keys/add-edit-dialog-activation-keys.component';
import {AddEditDialogCollegeStructureComponent} from "./add-edit-dialog-college-structure/add-edit-dialog-college-structure.component";

@Component({
  selector: 'app-college-structure',
  templateUrl: './college-structure.component.html',
  styleUrls: ['./college-structure.component.css']
})
export class CollegeStructureComponent implements OnInit {

  @ViewChild('addEditDialog', {read: ViewContainerRef, static: true})
  public addEditDialog: ViewContainerRef;
  private componentRef: any;


  collegeStructure: CollegeStructure;

  cols: any[];
  editModal: boolean;

  constructor(private resolver: ComponentFactoryResolver,
              private collegeService: CollegeService,
              private translateService: TranslateService) {
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
    this.collegeService.getCollegeStructure().subscribe(
      (res: CollegeStructure) => this.onSuccessLoadCollegeStructure(res),
      () => this.onErrorLoadCollegeStructure()
    );
  }

  private onSuccessLoadCollegeStructure(res: CollegeStructure) {
    this.collegeStructure = res;
  }

  private onErrorLoadCollegeStructure() {

  }
  public showAddNewDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegeStructureComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCollegeStructureDialog(true);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadCollegeStructure();
    });
  }

  public showEditDialog(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogCollegeStructureComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewCollegeStructureDialog(false);
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadCollegeStructure();
    });
  }
}
