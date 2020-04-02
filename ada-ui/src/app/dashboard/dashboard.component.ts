import { Component, OnInit } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import { NewEntryService } from '../new-entry.service';
import { SnapshotStyleService } from '../snapshot-style.service';

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
  private selectedNode: string;
  private selectedEdge: string;
  private areZeroWeightedEdgesHidden = false;
  private areNeighbourlessNodesHidden = false;
  private areEdgeWeightsShownAsLabels = false;
  private areEdgesColourCoded = false;
  private selectedLayoutOption = 'circle';
  private resetGraphView = false;

  constructor(private analyserService: AnalyserService,
              private newEntryService: NewEntryService,
              private snapshotStyleService: SnapshotStyleService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    if (this.router.url == '/dashboard/current') {
      this.analyserService.isLoading = true;
      this.analyserService.doAnalysis().subscribe(dataJson => {
        this.updateProjectStructure(dataJson);
        let owner = dataJson['gitRepoInfo'].owner;
        let repository = dataJson['gitRepoInfo'].repository;
        let branch = dataJson['gitRepoInfo'].branch;
        let snapshot = dataJson['gitRepoInfo'].timestamp;
        this.sendNewEntry(owner, repository, branch, snapshot);
      });
    } else {
      this.route.paramMap.subscribe(
        (params: ParamMap) =>
          this.getPreviousAnalysis(params.get('owner'), params.get('repository'), params.get('branch'), params.get('snapshot'))
      );
    }
  }

  getPreviousAnalysis(owner: string, repository: string, branch: string, snapshot: string): void {
    this.analyserService.isLoading = true;
    this.analyserService.getPreviousAnalysis(owner, repository, branch, snapshot)
      .subscribe(dataJson => this.updateProjectStructure(dataJson))
  }

  updateSelectedMetric(newMetric: string): void {
    this.selectedMetric = newMetric;
  }

  private updateProjectStructure(data: JSON): void {
    this.analyserService.isLoading = false;
    this.projectStructure = new ProjectStructure(data);
  }

  sendNewEntry(owner: string, repository: string, branch: string, snapshot: string) {
    this.newEntryService.confirmNewEntry([owner, repository, branch, snapshot]);
    this.snapshotStyleService.sendClickedSnapshotToSidebar([owner, repository, branch, snapshot]);
  }

  updateAdditionalInformationWithNodeAdded($event: any) {
    this.selectedNode = $event;
  }

  updateAdditionalInformationWithEdgeAdded($event: any) {
    this.selectedEdge = $event;
  }

  updateAdditionalInformationWithNodeRemoved($event: any) {
    this.selectedNode = null;
  }

  updateAdditionalInformationWithEdgeRemoved($event: any) {
    this.selectedEdge = null;
  }

  handleUpdateZeroWeightedEdgesRepresentationEvent(state: boolean): void {
    this.areZeroWeightedEdgesHidden = state;
  }

  handleUpdateNeighbourlessNodesRepresentationEvent(state: boolean): void {
    this.areNeighbourlessNodesHidden = state;
  }

  handleUpdateEdgeWeightsAsLabelRepresentationEvent(state: boolean): void {
    this.areEdgeWeightsShownAsLabels = state;
  }

  handleUpdateEdgesColourCodingRepresentationEvent(state: boolean): void {
    this.areEdgesColourCoded = state;
  }

  handleUpdateSelectedLayoutOptionEvent($event: string): void {
    this.selectedLayoutOption = $event;
  }

  handleResetGraphViewEvent() {
    this.resetGraphView = !this.resetGraphView;
  }
}
