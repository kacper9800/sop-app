import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { IEvent } from '../_model/event.model';

@Injectable({
  providedIn: 'root'
})
export class PlannerService {
  API_URL = 'http://localhost:8082/api/planner/';

  constructor(private http: HttpClient) {
  }


  getAllEventsForUser(userId: number): Observable<any> {
    return this.http.get(this.API_URL + 'event/' + userId, {responseType: 'text'});
  }

  createNewEvent(event: IEvent): Observable<number> {
    return this.http.post<number>(this.API_URL + 'event/' + event, {responseType: 'text'});
  }


}
