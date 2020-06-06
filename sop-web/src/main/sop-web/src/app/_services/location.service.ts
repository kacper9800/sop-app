import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ILocation } from '../_model/location.model';
import global from '../../global';

const LOCATIONS_API = '/locations';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  API_URL = 'http://localhost:8082/api/locations/';

  constructor(private http: HttpClient) {
    console.log(global.API);
  }

  public getAllLocations(): Observable<any> {
    return this.http.get<Location[]>(global.API + LOCATIONS_API);
  }

  public createNewLocation(location: ILocation): Observable<number> {
    return this.http.post<number>(this.API_URL, location);
  }

  public deleteEvent(id: number): Observable<any> {
    return this.http.delete<any>(this.API_URL + id);
  }
}
