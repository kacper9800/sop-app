import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import global from '../../global';
import {Observable} from 'rxjs';
import {Company, ICompany} from '../_model/company.model';

const COMPANIES_API = '/companies';

@Injectable({
  providedIn: 'root'
})

export class CompanyService {

  constructor(private httpClient: HttpClient) {
  }

  public getComapny(id: number): Observable<HttpResponse<ICompany>> {
    return this.httpClient.get<HttpResponse<ICompany>>(global.API + COMPANIES_API + '/' + id);
  }

  public getAllCompanies(): Observable<HttpResponse<ICompany[]>> {
    return this.httpClient.get<HttpResponse<ICompany[]>>(global.API + COMPANIES_API);
  }

  public createNewCompany(companyToSave: ICompany): Observable<HttpResponse<ICompany>> {
    return this.httpClient.post<HttpResponse<ICompany>>(global.API + COMPANIES_API, companyToSave);
  }

  public deleteCompany(id: number): Observable<number> {
    return this.httpClient.delete<number>(global.API + COMPANIES_API + '/' + id);
  }
}
