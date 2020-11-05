import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Dictionary, IDictionary} from '../_model/dictionary.model';
import global from '../../global';

const DICTIONARY_STUDY_MODES = '/dictionaries/study-modes';

@Injectable({
  providedIn: 'root'
})
export class DictionariesService {

  constructor(private httpClient: HttpClient) {
  }

  public getStudyModes(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_STUDY_MODES);
  }
}
