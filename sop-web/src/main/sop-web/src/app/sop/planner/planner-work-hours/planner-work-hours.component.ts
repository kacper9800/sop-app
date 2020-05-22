import { Component, OnInit, ViewChild } from '@angular/core';
import { ClrWizard } from '@clr/angular';
import { AddEditDialogComponent } from '../planner-activities/add-edit-dialog/add-edit-dialog.component';
import { Event } from '../../../_model/event.model';
import { User } from '../../../security/user';
import { PlannerService } from '../../../_services/planner.service';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../../../_services/user.service';
import { MessageService } from 'primeng';

@Component({
  selector: 'app-planner-work-hours',
  templateUrl: './planner-work-hours.component.html',
  styleUrls: ['./planner-work-hours.component.css'],
  providers: [MessageService]
})
export class PlannerWorkHoursComponent implements OnInit {
  @ViewChild('wizardlg', { static: true })
  public wizardLarge: ClrWizard;

  @ViewChild(AddEditDialogComponent, { static: true })
  public addEditDialog: AddEditDialogComponent;

  public showNewActivityDialog: boolean;
  public isWorkScheduleGeneratorVisible = false;
  public blockUI: boolean;

  public events: Event[];
  public users: User[];

  public columns: any[];
  public usersColumns: any[];
  public eventsColumns: any[];

  // ToDo Move fields to form
  public name: string;
  public description: string;
  public startDate: Date;
  public stopDate: Date;

  constructor(private plannerService: PlannerService,
              private formBuilder: FormBuilder,
              private userService: UserService) {
  }

  public ngOnInit(): void {
    this.blockUI = true;
    this.prepareColumns();
    this.loadEvents();
    this.loadUsers();
  }

  private prepareColumns(): void {
    this.columns = [
      { field: 'name', header: 'activitiesList.name' },
      { field: 'description', header: 'activitiesList.description' },
      { field: 'startDate', header: 'activitiesList.startDate' },
      { field: 'stopDate', header: 'activitiesList.stopDate' },
      { field: 'active', header: 'activitiesList.active' },
    ];
    this.usersColumns = [
      { field: 'id', header: 'users.id' },
      { field: 'name', header: 'users.name' },
      { field: 'lastName', header: 'users.lastName' },
      { field: 'college', header: 'users.college' },
      { field: 'faculty', header: 'users.faculty' },
      { field: 'institute', header: 'users.institute' }
    ];
    this.eventsColumns = [
      { field: 'id', header: 'common.id' },
      { field: 'name', header: 'common.name' },
      { field: 'startDate', header: 'common.startDate' },
      { field: 'stopDate', header: 'common.stopDate' }
    ];
  }

  private loadEvents(): void {
    this.plannerService.getAllEvents().subscribe(
      (res: Event[]) => this.onSuccessLoadEvents(res),
      () => this.onErrorLoadEvents()
    );
  }

  private onSuccessLoadEvents(res: Event[]): void {
    this.events = res;
    this.blockUI = false;
  }

  private onErrorLoadEvents(): void {
    this.blockUI = false;
  }

  private loadUsers() {
    this.userService.getAllUsers().subscribe(
      (res: User[]) => this.onSuccessLoadUsers(res),
      () => this.onErrorLoadUsers()
    );
  }

  private onSuccessLoadUsers(body: User[]) {
    this.users = body;
    this.blockUI = false;
  }

  private onErrorLoadUsers() {
    this.blockUI = false;
  }

  // ToDo Create form
  private prepareForm() {

  }

  public newWorkScheduleWizard(): void {
    this.isWorkScheduleGeneratorVisible = true;
  }

}
