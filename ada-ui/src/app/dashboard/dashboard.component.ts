import { Component, OnInit } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {catchError, switchMap, tap} from "rxjs/operators";
import {Snapshot} from "../classes/snapshot";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {parseErrorsFromMarkup} from "tslint/lib/verify/parse";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private projectStructure: ProjectStructure;
  private snapshots = this.analyserService.snapshots;
  private metrics = this.analyserService.metrics;
  private selectedMetric = this.metrics[0];

  constructor(private analyserService: AnalyserService,
              private route: ActivatedRoute,
              private router: Router,
              private http: HttpClient) { }

  ngOnInit() {
    if (this.router.url == '/dashboard/current') {
      this.doAnalysis();
    } else {
      this.route.paramMap.subscribe(
        (params: ParamMap) =>
          this.fetchPreviousAnalysis(params.get('owner'), params.get('repository'), params.get('branch'), params.get('snapshot'))
      );

      //   .pipe(
      //   switchMap((params: ParamMap) =>
      //     this.fetchPreviousAnalysis(params.get('owner'), params.get('repository'), params.get('branch'), params.get('snapshot')))
      // );
    }
  }

  updateSelectedMetric(newMetric: string): void {
    this.selectedMetric = newMetric;
  }

  private doAnalysis(): void {
    let params = new HttpParams()
      .set('url', this.analyserService.repoUrl)
      .set('branch', this.analyserService.repoBranch);

    this.http.post<JSON>(this.analyserService.analysisEndpointUrl, params)
      .pipe(
        tap(_ => console.log('fetched analysis'))
      )
      .subscribe(dataJson => this.updateProjectStructure(dataJson));
  }

  private fetchPreviousAnalysis(owner: string, repository: string, branch: string, snapshot: string): void {
    let apiUrl = this.analyserService.buildFetchPreviousSnapshotAPIUrl(owner, repository, branch, snapshot);

    this.http.post<JSON>(apiUrl, new HttpParams())
      .pipe(
        tap(_ => console.log('fetched analysis'))
      )
      .subscribe(dataJson => this.updateProjectStructure(dataJson));
  }

  private updateProjectStructure(data: JSON) {
    this.projectStructure = new ProjectStructure(data);
  }
}
