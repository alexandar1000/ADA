import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import { NewEntryService } from '../new-entry.service';

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
              private newEntryService: NewEntryService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    if (this.router.url == '/dashboard/current') {
      this.analyserService.doAnalysis().subscribe(dataJson => {
        this.updateProjectStructure(dataJson)
        let owner = dataJson['gitRepoInfo'].owner;
        let repository = dataJson['gitRepoInfo'].repository;
        let branch = dataJson['gitRepoInfo'].branch;
        let snapshot = dataJson['gitRepoInfo'].timestamp;
        this.sendNewEntry(owner, repository, branch, snapshot);
      });
    } else {
      this.route.paramMap.subscribe(
        (params: ParamMap) =>
          this.analyserService.getPreviousAnalysis(params.get('owner'), params.get('repository'), params.get('branch'), params.get('snapshot'))
            .subscribe(dataJson => this.updateProjectStructure(dataJson))
      );
    }
  }

  updateSelectedMetric(newMetric: string): void {
    this.selectedMetric = newMetric;
  }

  private updateProjectStructure(data: JSON) {
    this.projectStructure = new ProjectStructure(data);
  }

  sendNewEntry(owner: string, repository: string, branch: string, snapshot: string) {
    this.newEntryService.confirmNewEntry([owner, repository, branch, snapshot]);
  }
}
