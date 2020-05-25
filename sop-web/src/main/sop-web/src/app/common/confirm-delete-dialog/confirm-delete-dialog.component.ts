import { Component, Input, OnInit } from '@angular/core';
import { PlannerService } from '../../_services/planner.service';
import { MessageService } from 'primeng';

@Component({
  selector: 'app-confirm-delete-dialog',
  templateUrl: './confirm-delete-dialog.component.html',
  styleUrls: ['./confirm-delete-dialog.component.css']
})
export class ConfirmDeleteDialogComponent implements OnInit {

  @Input()
  private isVisible: boolean;

  @Input()
  private id: number;

  constructor(private plannerService: PlannerService,
              private messageService: MessageService) { }

  ngOnInit() {
  }

  deleteEvent() {
    if (this.id != null) {
      this.plannerService.deleteEvent(this.id).subscribe(
        () => this.onSuccessDelete(),
        () => this.onErrorDelete()
      );
    }
  }

  private onSuccessDelete() {
    console.log('Usunięto poprawnie!');
    this.messageService.add({ severity: 'success', summary: 'Success Message', detail: 'Order submitted' });
  }

  private onErrorDelete() {
    console.log('Usunięto błędnie!');
    this.messageService.add({ severity: 'error', summary: 'Error Message', detail: 'Order submitted' });

  }
}
