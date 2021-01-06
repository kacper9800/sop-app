import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {IRequest, Request} from '../_model/request.model';
import global from '../../global';

const API_URL = '/requests';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) {
  }

  public getRequestById(id: number) {
    return this.http.get<IRequest>(global.API + API_URL + '/' + id);
  }

  public getAllRequestsForStudent() {
    return this.http.get<HttpResponse<IRequest[]>>(global.API + API_URL + '/student');
  }

  public getAllRequestsForDirector() {
    return this.http.get<HttpResponse<IRequest[]>>(global.API + API_URL + '/director');
  }

  public getAllRequestsForInstitute() {
    return this.http.get<HttpResponse<IRequest[]>>(global.API + API_URL + '/institute');
  }

  public getAllRequests(): Observable<HttpResponse<IRequest[]>> {
    return this.http.get<HttpResponse<IRequest[]>>(global.API + API_URL);
  }

  public getRequestForId(id: number) {
    return this.http.get<HttpResponse<IRequest>>(global.API + API_URL + '/' + id);
  }

  public updateRequestAsModerator(requestToUpdate: Request) {
    return this.http.put(global.API + API_URL + '/moderatorResponse', requestToUpdate);
  }

  public updateRequestAsDirector(requestToUpdate: Request) {
    return this.http.put(global.API + API_URL + '/directorResponse', requestToUpdate);
  }

  public createRequest(requestToSave: IRequest) {
    return this.http.post(global.API + API_URL, requestToSave);
  }
}
