/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CollegeRegister} from '../../_model/college-register.model';
import {UserRegistration} from '../../_model/user-registration.model';
import global from '../../../global';

const AUTH_API = '/auth';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json',
                                    'Access-Control-Allow-Origin': '*'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(credentials): Observable<any> {
    return this.http.post(global.API + AUTH_API + '/signIn', {
      username: credentials[0],
      password: credentials[1]
    }, httpOptions);
  }

  public register(user: UserRegistration): Observable<any> {
    return this.http.post(global.API + AUTH_API + '/signUp', {
      token: user.token,
      firstName: user.firstName,
      lastName: user.lastName,
      birthDate: user.birthDate,
      email: user.email,
      sex: user.sex,
      password: user.password,
    }, httpOptions);
  }

  public registerCollege(college: CollegeRegister): Observable<any> {
    return this.http.post(global.API + AUTH_API + '/signUpCollege', {
      activationKey: college.activationKey,
      collegeId: college.collegeId,
      email: college.email,
      password: college.password
    }, httpOptions);
  }
}
