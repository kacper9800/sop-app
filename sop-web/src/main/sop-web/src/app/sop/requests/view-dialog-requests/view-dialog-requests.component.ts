import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {IRequest, Request} from '../../../_model/request.model';
import {RequestStatus} from '../../../_enums/request-status.enum';
import {RequestsService} from '../../../_services/requests.service';

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

  constructor(private translateService: TranslateService,
              private requestsService: RequestsService) {
  }

  ngOnInit(): void {

  }

  public showRequestDialog(id: number) {
    this.blockUI = true;
    this.dialogTitle = this.translateService.instant('requests.dialog.titleView');
    this.requestsService.getRequestById(id).subscribe(
      (res) => this.onSuccessLoadRequest(res),
      (err) => this.onErrorLoadRequest(err)
    );
  }

  private onSuccessLoadRequest(res: IRequest) {
    if (res != null) {
      this.request.sendRequestDate = res.sendRequestDate;

      this.request.moderatorDecisionStatus = res.moderatorDecisionStatus;
      this.request.moderatorDecisionDate = res.moderatorDecisionDate;
      this.request.moderatorDecisionFeedback = res.moderatorDecisionFeedback;

      this.request.adminDecisionStatus = res.adminDecisionStatus;
      this.request.adminDecisionDate = res.adminDecisionDate;
      this.request.adminDecisionFeedback = res.adminDecisionFeedback;
    }
    this.displayDialog = true;
  }

  private onErrorLoadRequest(err: any) {

  }
}
