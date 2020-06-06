import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../../security/user';
import { PlannerService } from '../../../../_services/planner.service';
import { MessageService } from 'primeng';
import { TranslateService } from '@ngx-translate/core';
import { Location } from '../../../../_model/location.model';
import { LocationService } from '../../../../_services/location.service';

@Component({
  selector: 'app-add-edit-dialog-locations',
  templateUrl: './add-edit-dialog-locations.component.html',
  styleUrls: ['./add-edit-dialog-locations.component.css']
})
export class AddEditDialogLocationsComponent implements OnInit {

  @Input()
  public isDialogVisible: boolean;

  @Output()
  closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();

  public addEditForm: FormGroup;
  private locationToSave: Location;
  public users: User[];
  public durationItems: string[];
  public blockUI = false;


  header: any;


  constructor(private formBuilder: FormBuilder,
              private plannerService: PlannerService,
              private messageService: MessageService,
              private translateService: TranslateService,
              private locationService: LocationService) {
  }

  ngOnInit() {
    this.prepareForm();
  }

  public showNewLocationDialog() {
    this.isDialogVisible = true;
    this.header = this.translateService.instant('add-edit-dialog.headerNewLocation');
  }

  public prepareForm() {
    this.addEditForm = this.formBuilder.group({
      name: new FormControl({ value: null, disabled: false }, Validators.required),
      description: new FormControl({ value: null, disabled: false }, Validators.required),
      floor: new FormControl({ value: null, disabled: false }, Validators.required),
      address: new FormControl({ value: null, disabled: false }, Validators.required),
      room: new FormControl({ value: null, disabled: false }, Validators.required),
      capacity: new FormControl({ value: null, disabled: false }, Validators.required),
    });
  }

  public save() {
    this.blockUI = true;
    this.locationToSave = new Location();
    this.locationToSave.name = this.addEditForm.get('name').value;
    this.locationToSave.description = this.addEditForm.get('description').value;
    this.locationToSave.floor = this.addEditForm.get('floor').value;
    this.locationToSave.address = this.addEditForm.get('address').value;
    this.locationToSave.room = this.addEditForm.get('room').value;
    this.locationToSave.capacity = this.addEditForm.get('capacity').value;

    this.locationService.createNewLocation(this.locationToSave)
      .subscribe(
        () => this.onSuccessCreate(),
        () => this.onErrorCreate()
      );
  }

  private onSuccessCreate() {
    this.blockUI = false;
    this.isDialogVisible = false;
    this.messageService.add({
      key: 'toast1', severity: 'success', summary: this.translateService.instant('toast.success'),
      detail: this.translateService.instant('toast.defaultSuccessDetailAdd')
    });
  }

  private onErrorCreate() {
    this.blockUI = false;
    this.messageService.add({
      key: 'toast1', severity: 'error', summary: this.translateService.instant('toast.error'),
      detail: this.translateService.instant('toast.defaultErrorDetailAdd')
    });
  }

  public onClose(): void {
    this.closeDialogWithSaveEmitter.emit();
    window.location.reload();
  }
}
