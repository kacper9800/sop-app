import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {IDepartment} from '../../_model/organization-structure/department.model';

const DEPARTMENTS_API = '/departments';

@Injectable({
  providedIn: 'root'
})

export class DepartmentService {

  constructor(private httpClient: HttpClient) {
  }

  public registerDepartment(departmentToSave: IDepartment): Observable<IDepartment> {
    return this.httpClient.post<IDepartment>(global.API + DEPARTMENTS_API, departmentToSave);
  }
}
