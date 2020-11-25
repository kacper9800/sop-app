import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TokenService} from '../../../_helpers/token.service';
import {TranslateService} from '@ngx-translate/core';
import {ClrLoadingState} from '@clr/angular';
import {RequestsService} from '../../../_services/requests.service';
import {IRequest, Request} from '../../../_model/request.model';
import {DictionariesService} from '../../../_services/dictionaries.service';
import {HttpResponse} from '@angular/common/http';
import {Dictionary, IDictionary} from '../../../_model/dictionary.model';
import {IUserView} from '../../../_model/user-view.model';
import {UserService} from "../../../_services/user.service";

@Component({
  selector: 'app-add-edit-dialog-requests',
  templateUrl: './add-edit-dialog-requests.component.html',
  styleUrls: ['./add-edit-dialog-requests.component.css']
})
export class AddEditDialogRequestsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public request: Request;
  public blockUI: boolean;
  public displayDialog: any;
  public requestForm: FormGroup;
  private requestToSave: Request;

  public dialogTitle: string;
  public validateBtnState: any;

  public requestTypes: Dictionary[];

  public moderators: IUserView[];
  public admins: IUserView[];

  constructor(private requestService: RequestsService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private userService: UserService,
              private dictionaryService: DictionariesService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.blockUI = true;
    this.prepareForm(this.request);
    this.loadRequestTypes();
  }

  private prepareForm(request: IRequest): void {
    this.requestForm = this.formBuilder.group({
      requestType: new FormControl({value: null}, Validators.required),
      moderator: new FormControl({value: request ? request.nip : null, disabled: false}, Validators.required),
      nip: new FormControl({value: request ? request.nip : null, disabled: false}, Validators.required),
      companyDescription: new FormControl({value: request ? request.companyDescription : null, disabled: false}),
      positionDescription: new FormControl({value: request ? request.positionDescription : null, disabled: false}),
      active: new FormControl({value: request ? request.active : null, disabled: false}),
      deleted: new FormControl({value: request ? request.removed : null, disabled: false})
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

  private loadModerators() {
    this.userService.getModeratorsForInstitute().subscribe(
      (res: HttpResponse<IDictionary[]>) => this.onSuccessLoadModerators(res),
      (error => this.onErrorLoadModerators(error))
    );
  }

  private onSuccessLoadModerators(body) {
    this.requestTypes = [];
    this.requestTypes.push({name: this.translateService.instant('requests.chooseModerator'), value: null});
    body.forEach(requestType => this.requestTypes.push({
      name: requestType.value,
      value: requestType.value
    }));
    this.blockUI = false;
  }

  private onErrorLoadModerators(error: any) {
    this.blockUI = false;
  }

  public showNewRequestDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleNew');
    this.displayDialog = true;
    this.prepareForm(null);
  }

  public showEditRequestDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleEdit');
    this.requestService.getRequestForId(id).subscribe(
      (res) => this.onSuccessLoadRequest(res),
      (res) => this.onErrorLoadRequest(res)
    );
  }

  private onSuccessLoadRequest(res) {
    this.request = res;
    this.displayDialog = true;
    this.blockUI = false;
    this.prepareForm(res);
  }

  private onErrorLoadRequest(res: any) {
    this.blockUI = false;
  }



  public onSubmit() {
    this.blockUI = true;
    this.validateBtnState = ClrLoadingState.LOADING;
    this.collectFormData();
    this.requestService.createRequest(this.requestToSave).subscribe(
      (res: number) => this.onSuccessCreateRequest(res),
      (error) => this.onErrorCreateRequest(error)
    );
  }

  private collectFormData() {
    this.requestToSave = new Request();
    this.requestToSave.active = true;
  }

  private onSuccessCreateRequest(res) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.SUCCESS;
    this.displayDialog = false;
  }

  private onErrorCreateRequest(error: any) {
    this.blockUI = false;
    this.validateBtnState = ClrLoadingState.ERROR;
    this.displayDialog = false;
  }

}
