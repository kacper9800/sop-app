import { Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../../../_services/user.service';
import { LocationService } from '../../../_services/location.service';
import { HttpResponse } from '@angular/common/http';
import { MessageService } from 'primeng';
import { AddEditDialogLocationsComponent } from './add-edit-dialog-locations/add-edit-dialog-locations.component';
import { Location } from '../../../_model/location.model';
import { TokenStorageService } from '../../../_services/auth/token-storage.service';
import { PrincipalService } from '../../../_services/auth/principal.service';
import { ConfirmDeleteDialogComponent } from '../../../common/confirm-delete-dialog/confirm-delete-dialog.component';

@Component({
  selector: 'app-planner-locations',
  templateUrl: './planner-locations.component.html',
  styleUrls: ['./planner-locations.component.css'],
  providers: [MessageService]
})
export class PlannerLocationsComponent implements OnInit {
  private isConfirmDeleteDialogVisible: boolean;
  private idOfLocationToDelete: any;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private locationService: LocationService,
              private messageService: MessageService,
              private resolver: ComponentFactoryResolver,
              private tokenStorageService: TokenStorageService,
              private principalService: PrincipalService) {
  }

  public locations: Location[];
  public blockUI = false;
  public columns: any;

  @ViewChild('addEditDialog', { read: ViewContainerRef, static: true })
  public addEditDialog: ViewContainerRef;

  @ViewChild('confirmDeleteDialog', { read: ViewContainerRef, static: true })
  public confirmDeleteDialog: ViewContainerRef;

  private componentRef: any;
  selectedUsers: any;
  isModerator: any;


  ngOnInit() {
    this.blockUI = true;
    this.setAuthorities();
    this.prepareColumns();
    this.loadLocations();
  }

  private setAuthorities() {
    if (this.tokenStorageService.getToken()) {
      this.isModerator = this.principalService.isModerator();
    }
  }

  private prepareColumns() {
    this.columns = [
      { field: 'address', header: 'locations.address' },
      { field: 'floor', header: 'locations.description' },
      { field: 'room', header: 'locations.room' },
      { field: 'capacity', header: 'locations.capacity' },
    ];
  }


  private loadLocations() {
    this.locationService.getAllLocations().subscribe(
      (res: HttpResponse<Location[]>) => this.onSuccessLocations(res),
      () => this.onErrorLocation()
    );
  }

  private onSuccessLocations(body: any): void {
    console.log(body);
    this.locations = body;
    console.log(this.locations);
    this.blockUI = false;

  }

  private onErrorLocation(): void {
    this.blockUI = false;
    // ToDo error message
  }

  public addNewLocation(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogLocationsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewLocationDialog();
    this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(() => {
      this.loadLocations();
    });
  }

  public editLocation(rowData: any): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogLocationsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditLocationDialog(rowData);
  }

  public confirmDelete(id: any): void {
    this.isConfirmDeleteDialogVisible = true;
    this.idOfLocationToDelete = id;
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
  }
}
