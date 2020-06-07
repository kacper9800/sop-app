import { Component, OnInit, ViewChild } from '@angular/core';
import { ClrWizard } from '@clr/angular';
import { AddEditDialogActivitiesComponent } from '../planner-activities/add-edit-dialog-activities/add-edit-dialog-activities.component';
import { Event } from '../../../_model/event.model';
import { User } from '../../../security/user';
import { PlannerService } from '../../../_services/planner.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../_services/user.service';
import { MessageService } from 'primeng';
import { Location } from '../../../_model/location.model';
import { TokenStorageService } from '../../../_services/auth/token-storage.service';
import { PrincipalService } from '../../../_services/auth/principal.service';
import { LocationService } from '../../../_services/location.service';
import { WorkSchedule } from '../../../_model/work-schedule.model';
import { BreaksDuration } from '../../../enums/breaks-duration.enum';

@Component({
  selector: 'app-planner-work-hours',
  templateUrl: './planner-work-hours.component.html',
  styleUrls: ['./planner-work-hours.component.css'],
  providers: [MessageService]
})
export class PlannerWorkHoursComponent implements OnInit {
  @ViewChild('wizardlg', { static: true })
  public wizardLarge: ClrWizard;

  @ViewChild(AddEditDialogActivitiesComponent, { static: true })
  public addEditDialog: AddEditDialogActivitiesComponent;

  public showNewActivityDialog: boolean;
  public isWorkScheduleGeneratorVisible = false;
  public blockUI: boolean;

  public events: Event[];
  public users: User[];
  public locations: Location[];


  public columns: any[];
  public usersColumns: any[];
  public eventsColumns: any[];
  public locationsColumns: any[];

  // ToDo Move fields to form
  public name: string;
  public description: string;
  public startDate: Date;
  public stopDate: Date;

  public selectedEvents: Event[];
  private selectedEventsIds: number[];

  public selectedUsers: User[];
  private selectedUsersIds: number[];

  public selectedLocations: Location[];
  private selectedLocationsIds: number[];

  public workScheduleForm: FormGroup;
  private workScheduleToSave: WorkSchedule;

  public isSuperAdmin: boolean;
  public isAdmin: boolean;
  public isModerator: boolean;
  public isSuperviser: boolean;
  public isStudent: boolean;
  public breaksItems: any;


  constructor(private plannerService: PlannerService,
              private formBuilder: FormBuilder,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private principalService: PrincipalService,
              private locationService: LocationService) {
  }

  public ngOnInit(): void {
    this.blockUI = true;
    this.setAuthorities();
    this.prepareForm();
    this.prepareColumns();
    this.loadEvents();
    this.loadUsers();
    this.loadLocations();
  }

  private setAuthorities() {
    if (this.tokenStorageService.getToken()) {
      this.isSuperAdmin = this.principalService.isSuperAdmin();
      this.isAdmin = this.principalService.isAdmin();
      this.isModerator = this.principalService.isModerator();
      this.isSuperviser = this.principalService.isSuperviser();
      this.isStudent = this.principalService.isStudent();
    }
  }

  private prepareForm() {
    this.workScheduleForm = this.formBuilder.group({
      name: new FormControl({ value: null, disabled: false }, Validators.required),
      description: new FormControl({ value: null, disabled: false }, Validators.required),
      startDate: new FormControl({ value: new Date(), disabled: false }, Validators.required),
      stopDate: new FormControl({ value: new Date(), disabled: false }, Validators.required),
      breaks: new FormControl({ value: null, disabled: false }),

      startHour: new FormControl({ value: null, disabled: false }),
      stopHour: new FormControl({ value: null, disabled: false }),

      instructor: new FormControl({ value: null, disabled: false }),

      location: new FormControl({ value: null, disabled: false }, Validators.required),

      allDay: new FormControl({ value: null, disabled: false }),
      repeat: new FormControl({ value: null, disabled: false }),
    });

    this.breaksItems = [
      { label: '15 min', value: BreaksDuration.FIFTENMINUTES },
      { label: '30 min', value: BreaksDuration.HALFHOUR },
      { label: '45 min', value: BreaksDuration.THREEFOURTHHOUR },
      { label: '1h min', value: BreaksDuration.HOUR }
    ];
  }

  private prepareColumns(): void {
    this.columns = [
      { field: 'name', header: 'common.name' },
      { field: 'description', header: 'common.description' },
      { field: 'startDate', header: 'workSchedules.startDate' },
      { field: 'stopDate', header: 'workSchedules.stopDate' },
      { field: 'active', header: 'common.active' },
      { field: 'actions', header: 'common.actions' }
    ];
    this.usersColumns = [
      { field: 'id', header: 'users.id' },
      { field: 'firstName', header: 'users.name' },
      { field: 'lastName', header: 'users.lastName' },
      { field: 'collegeName', header: 'users.college' },
      // { field: 'facultyName', header: 'users.faculty' },
      { field: 'instituteName', header: 'users.institute' }
    ];
    this.eventsColumns = [
      { field: 'id', header: 'common.id' },
      { field: 'name', header: 'common.name' },
      { field: 'durationConverted', header: 'common.duration' },
      { field: 'instructorName', header: 'common.hostBy' }
    ];
    this.locationsColumns = [
      { field: 'address', header: 'locations.address' },
      { field: 'floor', header: 'locations.floor' },
      { field: 'room', header: 'locations.floor' },
      { field: 'capacity', header: 'locations.capacity' }
    ];
  }

  private loadEvents(): void {
    this.plannerService.getAllBaseEvents().subscribe(
      (res: Event[]) => this.onSuccessLoadEvents(res),
      () => this.onErrorLoadEvents()
    );
  }

  private onSuccessLoadEvents(res: Event[]): void {
    this.events = [];
    if (res.length !== 0) {
      res.forEach((event) => {
        if (event.duration === 0) {
          event.durationConverted = '15 min';
        } else if (event.duration === 1) {
          event.durationConverted = '30 min';
        } else if (event.duration === 2) {
          event.durationConverted = '45 min';
        } else if (event.duration === 3) {
          event.durationConverted = '1h';
        } else if (event.duration === 4) {
          event.durationConverted = '1h 30min';
        } else {
          event.durationConverted = '2h 15min';
        }
        this.events.push(event);
      });
    }
    this.blockUI = false;
  }

  private onErrorLoadEvents(): void {
    this.blockUI = false;
  }

  private loadUsers(): void {
    this.userService.getAllUsers().subscribe(
      (res: User[]) => this.onSuccessLoadUsers(res),
      () => this.onErrorLoadUsers()
    );
  }

  private onSuccessLoadUsers(body: User[]): void {
    this.users = body;
    this.blockUI = false;
  }

  private onErrorLoadUsers(): void {
    this.blockUI = false;
  }

  private loadLocations(): void {
    this.locationService.getAllLocations().subscribe(
      (res: Location[]) => this.onSuccessLoadLocations(res),
      () => this.onErrorLoadLocations()
    );
  }

  private onSuccessLoadLocations(body: Location[]) {
    this.locations = body;
    this.blockUI = false;
  }

  private onErrorLoadLocations(): void {
    this.blockUI = false;
  }

  public newWorkScheduleWizard(): void {
    this.isWorkScheduleGeneratorVisible = true;
  }

  public saveNewWorkSchedule(): void {
    this.collectWorkScheduleDataFromForm();
    this.plannerService.createWorkSchedule(this.workScheduleToSave).subscribe(
      () => this.onSuccessSaveWorkSchedule(),
      () => this.onErrorSaveWorkSchedule()
    );
  }

  private collectWorkScheduleDataFromForm() {
    if (this.workScheduleForm !== null) {
      this.workScheduleToSave = new WorkSchedule();
      this.workScheduleToSave.name = this.workScheduleForm.get('name').value;
      this.workScheduleToSave.description = this.workScheduleForm.get('description').value;
      this.workScheduleToSave.startDate = this.workScheduleForm.get('startDate').value;
      this.workScheduleToSave.stopDate = this.workScheduleForm.get('stopDate').value;
      this.workScheduleToSave.breaks = this.workScheduleForm.get('breaks').value;
      this.workScheduleToSave.startHour = this.workScheduleForm.get('startHour').value;
      this.workScheduleToSave.stopHour = this.workScheduleForm.get('stopHour').value;

      this.selectedEventsIds = [];
      this.selectedUsersIds = [];
      this.selectedLocationsIds = [];

      if (this.selectedEvents.length !== 0) {
        this.selectedEvents.forEach(event => {
          console.log(event);
          this.selectedEventsIds.push(event.id);
        });
      } else {
        return;
      }
      this.workScheduleToSave.eventsId = this.selectedEventsIds

      if (this.selectedUsers.length !== 0) {
        this.selectedUsers.forEach(user => {
          console.log(user);
          this.selectedUsersIds.push(user.id);
        });
      } else {
        return;
      }
      this.workScheduleToSave.usersId = this.selectedUsersIds;

      if (this.selectedLocations.length !== 0) {
        this.selectedLocations.forEach(location => {
          console.log(location);
          this.selectedLocationsIds.push(location.id);
        });
      } else {
        return;
      }

      this.workScheduleToSave.locationsId = this.selectedLocationsIds;
    }
    console.log(this.workScheduleToSave);
  }

  private onSuccessSaveWorkSchedule() {

  }

  private onErrorSaveWorkSchedule() {

  }
}
