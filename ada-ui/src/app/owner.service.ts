import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private ownersUrl: string;
  private metaUrl: string;

  constructor(private http: HttpClient) { 
    this.ownersUrl = 'http://localhost:8080/users/names';
    this.metaUrl = 'http://localhost:8080/users/';
  }

  getOwnersList(): Observable<string[]> {
    return this.http.get<string[]>(this.ownersUrl);
  }

  getReposList(owner: string): Observable<string[]> {
    let reposUrl = this.metaUrl + owner + '/repositories'
    let params = new HttpParams()
      .set('owner', owner);
    return this.http.get<string[]>(reposUrl, {params});
  }
}
