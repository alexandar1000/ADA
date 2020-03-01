import { Component, OnInit } from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {tap} from "rxjs/operators";
import {GraphComponent} from "../graph/graph.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private projectStructure: ProjectStructure;
  nodes: any = {};
  edges: any = {};

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    this.analyserService.getAnalysis()
      .pipe(
        tap(_ => console.log('tapped'))
      ).subscribe(data => this.handleRequestResponse(data));
  }

  private handleRequestResponse(data: JSON) {
    this.projectStructure = this.populateProjectStructure(data);
    this.nodes = GraphComponent.extractNodes(this.projectStructure);
    this.edges = GraphComponent.extractEdges(this.projectStructure);

  }

  private populateProjectStructure(data: JSON): ProjectStructure {
    return new ProjectStructure(data)
  }

}
