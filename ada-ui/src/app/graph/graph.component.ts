import {Component, OnInit, ViewChild} from '@angular/core';
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

  private projectStructure: ProjectStructure;
  private cyGraphData: any;

  private s = new sigma();

  private dragListener: any;

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    this.analyserService.getAnalysis()
      .pipe(
        tap(_ => console.log('tapped'))
      ).subscribe(data => this.handleRequestResponse(data));
  }

  private handleRequestResponse(data: JSON) {
    this.projectStructure = this.populateGraphData(data);
    this.initSigma();
    console.log(sigma.plugins == undefined ? 'UNDEFINED' : 'NOT UNDEFINED');
    console.log('HERE');
    this.dragListener = new sigma.plugins.dragNodes(this.s, this.s.renderers[0]);

  }

  private populateGraphData(data: JSON): ProjectStructure {
    return new ProjectStructure(data)
  }

  private initSigma(): void {
    // Initialize sigma:
    this.s.addRenderer({
      type: 'canvas',
      container: 'sigma-container'
    });
    this.s.settings({
      settings: {
        edgeLabelSize: 'proportional',
        minArrowSize: 7,
        edgeColor: 'red',
        defaultEdgeColor: 'red',
        nodeColor: 'red',
        defaultNodeColor: 'red',
        defaultEdgeType: 'curve'
      }
    });


    // this.addNodes(s);
    //
    // this.addEdges(s);

    var graph = {
      nodes: [
        { id: "n0", label: "A node", x: 0, y: 0, size: 3, color: '#008cc2' },
        { id: "n1", label: "Another node", x: 3, y: 1, size: 2, color: '#008cc2' },
        { id: "n2", label: "And a last one", x: 1, y: 3, size: 1, color: '#E57821' }
      ],
      edges: [
        { id: "e0", source: "n0", target: "n1", color: '#282c34', count:0, size:0.5 },
        { id: "e1", source: "n1", target: "n2", color: '#282c34', count:0, size:1},
        { id: "e2", source: "n2", target: "n0", color: '#FF0000', count:0, size:2},
        { id: "e3", source: "n2", target: "n1", color: '#282c34', count:0, size:2},
        { id: "e4", source: "n2", target: "n1", color: '#282c34', count:1, size:2},
        { id: "e5", source: "n2", target: "n2", color: '#282c34', count:0, size:2},
        { id: "e6", source: "n2", target: "n1", color: '#282c34', count:2, size:2}
      ]
    };

// load the graph
    this.s.graph.read(graph);
    // Refresh the sigma instance:
    this.s.refresh();



  }

  private addNodes(sigma: sigma): any {
    let i = 0;
    let classNames = this.projectStructure.classStructures.keys();
    for (let className of classNames) {
      sigma.graph.addNode({
        id: className,
        label: this.extractClassName(className),
        x: Math.random()*100,
        y: Math.random()*100,
        size: 1
      })
    }
  }

  private addEdges(sigma: sigma): any {
    let i = 0;
    let classNames = this.projectStructure.classStructures.keys();
    for (let className of classNames) {
      for (let correspondingClassName of this.projectStructure.classStructures.get(className).outgoingDependenceInfo.keys()) {
        sigma.graph.addEdge({
          id: className + '1->' + correspondingClassName,
          // Reference extremities:
          source: className,
          target: correspondingClassName,
          count: 0
        });
        // sigma.graph.addEdge({
        //   id: correspondingClassName + '2->' + className,
        //   // Reference extremities:
        //   source: correspondingClassName,
        //   target: className,
        //   count: 1
        // });
      }

      // for (let correspondingClassName of this.projectStructure.classStructures.get(className).incomingDependenceInfo.keys()) {
      //   sigma.graph.addEdge({
      //     id: correspondingClassName + '->' + className,
      //     // Reference extremities:
      //     source: correspondingClassName,
      //     target: className,
      //     count: 2
      //   });
      // }
    }


  }

  public extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    let className = (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);

    return className;
  }
}
