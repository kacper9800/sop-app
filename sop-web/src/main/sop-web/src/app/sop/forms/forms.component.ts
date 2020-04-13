import { Component, OnInit, ViewChild } from '@angular/core';
import { ClrWizard } from '@clr/angular';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css']
})
export class FormsComponent implements OnInit {

  @ViewChild('internI', {static: false}) formInternI: ClrWizard;
  @ViewChild('internII', {static: false}) formInternII: ClrWizard;
  @ViewChild('superviseI', {static: false}) formSuperviseI: ClrWizard;
  @ViewChild('superviseII', {static: false}) formSuperviseII: ClrWizard;

  formInternIOpen: boolean = false;
  formInternIIOpen: boolean = false;
  formSuperviseIOpen: boolean = false;
  formSuperviseIIOpen: boolean = false;

  constructor() {
  }

  ngOnInit() {
  }

}
