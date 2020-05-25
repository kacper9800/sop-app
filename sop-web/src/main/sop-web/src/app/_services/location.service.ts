import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { IEvent } from '../_model/event.model';
import { ILocation } from '../_model/location.model';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  API_URL = 'http://localhost:8082/api/locations/';

  constructor(private http: HttpClient) {
  }

  public getAllLocations(): Observable<any> {
    return this.http.get<Location[]>(this.API_URL);
  }

  public createNewLocation(location: ILocation): Observable<number> {
    return this.http.post<number>(this.API_URL, location);
  }

  public deleteEvent(id: number): Observable<any> {
    return this.http.delete<any>(this.API_URL + id);
  }
}
