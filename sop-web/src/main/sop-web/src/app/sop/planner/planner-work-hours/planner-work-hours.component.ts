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
  public selectedUsers: User[];
  public selectedLocations: Location[];

  public workScheduleForm: FormGroup;

  public isSuperAdmin: boolean;
  public isAdmin: boolean;
  public isModerator: boolean;
  public isSuperviser: boolean;
  public isStudent: boolean;


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
    this.plannerService.getAllEvents().subscribe(
      (res: Event[]) => this.onSuccessLoadEvents(res),
      () => this.onErrorLoadEvents()
    );
  }

  private onSuccessLoadEvents(res: Event[]): void {
    console.log(res);
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

  }
}
