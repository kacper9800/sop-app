import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Event } from '../../../../_model/event.model';
import { PlannerService } from '../../../../_services/planner.service';

@Component({
  selector: 'app-add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class AddEditDialogComponent implements OnInit {

  @Input()
  public isDialogVisible: boolean;

  public addEditForm: FormGroup;
  private eventToSave: Event;
  blockUI: Boolean;

  constructor(private formBuilder: FormBuilder,
              private plannerService: PlannerService) {
  }

  ngOnInit() {
    this.addEditForm = this.formBuilder.group({
      name: new FormControl({value: null, disabled: false}, Validators.required),
      description: new FormControl({value: null, disabled: false}, Validators.required),
      location: new FormControl({value: null, disabled: false}, Validators.required),
      startDate: new FormControl({value: null, disabled: false}, Validators.required),
      stopDate: new FormControl({value: null, disabled: false}, Validators.required),
      allDay: new FormControl({value: null, disabled: false}),
      repeat: new FormControl({value: null, disabled: false}),
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
    this.eventToSave.startDate = this.addEditForm.get('startDate').value;
    this.eventToSave.stopDate = this.addEditForm.get('stopDate').value;
    this.eventToSave.allDay = this.addEditForm.get('allDay').value;
    this.eventToSave.repeat = this.addEditForm.get('repeat').value;
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
