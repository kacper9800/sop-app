import { Component, OnInit } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-export-table',
  templateUrl: './export-table.component.html',
  styleUrls: ['./export-table.component.css']
})
export class ExportTableComponent implements OnInit {
  public displayDialog: boolean;
  public dialogTitle: any;
  public blockUI: boolean;

  constructor(private translateService: TranslateService) { }

  ngOnInit(): void {
    this.dialogTitle = this.translateService.instant('common.export.dialogTitle');
  }

  public showExportTableDialog(data: any[]) {
    this.blockUI = true;
    this.displayDialog = true;
    this.dialogTitle = this.translateService.instant('common.export.dialogTitle');
  }
}
