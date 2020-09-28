import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PlannerService} from '../../_services/planner.service';
import {MessageService} from 'primeng';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {CollegeStructureEnum} from '../../_enums/college-structure.enum';

@Component({
  selector: 'app-confirm-delete-dialog',
  templateUrl: './confirm-delete-dialog.component.html',
  styleUrls: ['./confirm-delete-dialog.component.css']
})
export class ConfirmDeleteDialogComponent implements OnInit {

  @Input()
  public isVisible: boolean;
  @Input()
  private id: number;
  @Input()
  private collegeStructureLevel: CollegeStructureEnum;
  @Input()
  private componentName: string;

  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  private blockUI: boolean;

  constructor(private plannerService: PlannerService,
              private messageService: MessageService,
              private collegeService: CollegeService) {
  }

  ngOnInit() {
  }

  public prepareCollegeStructureData(id: number, collegeStructureLevel: CollegeStructureEnum): void {
    this.isVisible = true;
    this.id = id;
    this.componentName = 'collegeStructure';
    this.collegeStructureLevel = collegeStructureLevel;
  }

  public delete() {
    if (this.componentName === 'collegeStructure') {
      this.deleteCollegeStructure();
    } else if (this.componentName === 'events') {
      this.deleteEvent();
    } else {

    }
  }

  public deleteCollegeStructure() {
    this.collegeService.deleteCollegeStructure(this.id, this.collegeStructureLevel).subscribe(
      () => this.onSuccessDelete(),
      () => this.onErrorDelete()
    );
  }

  private onSuccessDelete() {
    console.log('Usunięto poprawnie!');
    this.isVisible = false;
    this.blockUI = false;
    // this.messageService.add({ severity: 'success', summary: 'Success Message', detail: 'Order submitted' });
  }

  private onErrorDelete() {
    console.log('Usunięto błędnie!');
    this.isVisible = false;
    this.blockUI = false;
    // this.messageService.add({ severity: 'error', summary: 'Error Message', detail: 'Order submitted' });

  }

  public deleteEvent(): void {
    if (this.id != null) {
      this.plannerService.deleteEvent(this.id).subscribe(
        () => this.onSuccessDelete(),
        () => this.onErrorDelete()
      );
    }
  }
}
