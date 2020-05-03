import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from '../security/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  // private endpointURL = SERVER_API_URL + 'api/dictionaries';
  private endpointURL = 'api/users';

  constructor(private http: HttpClient) {
  }

  getAllUseres(): Observable<HttpResponse<IUser>> {
    return this.http.get<IUser>(this.endpointURL, {observe: 'response'});
  }

  updateUser(iDictionary: IUser): Observable<number> {
    return this.http.put<number>(this.endpointURL, iDictionary);
  }

  createUser(iUser: IUser): Observable<number> {
    return this.http.post<number>(this.endpointURL, iUser);
  }

  deleteUser(userId: number): Observable<HttpResponse<IUser>> {
    return this.http.delete<IUser>(this.endpointURL + '/' + userId, {observe: 'response'});
  }

  getTest(): Observable<HttpResponse<any>> {
    return this.http.get<any>(this.endpointURL + '/tests');
  }

}
