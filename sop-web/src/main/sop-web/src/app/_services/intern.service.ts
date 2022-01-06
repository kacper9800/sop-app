/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from './auth/token-storage.service';
import global from '../../global';
import {Intern} from '../_model/intern.model';

const INTERNS_API = '/interns';


@Injectable({
  providedIn: 'root'
})
export class InternService {

  constructor(private http: HttpClient,
              private token: TokenStorageService) {
  }

  public getAllInternsForCollege(): Observable<any> {
    return this.http.get<Intern[]>(global.API + INTERNS_API + '-college');
  }

  public getAllInternsForInstitute(id: number): Observable<any> {
    return this.http.get<Intern[]>(global.API + INTERNS_API + '-institute/' + id);
  }

  public loadInternsForDirections(selectedClassId: any[]) {
    return this.http.get<Intern[]>(global.API + INTERNS_API + '/directions/' + selectedClassId);
  }
}
