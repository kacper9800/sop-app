import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {ICollege} from '../../_model/college.model';

const COLLEGES_API = '/colleges';

@Injectable({
  providedIn: 'root'
})

export class CollegeService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllAvailableColleges(): Observable<ICollege[]> {
    return this.httpClient.get<ICollege[]>(global.API + COLLEGES_API);
  }

  public registerCollege(collegeToSave: ICollege): Observable<ICollege> {
    return this.httpClient.post<ICollege>(global.API + COLLEGES_API, collegeToSave);
  }
}