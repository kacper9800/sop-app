import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';

const COMPANIES_API = '/roles';

@Injectable({
  providedIn: 'root'
})

export class RoleService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllRoles(): Observable<HttpResponse<object>> {
    return this.httpClient.get<HttpResponse<object>>(global.API + COMPANIES_API);
  }
}
