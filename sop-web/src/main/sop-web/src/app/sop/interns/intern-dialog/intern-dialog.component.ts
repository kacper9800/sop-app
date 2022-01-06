import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-intern-dialog',
  templateUrl: './intern-dialog.component.html',
  styleUrls: ['./intern-dialog.component.css']
})
export class InternDialogComponent implements OnInit {
  @Output()
  public closeDialogWithSaveEmitter: EventEmitter<any> = new EventEmitter<any>();
  public displayDialog: any;
  public dialogTitle: any;
  public internForm: FormGroup;
  public internId: number;

  constructor() { }

  ngOnInit(): void {
  }

  public showInternDialog(id: number) {
    this.displayDialog = true;
    this.internId = id;
  }

}
