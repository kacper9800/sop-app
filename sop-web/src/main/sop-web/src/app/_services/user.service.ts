/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../security/user';
import {TokenStorageService} from './auth/token-storage.service';
import global from '../../global';
import {IUserView} from '../_model/user-view.model';

const USERS_API = '/users';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
              private token: TokenStorageService) {
  }

  getAllUsers(): Observable<any> {
    return this.http.get<User[]>(global.API + USERS_API);
  }

  public changeCollege(selectedCollegeId: any): Observable<HttpResponse<number>> {
    return this.http.get<HttpResponse<number>>(global.API + USERS_API + '/changeCollege/' + selectedCollegeId);
  }

  public getModeratorsForInstitute(): Observable<HttpResponse<IUserView[]>> {
    return this.http.get<HttpResponse<IUserView[]>>(global.API + USERS_API + '/institute/moderators');
  }

  public getAdminsForInstitute(): Observable<HttpResponse<IUserView[]>> {
    return this.http.get<HttpResponse<IUserView[]>>(global.API + USERS_API + '/institute/admins');
  }
}
