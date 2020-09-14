import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {ICollege} from '../../_model/organization-structure/college.model';
import {CollegeStructure} from '../../_model/organization-structure/college-structure.model';
import {CollegeStructureToSave} from '../../_model/organization-structure/structure-to-save.model';
import {CollegeStructureEnum} from '../../_enums/college-structure.enum';

const COLLEGES_API = '/colleges';
const AVAILABLE_COLLEGES_API = '/available-colleges';
const COLLEGE_STRUCTURE_API = '/college-structure';

@Injectable({
  providedIn: 'root'
})

export class CollegeService {

  constructor(private httpClient: HttpClient) {
  }

  public getCollegeStructure(): Observable<CollegeStructure> {
    return this.httpClient.get<CollegeStructure>(global.API + COLLEGE_STRUCTURE_API);
  }

  public getAllAvailableColleges(): Observable<ICollege[]> {
    return this.httpClient.get<ICollege[]>(global.API + AVAILABLE_COLLEGES_API);
  }

  public registerCollege(collegeToSave: ICollege): Observable<ICollege> {
    return this.httpClient.post<ICollege>(global.API + COLLEGES_API, collegeToSave);
  }

  public createNewStructure(structureToSave: CollegeStructureToSave): Observable<number> {
    return this.httpClient.post<number>(global.API + COLLEGE_STRUCTURE_API, structureToSave);
  }

  public deleteCollegeStructure(id: number, collegeStructure: CollegeStructureEnum): Observable<HttpResponse<number>> {
    let params: HttpParams = new HttpParams();
    params = params.append('collegeStructureId', id.toString());
    params = params.append('collegeStructure', collegeStructure.toString());
    return this.httpClient.delete<number>(global.API + COLLEGE_STRUCTURE_API, { params, observe: 'response' });
  }

}
