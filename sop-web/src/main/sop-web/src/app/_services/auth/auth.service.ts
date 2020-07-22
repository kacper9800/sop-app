/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {College} from '../../_model/college.model';

const AUTH_API = 'http://localhost:8082/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(credentials): Observable<any> {
    console.log(credentials);
    return this.http.post(AUTH_API + 'signIn', {
      username: credentials[0],
      password: credentials[1]
    }, httpOptions);
  }

  public register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signUp', {
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      deleted: false,
    }, httpOptions);
  }

  public registerCollege(college: College): Observable<any> {
    return this.http.post(AUTH_API + 'signUpCollege', {
      collegeId: college.id,

    });
  }
}
