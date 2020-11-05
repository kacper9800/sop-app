import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-intern-dialog-basic-data',
  templateUrl: './intern-dialog-basic-data.component.html',
  styleUrls: ['./intern-dialog-basic-data.component.css']
})
export class InternDialogBasicDataComponent implements OnInit {
  public basicDataForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.createForm();
  }

  public createForm() {
    this.basicDataForm = this.formBuilder.group({
      firstName: new FormControl({value: null, disabled: false}, Validators.required),
      lastName: new FormControl({value: null, disabled: false}, Validators.required),
      username: new FormControl({value: null, disabled: false}, Validators.required),
      academicDegree: new FormControl({value: null, disabled: false}, Validators.required),
      phone: new FormControl({value: null, disabled: false}, Validators.required),
      email: new FormControl({
        value: null,
        disabled: false
      }, Validators.compose([Validators.required, Validators.email])),
    });
  }

}
