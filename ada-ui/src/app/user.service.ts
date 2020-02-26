import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private repoFormUrl: string;

  constructor(private http: HttpClient) { 
    this.repoFormUrl = 'http://localhost:8080/analyser';
  }

  public sendRepoForm(urlForm: string, branchName: string): Observable<any> {
    let params = new HttpParams()
      .set('url', urlForm)
      .set('branch', branchName);
    return this.http.post<any>(this.repoFormUrl, params);
  }
}
