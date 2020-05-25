import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../../security/user';
import { PlannerService } from '../../../../_services/planner.service';
import { MessageService } from 'primeng';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-add-edit-dialog-locations',
  templateUrl: './add-edit-dialog-locations.component.html',
  styleUrls: ['./add-edit-dialog-locations.component.css']
})
export class AddEditDialogLocationsComponent implements OnInit {
  public isDialogVisible: any;
  header: any;
  public addEditForm: FormGroup;
  public usears: User[];
  public durationItems: string[];


  constructor(private formBuilder: FormBuilder,
              private plannerService: PlannerService,
              private messageService: MessageService,
              private translateService: TranslateService) {
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
    this.durationItems = ['15 min', '30 min', '45 min', '1h', '1h 30min', '2h 15min'];
  }

  public save() {

  }
}
