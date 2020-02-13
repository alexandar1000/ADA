import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "./classes/user";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AnalyserService {
  private repoFormUrl: 'http://localhost:8080/analyser';
  private analysis: Observable<any>;

  constructor(private http: HttpClient) {
  }

  public doAnalysis(urlForm: string, branchName: string): Observable<any> {
    let params = new HttpParams()
      .set('url', urlForm)
      .set('branch', branchName);
    return this.http.post<any>(this.repoFormUrl, params);
  }

  public getAnalysis(): Observable<any> {
    return this.analysis;
  }
}
