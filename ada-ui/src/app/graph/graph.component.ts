import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {AnalyserService} from "../analyser.service";
import {ProjectStructure} from "../classes/project-structure";
import * as cytoscape from 'cytoscape';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  private cy;
  @Input() nodes;
  @Input() edges;

  constructor() { }

  ngOnInit() {
    this.initCytoscape();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.refreshGraph();
  }

  private initCytoscape(): void {
    let elements = this.getElements();
    console.log(elements);
    this.cy = cytoscape(
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

  private refreshGraph(): void {
    this.cy.elements().remove();
    let elements = this.getElements();
    this.cy.add( elements );

    var layout = this.cy.layout({
      name: 'circle'
    });

    layout.run();

  }

  public static extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    let className = (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);

    return className;
  }


  private getElements(): any {
    let elements = {
      nodes: this.nodes,
      edges: this.edges
    };
    // console.log(elements);
    return elements;
  }

  public static extractNodes(projectStructure: ProjectStructure) : any {
    let nodes = [];
    let fullyQualifiedClassNames = projectStructure.classStructures.keys();
    for (let fullyQualifiedClassName of fullyQualifiedClassNames) {
      let extractedClassName = this.extractClassName(fullyQualifiedClassName);
      fullyQualifiedClassName = (fullyQualifiedClassName == '' ? "$" : fullyQualifiedClassName);
      extractedClassName = (extractedClassName == '' ? '$' : extractedClassName);
      let node = {
        data: {
          id: fullyQualifiedClassName,
          label: extractedClassName
        }
      };
      nodes.push(node);
    }
    return nodes;
  }

  public static extractEdges(projectStructure: ProjectStructure) : any {
    let edges = [];
    let i = 0;
    let fullyQualifiedClassNames = projectStructure.classStructures.keys();
    for (let fullyQualifiedClassName of fullyQualifiedClassNames) {
      for (let correspondingFullyQualifiedClassNames of projectStructure.classStructures.get(fullyQualifiedClassName).outgoingDependenceInfo.keys()) {
        let edge = {
          data: {
            id: i++,
            source: (fullyQualifiedClassName == '' ? "$" : fullyQualifiedClassName),
            target: (correspondingFullyQualifiedClassNames == '' ? "$" : correspondingFullyQualifiedClassNames)
          }
        };
        edges.push(edge);
      }
    }
    return edges;
  }
}
