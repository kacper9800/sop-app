import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {ICollege} from '../../_model/organization-structure/college.model';
import {CollegeStructure} from '../../_model/organization-structure/college-structure.model';
import {CollegeStructureToSave} from '../../_model/organization-structure/structure-to-save.model';

const COLLEGES_API = '/colleges';
const AVAILABLE_COLLEGES_API = '/available-colleges';
const COLLEGE_STRUCTURE_API = '/college-structure';

@Injectable({
  providedIn: 'root'
})

export class CollegeService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllAvailableColleges(): Observable<ICollege[]> {
    return this.httpClient.get<ICollege[]>(global.API + AVAILABLE_COLLEGES_API);
  }

  public registerCollege(collegeToSave: ICollege): Observable<ICollege> {
    return this.httpClient.post<ICollege>(global.API + COLLEGES_API, collegeToSave);
  }

  public getCollegeStructure(): Observable<CollegeStructure> {
    return this.httpClient.get<CollegeStructure>(global.API + COLLEGE_STRUCTURE_API);
  }

  public createNewStructure(structureToSave: CollegeStructureToSave): Observable<number> {
    return this.httpClient.post<number>(global.API + COLLEGE_STRUCTURE_API, structureToSave);
  }
}
