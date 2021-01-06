import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {IDictionary} from '../_model/dictionary.model';
import global from '../../global';

const DICTIONARY_STUDY_MODES = '/dictionaries/study-modes';
const DICTIONARY_ACADEMIC_DEGREES = '/dictionaries/academic-degrees';
const DICTIONARY_SEX_TYPES = '/dictionaries/sex-types';
const DICTIONARY_REQUEST_TYPES = '/dictionaries/request-types';
const DICTIONARY_REQUEST_DECISION_STATUSES = '/dictionaries/request-decision-statuses';

@Injectable({
  providedIn: 'root'
})
export class DictionariesService {

  constructor(private httpClient: HttpClient) {
  }

  public getAcademicDegrees(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_ACADEMIC_DEGREES);
  }

  public getSexTypes(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_SEX_TYPES);
  }

  public getStudyModes(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_STUDY_MODES);
  }

  public getRequestTypes(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_REQUEST_TYPES);
  }

  public getRequestDecisionStatuses(): Observable<HttpResponse<IDictionary[]>> {
    return this.httpClient.get<HttpResponse<IDictionary[]>>(global.API + DICTIONARY_REQUEST_DECISION_STATUSES);
  }
}
