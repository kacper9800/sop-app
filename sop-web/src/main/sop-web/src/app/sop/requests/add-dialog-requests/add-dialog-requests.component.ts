import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {IRequest, Request} from '../../../_model/request.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Dictionary, IDictionary} from '../../../_model/dictionary.model';
import {IUserView} from '../../../_model/user-view.model';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from '@angular/common/http';
import {DictionariesService} from '../../../_services/dictionaries.service';
import {ClrWizard} from '@clr/angular';
import {RequestsService} from '../../../_services/requests.service';
import {MessageService} from 'primeng/api';
import {PrincipalService} from '../../../_services/auth/principal.service';

@Component({
  selector: 'app-add-dialog-requests',
  templateUrl: './add-dialog-requests.component.html',
  styleUrls: ['./add-dialog-requests.component.css'],
  providers: [MessageService]
})
export class AddDialogRequestsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();

  @ViewChild('wizard')
  public wizard: ClrWizard;
  public displayDialog: any;
  public blockUI: boolean;

  public requestToSave: Request = new Request();
  public isStartInternship: boolean;

  public firstPageForm: FormGroup;
  public secondPageForm: FormGroup;
  public thirdPageForm: FormGroup;
  public fourthPageForm: FormGroup;
  public fifthPageForm: FormGroup;

  public dialogTitle: string;
  public validateBtnState: any;

  public requestTypes: Dictionary[] = [];
  public moderators: IUserView[];
  public admins: IUserView[];

  public firstPageFormValid: boolean;
  public secondPageFormValid: boolean;
  public thirdPageFormValid: boolean;

  constructor(private translateService: TranslateService,
              private dictionaryService: DictionariesService,
              private requestsService: RequestsService,
              private principalService: PrincipalService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.loadRequestTypes();
  }

  public showNewRequestDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleNew');
    this.displayDialog = true;
    this.prepareForm(null);
  }

  private prepareForm(request: IRequest): void {
    this.firstPageForm = this.formBuilder.group({
      requestType: new FormControl({value: null}, [Validators.required, Validators.nullValidator])
    });

    this.secondPageForm = this.formBuilder.group({
      position: new FormControl(''),
      positionDescription: new FormControl(''),
      amountOfHours: new FormControl(''),
      responsibilities: new FormControl('')
    });

    this.thirdPageForm = this.formBuilder.group({
      companyName: new FormControl(''),
      companyNip: new FormControl(''),
      departmentName: new FormControl(''),
      practiceSuperviserName: new FormControl(''),
      practiceSuperviserLastName: new FormControl(''),
      practiceSuperviserPhone: new FormControl(''),
      practiceSuperviserEmail: new FormControl('')
    });

    this.fourthPageForm = this.formBuilder.group({
      infoAgreement: new FormControl(''),
      processingAgreement: new FormControl('')
    });
  }

  private loadRequestTypes() {
    this.dictionaryService.getRequestTypes().subscribe(
      (res: HttpResponse<IDictionary[]>) => this.onSuccessLoadRequestTypes(res),
      (error => this.onErrorLoadRequestTypes(error))
    );
  }

  private onSuccessLoadRequestTypes(body) {
    this.requestTypes = [];
    this.requestTypes.push({name: 'chooseRequestType', value: null});
    body.forEach(requestType => this.requestTypes.push({
      name: requestType.value,
      value: requestType.value
    }));
    this.blockUI = false;
  }

  private onErrorLoadRequestTypes(error: any) {
    this.blockUI = false;
  }


  reset($event: boolean) {

  }

  createRanking() {

  }

  get requestType() {
    return this.firstPageForm.get('requestType').value;
  }

  public onChangeSelect($event: any) {
    console.log($event);
    if ($event !== 'null') {
      if ($event === 'START_INTERNSHIP') {
        this.isStartInternship = true;
      }
      this.firstPageFormValid = true;
    } else {
      this.firstPageFormValid = false;
    }
  }

  public onSuccessCreateRequest(res: any) {
    // ToDo message
  }

  public onErrorCreateRequest() {
    // ToDo message
  }

  public prepareData() {
    this.requestToSave = new Request();
    const requestTypeName = 'requests.requestTypes.' + this.requestType;
    const userName = this.principalService.getUser().firstName + ' ' + this.principalService.getUser().lastName;
    this.requestToSave.name = this.translateService.instant(requestTypeName) + ' ' + userName;
    this.requestToSave.requestType = this.firstPageForm.get('requestType').value;
    this.requestToSave.position = this.secondPageForm.get('position').value;
    this.requestToSave.positionDescription = this.secondPageForm.get('positionDescription').value;
    this.requestToSave.amountOfHours = this.secondPageForm.get('amountOfHours').value;
    this.requestToSave.responsibilities = this.secondPageForm.get('responsibilities').value;

    this.requestToSave.companyName = this.thirdPageForm.get('companyName').value;
    this.requestToSave.nip = this.thirdPageForm.get('companyNip').value;
    this.requestToSave.departmentName = this.thirdPageForm.get('departmentName').value;
    this.requestToSave.practiceSuperviserName = this.thirdPageForm.get('practiceSuperviserName').value;
    this.requestToSave.practiceSuperviserLastName = this.thirdPageForm.get('practiceSuperviserLastName').value;
    this.requestToSave.practiceSuperviserPhone = this.thirdPageForm.get('practiceSuperviserPhone').value;
    this.requestToSave.practiceSuperviserEmail = this.thirdPageForm.get('practiceSuperviserEmail').value;
    this.requestToSave.infoAgreement = this.fourthPageForm.get('infoAgreement').value;
    this.requestToSave.processingAgreement = this.fourthPageForm.get('processingAgreement').value;
    console.log(this.requestToSave);
  }

  public sendRequest() {
    this.prepareData();
    this.requestsService.createRequest(this.requestToSave).subscribe(
      (res) => this.onSuccessCreateRequest(res),
      (err) => this.onErrorCreateRequest()
    );
  }
}
