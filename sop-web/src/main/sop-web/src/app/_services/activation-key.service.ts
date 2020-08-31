import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ActivationKey, IActivationKey} from '../_model/activation-key.model';

@Injectable({
  providedIn: 'root'
})
export class ActivationKeyService {
  API_URL = 'http://localhost:8082/api/activationKeys';

  constructor(private http: HttpClient) {
  }

  public getAllActivationKeysForCompany(): Observable<HttpResponse<IActivationKey[]>> {
    return this.http.get<HttpResponse<ActivationKey[]>>(this.API_URL);
  }

  public getActivationKeyForId(activationKeyId: number): Observable<IActivationKey> {
    return this.http.get<ActivationKey>(this.API_URL + '/' + activationKeyId);
  }

  public getActivationKeyForValue(value: string): Observable<IActivationKey> {
    return this.http.get<ActivationKey>(this.API_URL, {params: { keyValue: value }});
  }
}
