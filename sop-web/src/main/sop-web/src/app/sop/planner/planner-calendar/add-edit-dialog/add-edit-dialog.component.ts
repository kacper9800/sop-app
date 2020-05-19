import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Event } from '../../../../_model/event.model';
import { PlannerService } from '../../../../_services/planner.service';
import { User } from '../../../../security/user';

@Component({
  selector: 'app-add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css'],
})
export class AddEditDialogComponent implements OnInit {

  @Input()
  public isDialogVisible: boolean;

  public addEditForm: FormGroup;
  private eventToSave: Event;
  public blockUI = false;
  startDate: Date;
  stopDate: Date;
  locations: any;

  public users: User[];

  constructor(private formBuilder: FormBuilder,
              private plannerService: PlannerService) {
  }

  ngOnInit() {
    this.addEditForm = this.formBuilder.group({
      name: new FormControl({value: null, disabled: false}, Validators.required),
      description: new FormControl({value: null, disabled: false}, Validators.required),
      location: new FormControl({value: null, disabled: false}, Validators.required),
      startDate: new FormControl({value: new Date(), disabled: false}, Validators.required),
      stopDate: new FormControl({value: new Date(), disabled: false}, Validators.required),
      allDay: new FormControl({value: null, disabled: false}),
      repeat: new FormControl({value: null, disabled: false}),
      duration: new FormControl({value: null, disabled: false}),
      hostBy: new FormControl({value: null, disabled: false})
    });
  }

  public showDialog() {
    this.isDialogVisible = true;

  }

  public save() {
    this.blockUI = true;
    this.eventToSave = new Event();
    this.eventToSave.name = this.addEditForm.get('name').value;
    this.eventToSave.description = this.addEditForm.get('description').value;
    this.eventToSave.location = this.addEditForm.get('location').value;
    // this.eventToSave.startDate = this.addEditForm.get('startDate').value;
    // this.eventToSave.stopDate = this.addEditForm.get('stopDate').value;
    this.eventToSave.startDate = this.startDate;
    this.eventToSave.stopDate = this.stopDate;
    if (this.addEditForm.get('allDay') === null) {
      this.eventToSave.allDay = false;
    } else {
      this.eventToSave.allDay = this.addEditForm.get('allDay').value;
    }
    if (this.addEditForm.get('repeat') === null) {
      this.eventToSave.repeat = 0;
    } else {
      this.eventToSave.repeat = this.addEditForm.get('repeat').value;
    }
    if (this.addEditForm.get('duration') === null) {
      this.eventToSave.duration = null;
    } else {
      this.eventToSave.duration = this.addEditForm.get('duration').value;
    }

    console.log(this.eventToSave);
    this.plannerService.createNewEvent(this.eventToSave)
      .subscribe(
        () => this.onSuccessCreate(),
        () => this.onErrorCreate()
      );
  }

  private onSuccessCreate() {
    console.log('Dodano poprawnie!');
    this.blockUI = false;
  }

  private onErrorCreate() {
    console.log('Dodano błędnie!');
    this.blockUI = false;
  }


}
