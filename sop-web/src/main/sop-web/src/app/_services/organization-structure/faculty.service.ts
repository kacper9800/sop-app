import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {IFaculty} from '../../_model/organization-structure/faculty.model';

const FACULTIES_API = '/faculties';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllAvailableFaculties(): Observable<IFaculty[]> {
    return this.httpClient.get<IFaculty[]>(global.API + FACULTIES_API);
  }

  public registerFaculty(facultyToSave: IFaculty): Observable<IFaculty> {
    return this.httpClient.post<IFaculty>(global.API + FACULTIES_API, facultyToSave);
  }
}
