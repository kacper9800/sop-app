import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  const
  API_URL = 'http://localhost:8082/api/Todo/';


  constructor(private http: HttpClient) {
  }

  getToDoListForUser(id: number): Observable<any> {
    return this.http.get(this.API_URL + id, {observe: 'response'});
  }

  addTitle(title: string) {
    return this.http.post(this.API_URL + title, {observe: 'response'});
  }

  changeCheckStatus(userId: number, toDoId: number, flag: boolean) {
    let params: HttpParams = new HttpParams();
    if (userId) {
      params = params.append('userId', userId.toString());
    }
    if (toDoId) {
      params = params.append('toDoId', toDoId.toString());
    }
    if (flag) {
      params = params.append('flag', flag.toString());
    }
    return this.http.get<any>(this.API_URL + '/change', {
      params,
      observe: 'response'
    });
  }

  removeTitle(toDoId: number) {
    return this.http.delete<any>(this.API_URL + '/delete/' + toDoId);
  }
}
