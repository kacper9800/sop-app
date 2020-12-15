import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {Request} from '../../../_model/request.model';
import {RequestStatus} from "../../../_enums/request-status.enum";

@Component({
  selector: 'app-view-dialog-requests',
  templateUrl: './view-dialog-requests.component.html',
  styleUrls: ['./view-dialog-requests.component.css']
})
export class ViewDialogRequestsComponent implements OnInit {

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayDialog: any;
  public blockUI: boolean;
  public dialogTitle: string;
  public request: Request = new Request();
  public isModeratorDecisionStatusAwaiting: any;
  public isAdminDecisionStatusAwaiting: any;

  constructor(private translateService: TranslateService) {
  }

  ngOnInit(): void {
  }

  public showRequestDialog() {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleView');
    this.displayDialog = true;
    this.request.sendRequestDate = new Date();
    // this.request.adminDecisionDate = new Date();
    this.request.moderatorDecisionDate = new Date();
    this.request.moderatorDecisionStatus = RequestStatus.ACCEPTED;
    this.request.adminDecisionFeedback = 'Poprawnie';
    this.request.moderatorDecisionFeedback = 'Ok';
    if (this.request.moderatorDecisionStatus === RequestStatus.ACCEPTED) {
      this.isAdminDecisionStatusAwaiting = true;
    }
  }

}
