import { Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { PlannerService } from '../../../_services/planner.service';
import { AddEditDialogActivitiesComponent } from './add-edit-dialog-activities/add-edit-dialog-activities.component';
import { ClrWizard } from '@clr/angular';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../../../_services/user.service';
import { User } from '../../../security/user';
import { Event } from '../../../_model/event.model';
import { MessageService } from 'primeng';
import { ConfirmDeleteDialogComponent } from '../../../common/confirm-delete-dialog/confirm-delete-dialog.component';

@Component({
  selector: 'app-planner-activities',
  templateUrl: './planner-activities.component.html',
  styleUrls: ['./planner-activities.component.css'],
  providers: [MessageService]

})
export class PlannerActivitiesComponent implements OnInit {
  @ViewChild('wizardlg', { static: true })
  public wizardLarge: ClrWizard;

  @ViewChild('addEditDialog', { read: ViewContainerRef, static: true })
  public addEditDialog: ViewContainerRef;

  @ViewChild('confirmDeleteDialog', { read: ViewContainerRef, static: true })
  public confirmDeleteDialog: ViewContainerRef;

  private componentRef: any;

  // @ViewChild(AddEditDialogActivitiesComponent, { static: true })
  // public addEditDialog: AddEditDialogActivitiesComponent;

  public showNewActivityDialog: boolean;
  public isWorkScheduleGeneratorVisible = false;
  public blockUI: boolean;

  public events: Event[];
  public idOfEventToDelete = null;
  public users: User[];

  public columns: any[];
  public usersColumns: any[];
  public eventsColumns: any[];

  // ToDo Move fields to form
  public name: string;
  public description: string;
  public startDate: Date;
  public stopDate: Date;
  public isConfirmDeleteDialogVisible = false;

  constructor(private plannerService: PlannerService,
              private formBuilder: FormBuilder,
              private userService: UserService,
              private messageService: MessageService,
              private resolver: ComponentFactoryResolver) {
  }

  public ngOnInit(): void {
    this.blockUI = true;
    this.prepareColumns();
    this.loadEvents();
    this.loadUsers();
  }

  // ToDo Create form
  private prepareForm() {

  }

  private prepareColumns(): void {
    this.columns = [
      { field: 'name', header: 'common.name' },
      { field: 'description', header: 'common.description' },
      { field: 'duration', header: 'common.duration' },
      { field: 'instructorName', header: 'common.instructor' },
      { field: 'active', header: 'common.active' },
      { field: 'actions', header: 'common.actions' }
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


  public addNewEvent(): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogActivitiesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showNewEventDialog();
  }

  public editEvent(rowData: any): void {
    this.addEditDialog.clear();
    const factory = this.resolver.resolveComponentFactory(AddEditDialogActivitiesComponent);
    this.componentRef = this.addEditDialog.createComponent(factory);
    this.componentRef.instance.showEditEventDialog(rowData);

    // this.componentRef.instance.closeDialogWithSaveEmitter.subscribe(data => {
    // });
  }

  public newWorkScheduleWizard(): void {
    this.isWorkScheduleGeneratorVisible = true;
  }

  public confirmDelete(id: any) {
    this.isConfirmDeleteDialogVisible = true;
    this.idOfEventToDelete = id;
    this.confirmDeleteDialog.clear();
    const factory = this.resolver.resolveComponentFactory(ConfirmDeleteDialogComponent);
    this.componentRef = this.confirmDeleteDialog.createComponent(factory);
  }

  // public deleteEvent(id: number): void {

    // if (id != null) {
    //   this.plannerService.deleteEvent(id).subscribe(
    //     () => this.onSuccessDelete(),
    //     () => this.onErrorDelete()
    //   );
    // }
  // }

  private onSuccessDelete() {
    this.isConfirmDeleteDialogVisible = false;

  }

  private onErrorDelete() {
    this.isConfirmDeleteDialogVisible = false;

  }


}

//   // Don't use FullcalendarOption interface
//   this.options = {
//     editable: true,
//     customButtons: {
//       myCustomButton: {
//         text: 'custom!',
//         click: function () {
//           alert('clicked the custom button!');
//         }


//     },
//     theme: 'standart', // default view, may be bootstrap
//     header: {
//       left: 'prev,next today myCustomButton',
//       center: 'title',
//       right: 'dayGridMonth,timeGridWeek,timeGridDay'
//     },
//     columnHeaderHtml: () => {
//       return '<b>Friday!</b>';
//     },
//     locales: [esLocale, frLocale],
//     locale: 'fr',
//     // add other plugins
//     plugins: [dayGridPlugin, interactionPlugin, timeGridPlugin, resourceTimeGridPlugin]
//   };
//   new Draggable(this.external.nativeElement, {
//     itemSelector: '.fc-event',
//     eventData: function (eventEl) {
//       return {
//         title: eventEl.innerText
//       };
//     }
//   });
// }
//
// eventClick(model) {
//   console.log(model);
// }
//
// eventDragStop(model) {
//   console.log(model);
// }
//
// dateClick(model) {
//   console.log(model);
// }
//
// updateHeader() {
//   this.options.header = {
//     left: 'prev,next myCustomButton',
//     center: 'title',
//     right: ''
//   };
// }
//
// updateEvents() {
//   this.eventsModel = [{
//     title: 'Updaten Event',
//     start: this.yearMonth + '-08',
//     end: this.yearMonth + '-10'
//   }];
// }
//
// get yearMonth(): string {
//   const dateObj = new Date();
//   return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
// }
//
// dayRender(ev) {
//   ev.el.addEventListener('dblclick', () => {
//     alert('double click!');
//   });
// }
