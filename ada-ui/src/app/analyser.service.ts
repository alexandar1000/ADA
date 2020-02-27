import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";
import {ProjectStructure1} from "./project-structure1";

@Injectable({
  providedIn: 'root'
})
export class AnalyserService {

  private repoFormUrl = 'http://localhost:8080/analyser';
  private analysis: Observable<ProjectStructure1>;

  constructor(private http: HttpClient) {
  }

  public doAnalysis(urlForm: string, branchName: string): void {
    let params = new HttpParams()
      .set('url', urlForm)
      .set('branch', branchName);

    this.analysis = this.http.post<ProjectStructure1>(this.repoFormUrl, params)
      .pipe(
        tap(_ => console.log('fetched analysis')),
        catchError(this.handleError<any>('doAnalysis', []))
      )
  }

  public getAnalysis(): Observable<ProjectStructure1> {
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
