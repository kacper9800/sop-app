import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Event } from '../../../../_model/event.model';
import { PlannerService } from '../../../../_services/planner.service';
import { User } from '../../../../security/user';
import { MessageService } from 'primeng';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from '../../../../_services/user.service';
import { Location } from '../../../../_model/location.model';
import { LocationService } from '../../../../_services/location.service';

@Component({
  selector: 'app-add-edit-dialog-activities',
  templateUrl: './add-edit-dialog-activities.component.html',
  styleUrls: ['./add-edit-dialog-activities.component.css'],
  providers: [MessageService]

})
export class AddEditDialogActivitiesComponent implements OnInit {

  @Input()
  public isDialogVisible: boolean;

  @Output()
  closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();

  public addEditForm: FormGroup;
  private eventToSave: Event;
  public blockUI = false;
  startDate: Date;
  stopDate: Date;

  public users: User[];
  public locations: Location[];
  public header: string;
  public durationItems: any[];


  constructor(private formBuilder: FormBuilder,
              private plannerService: PlannerService,
              private messageService: MessageService,
              private translateService: TranslateService,
              private userService: UserService,
              private locationService: LocationService) {
  }

  ngOnInit() {
    this.prepareForm();
    this.loadUsers();
    this.loadLocations();
  }

  public showNewEventDialog() {
    this.isDialogVisible = true;
    this.header = this.translateService.instant('add-edit-dialog.headerNewEvent');
  }

  public showEditEventDialog(rowData: Event) {
    this.isDialogVisible = true;
    this.header = this.translateService.instant('add-edit-dialog.headerEditEvent ') + rowData.name;
  }

  public prepareForm() {
    this.users = [];
    this.locations = [];
    this.addEditForm = this.formBuilder.group({
      name: new FormControl({ value: null, disabled: false }, Validators.required),
      description: new FormControl({ value: null, disabled: false }, Validators.required),
      duration: new FormControl({ value: null, disabled: false }),
      instructor: new FormControl({ value: null, disabled: false }),

      location: new FormControl({ value: null, disabled: false }, Validators.required),
      startDate: new FormControl({ value: new Date(), disabled: false }, Validators.required),
      stopDate: new FormControl({ value: new Date(), disabled: false }, Validators.required),

      allDay: new FormControl({ value: null, disabled: false }),
      repeat: new FormControl({ value: null, disabled: false }),
    });

    this.durationItems = [
      { label: '15 min', value: 0 },
      { label: '30 min', value: 1 },
      { label: '45 min', value: 2 },
      { label: '1h min', value: 3 },
      { label: '1h 30 min', value: 4 },
      { label: '2h 15 min', value: 5 }
    ];
  }

  private loadUsers(): void {
    this.userService.getAllUsers()
      .subscribe(
        (res) => this.onSuccessLoadUsers(res),
        (res) => this.onErrorLoadUsers()
      );
  }

  private onSuccessLoadUsers(res: any) {
    this.users = res;
  }

  private onErrorLoadUsers() {
    this.messageService.add({
      key: 'toast1', severity: 'error', summary: this.translateService.instant('toast.error'),
      detail: this.translateService.instant('toast.defaultErrorDetailLoad')
    });
  }

  private loadLocations(): void {
    this.locationService.getAllLocations()
      .subscribe(
        (res) => this.onSuccessLoadLocations(res),
        () => this.onErrorLoadLocations()
      );
  }

  private onSuccessLoadLocations(res: any) {
    this.locations = res;
  }

  private onErrorLoadLocations() {
    this.messageService.add({
      key: 'toast1', severity: 'error', summary: this.translateService.instant('toast.error'),
      detail: this.translateService.instant('toast.defaultErrorDetailLoad')
    });
  }


  public save() {
    this.blockUI = true;
    this.eventToSave = new Event();
    this.eventToSave.name = this.addEditForm.get('name').value;
    this.eventToSave.description = this.addEditForm.get('description').value;
    this.eventToSave.location = this.addEditForm.get('location').value;
    // this.eventToSave.startDate = this.addEditForm.get('startDate').value;
    // this.eventToSave.stopDate = this.addEditForm.get('stopDate').value;
    // this.eventToSave.startDate = this.startDate;
    // this.eventToSave.stopDate = this.stopDate;
    // if (this.addEditForm.get('allDay') === null) {
    //   this.eventToSave.allDay = false;
    // } else {
    //   this.eventToSave.allDay = this.addEditForm.get('allDay').value;
    // }
    // if (this.addEditForm.get('repeat') === null) {
    //   this.eventToSave.repeat = 0;
    // } else {
    //   this.eventToSave.repeat = this.addEditForm.get('repeat').value;
    // }
    if (this.addEditForm.get('duration') === null) {
      this.eventToSave.duration = null;
    } else {
      this.eventToSave.duration = this.addEditForm.get('duration').value;
    }

    if (this.addEditForm.get('instructor') === null) {
      this.eventToSave.instructorId = null;
    } else {
      this.eventToSave.instructorId = this.addEditForm.get('instructor').value;
    }

    this.plannerService.createNewEvent(this.eventToSave)
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
