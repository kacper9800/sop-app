import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { IEvent } from '../_model/event.model';
import { WorkSchedule } from '../_model/work-schedule.model';

@Injectable({
  providedIn: 'root'
})
export class PlannerService {
  API_URL = 'http://localhost:8082/api/planner/';
  API_URL_WORK_SCHEDULE = 'http://localhost:8082/api/planner/workSchedule';

  constructor(private http: HttpClient) {
  }

  public getAllEvents(): Observable<any> {
    return this.http.get<Event[]>(this.API_URL + 'event');
  }

  public createNewEvent(event: IEvent): Observable<number> {
    return this.http.post<number>(this.API_URL + 'event', event);
  }

  public deleteEvent(id: number): Observable<any> {
    return this.http.delete<any>(this.API_URL + 'event/' + id);
  }

  public createWorkSchedule(workSchedule: WorkSchedule): Observable<number> {
    return this.http.post<number>(this.API_URL_WORK_SCHEDULE, workSchedule);
  }
}
