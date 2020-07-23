import {Component, OnInit} from '@angular/core';
import {TreeNode} from "primeng";
import {CollegeService} from "../../_services/structure/college.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-college-structure',
  templateUrl: './college-structure.component.html',
  styleUrls: ['./college-structure.component.css']
})
export class CollegeStructureComponent implements OnInit {

  collegeStructure: TreeNode[];

  cols: any[];

  constructor(private collegeService: CollegeService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.collegeService.getCollegeStructure().subscribe(
      (res: any[]) => this.onSuccessLoadCollegeStructure(res),
      () => this.onErrorLoadCollegeStructure()
    );

    this.cols = [
      {field: 'name', header: 'common.name'},
      {field: 'location', header: 'common.location'},
      {field: 'actions', header: 'common.actions'}
    ];
  }

  private onSuccessLoadCollegeStructure(res: any[]) {
    this.collegeStructure = res;
  }

  private onErrorLoadCollegeStructure() {

  }
}
