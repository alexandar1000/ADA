import { Component, OnInit } from '@angular/core';
import {AnalyserService} from "../analyser.service";
import {tap} from "rxjs/operators";
import { sigma } from 'sigma';
import {ProjectStructure} from "../classes/project-structure";

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  private graphData: ProjectStructure;

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    this.analyserService.getAnalysis()
      .pipe(
        tap(_ => console.log('tapped'))
      ).subscribe(data => this.handleRequestResponse(data));
  }

  private handleRequestResponse(data: ProjectStructure) {
    this.populateGraphData(data);
    this.populateGraph(data);
  }

  private populateGraphData(data: ProjectStructure) {
    this.graphData = new ProjectStructure(data.classStructures)
  }

  populateGraph(data: ProjectStructure) : void {
    // Initialize sigma:
    var s = new sigma('sigma-container');

    this.addNodes(s);

    this.addEdges(s);

    // Refresh the sigma instance:
    s.refresh();

  }

  private addNodes(sigma: sigma): any {
    // Then, let's add some data to display:
    let i = 0;
    for ( let element in this.graphData.classStructures) {
      // console.log(element);
      let lastIndex = element.lastIndexOf('.');
      let className = (lastIndex > 0 ? element.substr(lastIndex + 1, element.length - 1) : element);

      sigma.graph.addNode({
        id: element,
        label: className,
        x: i,
        y: i++,
        size: 1,
        color: '#foo'
      })
    }
  }

  private addEdges(sigma: sigma): any {
    // Then, let's add some data to display:
    let i = 0;
    for (let element in this.graphData.classStructures) {
      console.log();
    }


    // s.addEdge({
    //   id: 'e0',
    //   Reference extremities:
    // source: 'n0',
    // target: 'n1'
    // });
  }
}
