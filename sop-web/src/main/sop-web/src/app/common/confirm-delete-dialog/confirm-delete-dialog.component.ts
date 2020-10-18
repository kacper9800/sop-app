import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PlannerService} from '../../_services/planner.service';
import {MessageService} from 'primeng';
import {CollegeService} from '../../_services/organization-structure/college.service';
import {CollegeStructureEnum} from '../../_enums/college-structure.enum';
import {ActivationKeyService} from '../../_services/activation-key.service';

@Component({
  selector: 'app-confirm-delete-dialog',
  templateUrl: './confirm-delete-dialog.component.html',
  styleUrls: ['./confirm-delete-dialog.component.css']
})
export class ConfirmDeleteDialogComponent implements OnInit {
  @Input()
  public isVisible: boolean;
  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  private id: number;
  private collegeStructureLevel: CollegeStructureEnum;
  private componentName: string;
  private blockUI: boolean;

  constructor(private plannerService: PlannerService,
              private messageService: MessageService,
              private collegeService: CollegeService,
              private activationKeyService: ActivationKeyService) {
  }

  ngOnInit() {
  }
  public prepareCollegeData(id: number): void {
    this.isVisible = true;
    this.id = id;
    this.componentName = 'colleges';
  }

  public prepareCollegeStructureData(id: number, collegeStructureLevel: CollegeStructureEnum): void {
    this.isVisible = true;
    this.id = id;
    this.componentName = 'collegeStructure';
    this.collegeStructureLevel = collegeStructureLevel;
  }

  public prepareActivationKeyData(id: number): void {
    this.isVisible = true;
    this.id = id;
    this.componentName = 'activationKeys';
  }

  public delete() {
    if (this.componentName === 'collegeStructure') {
      this.deleteCollegeStructure();
    } else if (this.componentName === 'events') {
      this.deleteEvent();
    } else if (this.componentName === 'activationKeys') {
      this.deleteActivationKey();
    } else if (this.componentName === 'colleges') {
      this.deleteCollege();
    }
  }

  public deleteCollegeStructure() {
    this.collegeService.deleteCollegeStructure(this.id, this.collegeStructureLevel).subscribe(
      () => this.onSuccessDelete(),
      () => this.onErrorDelete()
    );
  }

  private onSuccessDelete() {
    this.isVisible = false;
    this.blockUI = false;
    this.closeDialogWithSaveEmitter.emit();
    // this.messageService.add({ severity: 'success', summary: 'Success Message', detail: 'Order submitted' });
  }

  private onErrorDelete() {
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

  private deleteActivationKey(): void {
    if (this.id != null) {
      this.activationKeyService.deleteActivationKeyForId(this.id).subscribe(
        () => this.onSuccessDelete(),
        () => this.onErrorDelete()
      );
    }
  }

  private deleteCollege() {
    if (this.id != null) {
      this.collegeService.deleteCollege(this.id).subscribe(
        () => this.onSuccessDelete(),
        () => this.onErrorDelete()
      );
    }
  }
}
