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

const API_URL = 'http://localhost:8082/api/test/';
const USERS_API = '/users';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
              private token: TokenStorageService) {
  }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', {responseType: 'text'});
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', {responseType: 'text'});
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', {responseType: 'text'});
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', {responseType: 'text'});
  }

  getAllUsers(): Observable<any> {
    return this.http.get<User[]>(global.API + USERS_API);
  }

  public changeCollege(selectedCollegeId: any): Observable<HttpResponse<number>> {
    return this.http.get<HttpResponse<number>>(global.API + USERS_API + '/changeCollege/' + selectedCollegeId);
  }
}
