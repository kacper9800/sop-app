/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../security/user';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username, password): Observable<any> {
    console.log('login service');
    return this.http.post(AUTH_API + 'signIn', {
      username: username,
      password: password
    }, httpOptions);
  }

  register(user: User): Observable<any> {
    return this.http.post(AUTH_API + 'signUp', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
