import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  private ownersUrl: string;
  private metaUrl: string;

  constructor(private http: HttpClient) {
    this.ownersUrl = environment.backendBaseUrl + '/owners/names';
    this.metaUrl = environment.backendBaseUrl + '/owners/';
  }

  getOwnersList(): Observable<string[]> {
    return this.http.post<string[]>(this.ownersUrl, new HttpParams());
  }

  getReposList(owner: string): Observable<any> {
    let reposUrl = this.metaUrl + owner + '/repositories';
    let params = new HttpParams()
      .set('owner', owner);
    return this.http.post<any>(reposUrl, {params});
  }

  getBranchesList(owner: string, repository: string): Observable<any> {
    let branchesUrl = this.metaUrl + owner + '/repositories/' + repository + '/branches';
    let params = new HttpParams()
      .set('owner', owner)
      .set('repository', repository);
    return this.http.post<any>(branchesUrl, {params});
  }

  getSnapshotsList(owner: string, repository: string, branch:string): Observable<any> {
    let snapshotsUrl = this.metaUrl + owner + '/repositories/' + repository + '/branches/' + branch + '/snapshots';
    let params = new HttpParams()
      .set('owner', owner)
      .set('repository', repository)
      .set('branch', branch);
    return this.http.post<any>(snapshotsUrl, {params});
  }

  addNewEntry(owner: string, repository: string, branch: string) {

  }
}
