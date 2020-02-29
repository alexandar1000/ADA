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
    let elements = this.getElements();
    let cy = cytoscape(
      {
        container: document.getElementById('cytoscape-container'),
        elements: elements,
        style: [
          {
            selector: '*',
            style: {
            }
          },
          {
            selector: 'node',
            style: {
              label: 'data(label)'
            }
          },
          {
            selector: 'edge',
            style: {
              'curve-style': 'bezier',
              'target-arrow-shape': 'triangle'
            }
          }
        ]
      });
  }

  public extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    let className = (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);

    return className;
  }


  private getElements(): any {
    let elements = {
      nodes: this.getNodes(),
      edges: this.getEdges()
    };
    return elements;
  }

  private getNodes() : any {
    let nodes = [];
    let classNames = this.projectStructure.classStructures.keys();
    for (let className of classNames) {
      let node = {
        data: {
          id: className,
          label: this.extractClassName(className)
        }
      };
      nodes.push(node);
    }
    return nodes;
  }

  private getEdges() : any {
    let edges = [];
    let classNames = this.projectStructure.classStructures.keys();
    for (let className of classNames) {
      for (let correspondingClassName of this.projectStructure.classStructures.get(className).outgoingDependenceInfo.keys()) {
        let edge = {
          data: {
            id: className + 'To' + correspondingClassName,
            source: className,
            target: correspondingClassName
          }
        };
        edges.push(edge);
      }
    }
    return edges;
  }
}
