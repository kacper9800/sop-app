import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {TokenStorageService} from './auth/token-storage.service';
import {Observable} from 'rxjs';
import {Direction, IDirection} from '../_model/direction.model';

@Injectable({
  providedIn: 'root'
})
export class DirectionsService {

  private DIRECTIONS_ENDPOINT = 'api/directions';
  private COLLEGE_DIRECTIONS_ENDPOINT = 'api/college-directions';


  constructor(private http: HttpClient, private token: TokenStorageService) {
  }

  public getAllDirections(id: number): Observable<HttpResponse<IDirection[]>> {
    return this.http.get<IDirection[]>(this.COLLEGE_DIRECTIONS_ENDPOINT + '/' + id, {observe: 'response'});
  }

  public createDirection(directionToSave: Direction) {
    return this.http.post(this.DIRECTIONS_ENDPOINT, directionToSave);
  }
}
