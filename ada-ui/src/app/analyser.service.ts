import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Snapshot} from "./classes/snapshot";
import { environment } from '../environments/environment';
import {ElementInsightService} from "./element-insight.service";

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

  public metrics: string[] = [
    'NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING',
    'NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING',
    'NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING',
    'NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING',
    'NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS'
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
