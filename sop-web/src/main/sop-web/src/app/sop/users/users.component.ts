import { Component, Input, OnInit } from '@angular/core';
import { UsersService } from '../../_services/users.service';
import {User} from '../../security/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  selectedUsers: any;
  columns: any;
  users: User[] = [];

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.loadUsers();
  }

  private loadUsers() {

  }

  editIntern() {

  }

  showAddNewDialog() {

  }

  onExportAll() {

  }

  onExportSelected() {

  }

  copyKeyValue(value: any) {

  }

  showEditDialog(value: any) {

  }

  showConfirmDeleteDialog() {

  }

}
