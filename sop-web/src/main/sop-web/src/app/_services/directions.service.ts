import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {TokenStorageService} from './auth/token-storage.service';
import {Observable} from 'rxjs';
import {Direction, IDirection} from '../_model/direction.model';
import global from '../../global';

@Injectable({
  providedIn: 'root'
})
export class DirectionsService {

  private DIRECTIONS_ENDPOINT = '/directions';
  private COLLEGE_DIRECTIONS_ENDPOINT = '/college-directions';


  constructor(private http: HttpClient, private token: TokenStorageService) {
  }

  public getDirectionForId(id: number): Observable<IDirection> {
    return this.http.get<IDirection>(global.API + this.DIRECTIONS_ENDPOINT + '/' + id);
  }

  public getAllDirections(): Observable<HttpResponse<IDirection[]>> {
    return this.http.get<HttpResponse<IDirection[]>>(global.API + this.COLLEGE_DIRECTIONS_ENDPOINT);
  }

  public createDirection(directionToSave: Direction) {
    return this.http.post(global.API + this.DIRECTIONS_ENDPOINT, directionToSave);
  }

  public deleteDirection(id: number) {
    return this.http.delete(global.API + this.DIRECTIONS_ENDPOINT + '/' + id);
  }
}
