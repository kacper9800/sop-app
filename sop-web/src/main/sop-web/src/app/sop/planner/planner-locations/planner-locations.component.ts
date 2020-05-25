import { Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../../../_services/user.service';
import { LocationService } from '../../../_services/location.service';
import { HttpResponse } from '@angular/common/http';
import { MessageService } from 'primeng';
import { AddEditDialogLocationsComponent } from './add-edit-dialog-locations/add-edit-dialog-locations.component';

@Component({
  selector: 'app-planner-locations',
  templateUrl: './planner-locations.component.html',
  styleUrls: ['./planner-locations.component.css'],
  providers: [MessageService]
})
export class PlannerLocationsComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private locationService: LocationService,
              private messageService: MessageService,
              private resolver: ComponentFactoryResolver) {
  }

  public locations: Location[];
  public blockUI = false;
  public columns: any;

  @ViewChild('addEditDialog', { read: ViewContainerRef, static: true })
  public addEditDialog: ViewContainerRef;

  @ViewChild('confirmDeleteDialog', { read: ViewContainerRef, static: true })
  public confirmDeleteDialog: ViewContainerRef;

  private componentRef: any;


  ngOnInit() {
    this.blockUI = true;
    this.prepareColumns();
    this.loadLocations();
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
      (res: HttpResponse<Location[]>) => this.onSuccessLocations(res.body),
      () => this.onErrorLocation()
    );
  }

  private onSuccessLocations(body: Location[]) {
    this.locations = body;
    this.blockUI = false;

  }

  private onErrorLocation() {
    this.blockUI = false;
    // ToDo error message
  }

  public addNewLocation() {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogLocationsComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewLocationDialog();
  }

  public editLocation(id: number) {

  }

}
