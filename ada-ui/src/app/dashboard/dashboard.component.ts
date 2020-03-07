import { Component, OnInit } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {catchError, tap} from "rxjs/operators";
import {Snapshot} from "../classes/snapshot";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";

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
      console.log('Previous')
    }
  }

  updateSelectedMetric(newMetric: string): void {
    this.selectedMetric = newMetric;
  }

  public doAnalysis(): void {
    let params = new HttpParams()
      .set('url', this.analyserService.repoUrl)
      .set('branch', this.analyserService.repoBranch);

    this.http.post<JSON>(this.analyserService.analysisEndpointUrl, params)
      .pipe(
        tap(_ => console.log('fetched analysis'))
      )
      .subscribe(dataJson => this.handleRequestResponse(dataJson))
  }

  private handleRequestResponse(data: JSON) {
    this.projectStructure = this.populateProjectStructure(data);

  }

  private populateProjectStructure(data: JSON): ProjectStructure {
    return new ProjectStructure(data)
  }
}
