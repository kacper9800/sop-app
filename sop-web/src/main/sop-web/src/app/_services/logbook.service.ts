import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import global from '../../global';
import {ILogbook} from '../_model/logbook.model';
import {ILogbookPost} from "../_model/logbook-post.model";

const LOGBOOK_API = '/logbooks';
const LOGBOOK_POSTS_API = '/logbooks-posts';

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

  public getAllLogbooksForIntern(id: number): Observable<HttpResponse<ILogbook[]>> {
    return this.httpClient.get<HttpResponse<ILogbook[]>>(global.API + LOGBOOK_API + '/intern/' + id);
  }

  public getAllLogbooksForLoggedIntern(): Observable<HttpResponse<ILogbook[]>> {
    return this.httpClient.get<HttpResponse<ILogbook[]>>(global.API + LOGBOOK_API + '/intern');
  }

  public getAllLogbookPostsForLogbookId(id: number) {
    return this.httpClient.get<ILogbookPost[]>(global.API + LOGBOOK_POSTS_API + '/' + id);
  }

  public createNewLogbook(logbookToSave: ILogbook): Observable<HttpResponse<ILogbook>> {
    return this.httpClient.post<HttpResponse<ILogbook>>(global.API + LOGBOOK_API, logbookToSave);
  }

  public createNewLogbookPost(logbookPostToSave: ILogbookPost): Observable<HttpResponse<ILogbookPost>> {
    return this.httpClient.post<HttpResponse<ILogbookPost>>(global.API + LOGBOOK_POSTS_API, logbookPostToSave);
  }

  public deleteLogbook(id: number): Observable<number> {
    return this.httpClient.delete<number>(global.API + LOGBOOK_API + '/' + id);
  }
}
