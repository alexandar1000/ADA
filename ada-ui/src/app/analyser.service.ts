import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AnalyserService {

  private repoFormUrl = 'http://localhost:8080/analyser';
  private analysis: Observable<any>;

  constructor(private http: HttpClient) {
  }

  public doAnalysis(urlForm: string, branchName: string): void {
    let params = new HttpParams()
      .set('url', urlForm)
      .set('branch', branchName);

    this.analysis = this.http.post<any>(this.repoFormUrl, params)
      .pipe(
        tap(_ => console.log('fetched analysis')),
        catchError(this.handleError<any>('doAnalysis', []))
      )
  }

  public getAnalysis(): Observable<any> {
    return this.analysis;
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
