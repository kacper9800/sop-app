import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-edit-dialog',
  templateUrl: './add-edit-dialog.component.html',
  styleUrls: ['./add-edit-dialog.component.css']
})
export class AddEditDialogComponent implements OnInit {

  @Input()
  public isDialogVisible: boolean;

  public addEditForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.addEditForm = this.formBuilder.group({
      name: new FormControl({value: null, disabled: false}, Validators.required),
      description: new FormControl({value: null, disabled: false}, Validators.required),
      location: new FormControl({value: null, disabled: false}, Validators.required),
      startDate: new FormControl({value: null, disabled: false}, Validators.required),
      stopDate: new FormControl({value: null, disabled: false}, Validators.required),
    });
  }

  public showDialog() {
    this.isDialogVisible = true;

  }

  save() {

  }
}
