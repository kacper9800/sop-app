import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {IDocument} from '../_model/document.model';
import global from '../../global';

const API_URL = '/documents';

@Injectable({
  providedIn: 'root'
})
export class DocumentsService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllDocuments(): Observable<HttpResponse<IDocument[]>> {
    return this.httpClient.get<HttpResponse<IDocument[]>>(global.API + API_URL);
  }
}
