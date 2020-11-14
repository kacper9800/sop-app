import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {IRequest} from '../_model/request.model';
import global from '../../global';

const API_URL = '/requests';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) {
  }

  public getAllRequests(): Observable<HttpResponse<IRequest[]>> {
    return this.http.get<HttpResponse<IRequest[]>>(global.API + API_URL);
  }
}
