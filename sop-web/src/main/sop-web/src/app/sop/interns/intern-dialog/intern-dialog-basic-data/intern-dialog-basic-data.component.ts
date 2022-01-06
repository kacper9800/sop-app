import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../../../_services/user.service';
import {IUserView, UserView} from '../../../../_model/user-view.model';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-intern-dialog-basic-data',
  templateUrl: './intern-dialog-basic-data.component.html',
  styleUrls: ['./intern-dialog-basic-data.component.css']
})
export class InternDialogBasicDataComponent implements OnInit {
  @Input()
  public internId: number;

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public basicDataForm: FormGroup;
  public user: UserView;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.createForm();
    this.getInternBasicData();
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

  public getInternBasicData() {
    this.userService.getInternBasicData(this.internId).subscribe(
      (res) => this.onSuccessLoadInternBasicData(res),
      (err) => this.onErrorLoadInternBasicData(err)
    );
  }

  private onSuccessLoadInternBasicData(res: HttpResponse<IUserView>) {
    this.user = new UserView();
    this.user = res.body;
  }

  private onErrorLoadInternBasicData(err: any) {

  }
}
