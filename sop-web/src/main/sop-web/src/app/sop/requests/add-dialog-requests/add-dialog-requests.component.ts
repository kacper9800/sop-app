import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {IRequest, Request} from '../../../_model/request.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Dictionary, IDictionary} from '../../../_model/dictionary.model';
import {IUserView} from '../../../_model/user-view.model';
import {TranslateService} from '@ngx-translate/core';
import {HttpResponse} from "@angular/common/http";
import {DictionariesService} from "../../../_services/dictionaries.service";
import {$e} from "codelyzer/angular/styles/chars";

@Component({
  selector: 'app-add-dialog-requests',
  templateUrl: './add-dialog-requests.component.html',
  styleUrls: ['./add-dialog-requests.component.css']
})
export class AddDialogRequestsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayDialog: any;
  public blockUI: boolean;

  public requestToSave: Request = new Request();

  public firstPageForm: FormGroup;
  public secondPageForm: FormGroup;
  public thirdPageForm: FormGroup;

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
    this.firstPageForm.patchValue({requestType: null});
    // this.secondPageForm = this.formBuilder.group({});
    //
    //   this.requestForm = this.formBuilder.group({
    //     moderator: new FormControl({
    //       value: request ? request.nip : null,
    //       disabled: false
    //     }, Validators.required),
    //     nip: new FormControl({
    //       value: request ? request.nip : null,
    //       disabled: false
    //     }, Validators.required),
    //     companyDescription: new FormControl({
    //       value: request ? request.companyDescription : null,
    //       disabled: false
    //     }),
    //     positionDescription: new FormControl({
    //       value: request ? request.positionDescription : null,
    //       disabled: false
    //     }),
    //     active: new FormControl({value: request ? request.active : null, disabled: false}),
    //     deleted: new FormControl({value: request ? request.removed : null, disabled: false})
    //   });
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

  public onChangeSelect($event: any) {
    if ($event !== 'null') {
      this.firstPageFormValid = true;
    } else {
      this.firstPageFormValid = false;
    }
  }
}
