import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Snapshot} from "./classes/snapshot";
import { environment } from '../environments/environment';
import {ElementInsightService} from "./element-insight.service";
import {MetricNameConverter} from "./classes/metric-name-converter";
import {CountdownConfig} from "ngx-countdown";

@Injectable({
  providedIn: 'root'
})
export class AnalyserService {

  public analysisEndpointUrl = environment.backendBaseUrl + '/analyser';
  public repoUrl: string;
  public repoBranch: string;
  public isLoading: boolean = false;

  public snapshots: Snapshot[] = [
    new Snapshot(78, '2020-03-01 15:14'),
    new Snapshot(12, '2019-09-21 13:00'),
    new Snapshot(0, '2019-07-28 01:02')
  ];

  constructor(private http: HttpClient, private elementInsightService: ElementInsightService) {}

  public buildFetchPreviousSnapshotAPIUrl(owner: string, repository: string, branch: string, snapshot: string): string {
    return environment.backendBaseUrl + '/owners/' + owner +
      '/repositories/' + repository +
      '/branches/' + branch +
      '/snapshots/' + snapshot +
      '/project-structure';
  }

  public doAnalysis(): Observable<JSON> {
    let params = new HttpParams()
      .set('url', this.repoUrl)
      .set('branch', this.repoBranch);

    this.elementInsightService.clearSelectedNodes();
    this.elementInsightService.clearSelectedEdges();
    return this.http.post<JSON>(this.analysisEndpointUrl, params);
  }

  public getPreviousAnalysis(owner: string, repository: string, branch: string, snapshot: string): Observable<JSON> {
    let apiUrl = this.buildFetchPreviousSnapshotAPIUrl(owner, repository, branch, snapshot);
    this.elementInsightService.clearSelectedNodes();
    this.elementInsightService.clearSelectedEdges();
    return this.http.post<JSON>(apiUrl, new HttpParams());
  }
}
