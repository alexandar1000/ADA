import {Component, OnInit} from '@angular/core';
import {AnalyserService} from "../analyser.service";
import {tap} from "rxjs/operators";
import {ProjectStructure} from "../classes/project-structure";
import * as cytoscape from 'cytoscape';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  private projectStructure: ProjectStructure;

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    this.analyserService.getAnalysis()
      .pipe(
        tap(_ => console.log('tapped'))
      ).subscribe(data => this.handleRequestResponse(data));
  }

  private handleRequestResponse(data: JSON) {
    this.projectStructure = this.populateGraphData(data);
    this.initCytoscape();
  }

  private populateGraphData(data: JSON): ProjectStructure {
    return new ProjectStructure(data)
  }


  private initCytoscape() {
    let cy = cytoscape(
      {
        container: document.getElementById('cytoscape-container'),
        elements: [
          { // node a
            data: {id: 'a'}
          },
          { // node b
            data: {id: 'b'}
          },
          { // edge ab
            data: {id: 'ab0', source: 'a', target: 'b'}
          },
          { // edge ab
            data: {id: 'ab1', source: 'a', target: 'b'}
          }
        ],
        style: [
          {
            selector: 'edge',
            style: {
              'curve-style': 'bezier'
            }
          }
        ]
      });
  }


  // private addNodes(sigma: sigma): any {
    // let i = 0;
    // let classNames = this.projectStructure.classStructures.keys();
    // for (let className of classNames) {
    //   sigma.graph.addNode({
    //     id: className,
    //     label: this.extractClassName(className),
    //     x: Math.random()*100,
    //     y: Math.random()*100,
    //     size: 1
    //   })
    // }
  // }

  // private addEdges(sigma: sigma): any {
    // let i = 0;
    // let classNames = this.projectStructure.classStructures.keys();
    // for (let className of classNames) {
    //   for (let correspondingClassName of this.projectStructure.classStructures.get(className).outgoingDependenceInfo.keys()) {
    //     sigma.graph.addEdge({
    //       id: className + '1->' + correspondingClassName,
    //       // Reference extremities:
    //       source: className,
    //       target: correspondingClassName,
    //       count: 0
    //     });
      // }
    // }
  // }

  public extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    let className = (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);

    return className;
  }


}
