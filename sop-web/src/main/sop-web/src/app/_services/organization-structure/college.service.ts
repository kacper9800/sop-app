import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import global from '../../../global';
import {Observable} from 'rxjs';
import {ICollege} from '../../_model/organization-structure/college.model';
import {CollegeStructure} from '../../_model/organization-structure/college-structure.model';
import {CollegeStructureToSave} from '../../_model/organization-structure/structure-to-save.model';
import {CollegeStructureEnum} from '../../_enums/college-structure.enum';
import {ActivationKey, IActivationKey} from "../../_model/activation-key.model";
import {ICollegeRegister} from "../../_model/college-register.model";

const COLLEGES_API = '/colleges';
const AVAILABLE_COLLEGES_API = '/available-colleges';
const COLLEGE_STRUCTURE_API = '/college-structure';

@Injectable({
  providedIn: 'root'
})

export class CollegeService {

  constructor(private httpClient: HttpClient) {
  }

  public getCollegeForId(id: number): Observable<HttpResponse<ICollege>> {
    return this.httpClient.get<HttpResponse<ICollege>>(global.API + COLLEGES_API + '/' + id);

  }

  public getAllAvailableColleges(): Observable<HttpResponse<ICollege[]>> {
    return this.httpClient.get<HttpResponse<ICollege[]>>(global.API + AVAILABLE_COLLEGES_API);
  }

  // Superadmin creates new college via this method
  public createNewCollege(collegeToSave: ICollege): Observable<ICollege> {
    return this.httpClient.post<ICollege>(global.API + COLLEGES_API + '/createNew', collegeToSave);
  }

  // Superadmin activates token for new college
  public activateCollege(activationKeyToSave: IActivationKey): Observable<number> {
    return this.httpClient.post<number>(global.API + COLLEGES_API + '/activate', activationKeyToSave);
  }

  // College admin register college via this method
  public registerCollege(collegeRegister: ICollegeRegister): Observable<number> {
    return this.httpClient.post<number>(global.API + COLLEGES_API + '/register', collegeRegister);
  }

  public getCollegeStructure(): Observable<CollegeStructure> {
    return this.httpClient.get<CollegeStructure>(global.API + COLLEGE_STRUCTURE_API);
  }

  public createNewStructure(structureToSave: CollegeStructureToSave): Observable<number> {
    return this.httpClient.post<number>(global.API + COLLEGE_STRUCTURE_API, structureToSave);
  }

  public deleteCollegeStructure(id: number, collegeStructure: CollegeStructureEnum): Observable<HttpResponse<number>> {
    let params: HttpParams = new HttpParams();
    params = params.append('collegeStructureId', id.toString());
    params = params.append('collegeStructure', collegeStructure.toString());
    return this.httpClient.delete<number>(global.API + COLLEGE_STRUCTURE_API, {
      params,
      observe: 'response'
    });
  }


}
