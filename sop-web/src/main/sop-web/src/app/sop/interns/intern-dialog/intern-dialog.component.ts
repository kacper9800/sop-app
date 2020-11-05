import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-intern-dialog',
  templateUrl: './intern-dialog.component.html',
  styleUrls: ['./intern-dialog.component.css']
})
export class InternDialogComponent implements OnInit {
  public displayDialog: any;
  public dialogTitle: any;
  public internForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

  public showInternDialog(id: number) {
    this.displayDialog = true;

  }

}
