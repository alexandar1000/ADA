import { Component, OnInit } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {tap} from "rxjs/operators";
import {GraphComponent} from "../graph/graph.component";
import {Snapshot} from "../classes/snapshot";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private projectStructure: ProjectStructure;
  private snapshots: Snapshot[] = [
    new Snapshot(78, '2020-03-01 15:14'),
    new Snapshot(12, '2019-09-21 13:00'),
    new Snapshot(0, '2019-07-28 01:02')
    ];
  private metrics = [
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
  private selectedMetric = this.metrics[0];

  constructor(private analyserService: AnalyserService,
              private route: ActivatedRoute,
              private router: Router,) { }

  ngOnInit() {
    if (this.router.url == '/dashboard/current') {
      this.analyserService.getAnalysis()
        .pipe(
          tap(_ => console.log('tapped'))
        ).subscribe(data => this.handleRequestResponse(data));
    } else {
      console.log('Previous')
    }
  }

  private handleRequestResponse(data: JSON) {
    this.projectStructure = this.populateProjectStructure(data);

  }

  private populateProjectStructure(data: JSON): ProjectStructure {
    return new ProjectStructure(data)
  }

  updateSelectedMetric(newMetric: string): void {
    this.selectedMetric = newMetric;
  }

}
