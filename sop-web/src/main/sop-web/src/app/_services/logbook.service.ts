import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import global from '../../global';
import {ILogbook} from '../_model/logbook.model';
const LOGBOOK_API = '/logbooks';

@Injectable({
  providedIn: 'root'
})
export class LogbookService {

  constructor(private httpClient: HttpClient) {
  }

  public getLogbook(id: number): Observable<HttpResponse<ILogbook>> {
    return this.httpClient.get<HttpResponse<ILogbook>>(global.API + LOGBOOK_API + '/' + id);
  }

  public getAllLogbooks(): Observable<HttpResponse<ILogbook[]>> {
    return this.httpClient.get<HttpResponse<ILogbook[]>>(global.API + LOGBOOK_API);
  }

  public createNewLogbook(logbookToSave: ILogbook): Observable<HttpResponse<ILogbook>> {
    return this.httpClient.post<HttpResponse<ILogbook>>(global.API + LOGBOOK_API, logbookToSave);
  }

  public deleteLogbook(id: number): Observable<number> {
    return this.httpClient.delete<number>(global.API + LOGBOOK_API + '/' + id);
  }
}
