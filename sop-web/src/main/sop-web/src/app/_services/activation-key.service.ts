import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ActivationKey, IActivationKey} from '../_model/activation-key.model';
import global from '../../global';

const API_URL = '/activationKeys';

@Injectable({
  providedIn: 'root'
})
export class ActivationKeyService {

  constructor(private http: HttpClient) {
  }

  public getAllActivationKeysForCompany(): Observable<HttpResponse<IActivationKey[]>> {
    return this.http.get<HttpResponse<ActivationKey[]>>(global.API + API_URL);
  }

  public getActivationKeyForId(activationKeyId: number): Observable<HttpResponse<ActivationKey>> {
    return this.http.get<HttpResponse<ActivationKey>>(global.API + API_URL + '/' + activationKeyId);
  }

  public getActivationKeyForValue(value: string): Observable<HttpResponse<IActivationKey>> {
    return this.http.get<HttpResponse<IActivationKey>>(global.API + API_URL + '/value', {params: {keyValue: value}});
  }

  public createActivationKeyForCollege(activationKeyToSave: any): Observable<HttpResponse<boolean>> {
    return this.http.post<HttpResponse<boolean>>(global.API + API_URL + '/college', activationKeyToSave);
  }

  public createActivationKeyForCompany(activationKeyToSave: any): Observable<HttpResponse<boolean>> {
    return this.http.post<HttpResponse<boolean>>(global.API + API_URL + '/company', activationKeyToSave);
  }

  public createActivationKey(activationKeyToSave: any): Observable<HttpResponse<boolean>> {
    return this.http.post<HttpResponse<boolean>>(global.API + API_URL, activationKeyToSave );
  }

  public deleteActivationKeyForId(id: number): Observable<HttpResponse<boolean>> {
    return this.http.delete<HttpResponse<boolean>>(global.API + API_URL + '/' + id);
  }
}
