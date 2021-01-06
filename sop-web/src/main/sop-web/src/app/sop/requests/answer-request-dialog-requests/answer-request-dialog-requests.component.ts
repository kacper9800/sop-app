import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {IRequest, Request} from '../../../_model/request.model';
import {TranslateService} from '@ngx-translate/core';
import {RequestsService} from '../../../_services/requests.service';
import {UserService} from '../../../_services/user.service';
import {IUserView} from '../../../_model/user-view.model';
import {IDictionary} from '../../../_model/dictionary.model';
import {DictionariesService} from '../../../_services/dictionaries.service';
import {PrincipalService} from '../../../_services/auth/principal.service';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-answer-request-dialog-requests',
  templateUrl: './answer-request-dialog-requests.component.html',
  styleUrls: ['./answer-request-dialog-requests.component.css']
})
export class AnswerRequestDialogRequestsComponent implements OnInit {


  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayDialog: any;
  public blockUI: boolean;
  public dialogTitle: string;
  public request: Request = new Request();
  public requestToSave: Request = new Request();
  public admins: IUserView[];
  public decisionStatuses: IDictionary[];
  public isDecisionPositive: boolean;

  public validateBtnState: any;
  public moderatorDecisionForm: FormGroup;
  public directorDecisionForm: FormGroup;

  constructor(private translateService: TranslateService,
              private userService: UserService,
              private formBuilder: FormBuilder,
              public principalService: PrincipalService,
              private dictionaryService: DictionariesService,
              private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.prepareForm();
    this.loadDecisionStatuses();
    this.loadAdmins();
  }

  public prepareForm() {
    if (this.principalService.isModerator()) {
      this.moderatorDecisionForm = this.formBuilder.group({
        moderatorDecisionStatus: new FormControl(''),
        moderatorDecisionFeedback: new FormControl(''),
        adminId: new FormControl(''),
      });
    } else if (this.principalService.isDirector()) {
      this.directorDecisionForm = this.formBuilder.group({
        adminDecisionStatus: new FormControl(''),
        adminDecisionFeedback: new FormControl('')
      });
    }
  }

  public isSaveButtonDisabled(): boolean {
    if (this.principalService.isModerator()) {
      return this.moderatorDecisionForm.invalid;
    } else if (this.principalService.isDirector()) {
      return this.directorDecisionForm.invalid;
    }
  }

  private loadDecisionStatuses() {
    this.dictionaryService.getRequestDecisionStatuses().subscribe(
      (res: any) => this.onSuccessLoadDecisionStatuses(res),
      (err) => this.onErrorLoadDecisionStatuses(err)
    );
  }

  private onSuccessLoadDecisionStatuses(res: IDictionary[]) {
    this.decisionStatuses = [];
    this.decisionStatuses.push({
      name: this.translateService.instant('requests.wizard.chooseDecision'),
      value: null
    });
    res.forEach(dictionary => this.decisionStatuses.push({
      name: this.translateService.instant('requests.decisionStatuses.' + dictionary.value),
      value: dictionary.value,
    }));
  }

  private onErrorLoadDecisionStatuses(err) {
    console.log(err);
  }

  private loadAdmins() {
    this.userService.getAdminsForInstitute().subscribe(
      (res: any) => this.onSuccessLoadAdmins(res),
      (err) => this.onErrorLoadAdmins(err)
    );
  }

  private onSuccessLoadAdmins(res: any) {
    this.admins = [];
    this.admins.push({
      name: this.translateService.instant('requests.wizard.chooseModerator'),
      id: null
    });
    res.forEach(moderator => this.admins.push({
      name: moderator.firstName + ' ' + moderator.lastName + '->' + moderator.instituteName,
      id: moderator.id,
      instituteId: moderator.instituteId,
      instituteName: moderator.instituteName
    }));
  }

  private onErrorLoadAdmins(err) {

  }

  public showAnswerRequestDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleAnswer');
    this.requestsService.getRequestById(id).subscribe(
      (res) => this.onSuccessLoadRequest(res),
      (err) => this.onErrorLoadRequest(err)
    );
  }

  private onSuccessLoadRequest(res: IRequest) {
    if (res != null) {
      this.request = res;
    }
    this.displayDialog = true;
  }

  private onErrorLoadRequest(err: any) {

  }

  public onDecisionChange() {
    const decision = this.moderatorDecisionForm.get('moderatorDecisionStatus').value;
    if (decision === 'ACCEPTED') {
      this.isDecisionPositive = true;
    } else {
      this.isDecisionPositive = false;
    }
  }

  public onSubmit() {
    if (this.principalService.isModerator()) {
      this.requestToSave = new Request();
      this.requestToSave.id = this.request.id;
      this.requestToSave.adminId = this.moderatorDecisionForm.get('adminId').value;
      this.requestToSave.moderatorDecisionDate = new Date();
      this.requestToSave.moderatorDecisionStatus = this.moderatorDecisionForm.get('moderatorDecisionStatus').value;
      this.requestToSave.moderatorDecisionFeedback = this.moderatorDecisionForm.get('moderatorDecisionFeedback').value;
      this.requestsService.updateRequestAsModerator(this.requestToSave).subscribe(
        (res) => this.onSuccessUpdateRequest(res),
        (err) => this.onErrorUpdateRequest(err)
      );
    } else if (this.principalService.isDirector()) {
      this.requestToSave = new Request();
      this.requestToSave.id = this.request.id;
      this.requestToSave.adminDecisionDate = new Date();
      this.requestToSave.adminDecisionStatus = this.directorDecisionForm.get('adminDecisionStatus').value;
      this.requestToSave.adminDecisionFeedback = this.directorDecisionForm.get('adminDecisionFeedback').value;
      this.requestsService.updateRequestAsDirector(this.requestToSave).subscribe(
        (res) => this.onSuccessUpdateRequest(res),
        (err) => this.onErrorUpdateRequest(err)
      );

    }
  }

  public onSuccessUpdateRequest(res) {
    this.closeDialogWithSaveEmitter.emit();
    this.displayDialog = false;
  }

  public onErrorUpdateRequest(err) {

  }
}
