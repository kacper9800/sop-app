import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {IInstitute} from '../../_model/organization-structure/institute.model';

const INSTITUTES_API = '/institutes';
const COLLEGE_INSTITUTES_API = '/college-institutes';

@Injectable({
  providedIn: 'root'
})

export class InstituteService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllAvailableColleges(): Observable<IInstitute[]> {
    return this.httpClient.get<IInstitute[]>(global.API + INSTITUTES_API);
  }

  public registerCollege(instituteToSave: IInstitute): Observable<IInstitute> {
    return this.httpClient.post<IInstitute>(global.API + INSTITUTES_API, instituteToSave);
  }

  public getCollegeStructure(): Observable<any[]> {
    return this.httpClient.get<any[]>(global.API + INSTITUTES_API);
  }

  public getAllInstitutesForCollege(): Observable<HttpResponse<IInstitute[]>> {
    return this.httpClient.get<HttpResponse<IInstitute[]>>(global.API + COLLEGE_INSTITUTES_API);
  }
}
