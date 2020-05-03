import { Component, Input, OnInit } from '@angular/core';
import { UsersService } from '../../_services/users.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  @Input()
  isLoggedIn: Boolean = false;

  constructor(private usersService: UsersService) {
  }

  ngOnInit() {
    this.loadTest();
  }

  private loadTest() {
    this.usersService.getTest().subscribe(
      (res: HttpResponse<any>) => this.onSuccessTest(res.body),
      (res: HttpResponse<any>) => this.onError(res.body),
    );
  }

  private onSuccessTest(body: any) {
    console.log('git!');
  }

  private onError(body: any) {
    console.log('lipa!')
  }
}
