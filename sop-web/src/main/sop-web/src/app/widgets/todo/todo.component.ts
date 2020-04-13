import { Component, Input, OnInit } from '@angular/core';
import { TodoService } from '../../_services/todo.service';
import { User } from '../../security/user';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css'],
  providers: [TodoService]
})
export class TodoComponent implements OnInit {

  @Input()
  userDetails: User;
  toDoList: any[];

  constructor(private toDoService: TodoService) {
  }

  ngOnInit() {
    this.toDoService.getToDoListForUser(this.userDetails.id).subscribe(
      (res: HttpResponse<any>) => this.onSuccessLoadList(res.body, res.headers),
      (res: HttpResponse<any>) => this.onErrorLoadList(res.body)
    );
  }

  onAdd(itemTitle: HTMLInputElement) {
    this.toDoList.push();
    this.toDoService.addTitle(itemTitle.title).subscribe(
      ((res) => this.onSuccessAdd()),
      ((res) => this.onErrorAdd())
    );
  }

  private onSuccessLoadList(body: any, headers: HttpHeaders) {

  }

  private onErrorLoadList(body: any) {

  }

  private onSuccessAdd() {
    console.log('Pomyślnie dodano');
  }

  private onErrorAdd() {
    console.log('Błąd dodawania');
  }
}
