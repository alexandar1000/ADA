import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import * as cytoscape from 'cytoscape';
import {MetricNameConverter} from "../classes/metric-name-converter";

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  private cy;
  @Input() projectStructure;
  @Input() selectedMetric;
  private metricNameConverter = new MetricNameConverter();

  constructor() { }

  ngOnInit() {
    this.initCytoscape();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.projectStructure && !changes.projectStructure.firstChange) {
      this.repopulateGraph();
    }
    if (changes.selectedMetric && !changes.selectedMetric.firstChange) {
      this.changeMetricRepresentedInGraph(this.selectedMetric);
    }
  }

  private initCytoscape(): void {
    let elements: any = {};
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
              'target-arrow-shape': 'triangle',
              label: 'data(weight)'
            }
          }
        ]
      });
  }

  private repopulateGraph(): void {
    this.cy.elements().remove();
    let elements = this.getElements();
    this.cy.add( elements );

    var layout = this.cy.layout({
      name: 'circle'
    });

    layout.run();

  }

  public extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    let className = (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);

    return className;
  }


  private getElements(): any {
    let elements = {
      nodes: this.extractNodes(),
      edges: this.extractEdges()
    };
    return elements;
  }

  public extractNodes() : any {
    let nodes = [];
    // The node names will be the names in the classStructures Map of the ProjectStructure, as it contains all of the
    // nodes of the graph
    let fullyQualifiedClassNames = this.projectStructure.classStructures.keys();
    for (let fullyQualifiedClassName of fullyQualifiedClassNames) {
      // Extract specifically the class name in order to display it and not clutter the screen, but use the fully
      // qualified class name as the id as it is unique.
      let extractedClassName = this.extractClassName(fullyQualifiedClassName);
      // If the class name is empty string, then replace it with a $ symbol
      fullyQualifiedClassName = (fullyQualifiedClassName == '' ? "$" : fullyQualifiedClassName);
      extractedClassName = (extractedClassName == '' ? '$' : extractedClassName);
      // Create the node as per cytoscape representation
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

  public extractEdges() : any {
    let edges = [];
    let i = 0;
    // The edges will be drawn between all of the classes which have entries in the outgoingDependencies Map, as it will
    // contain all the invocations which the node makes.
    let fullyQualifiedClassNames = this.projectStructure.classStructures.keys();
    for (let fullyQualifiedClassName of fullyQualifiedClassNames) {
      for (let correspondingFullyQualifiedClassName of this.projectStructure.classStructures.get(fullyQualifiedClassName).outgoingDependenceInfo.keys()) {
        let weight = this.projectStructure.classStructures.get(fullyQualifiedClassName).relationMetricValues.get(correspondingFullyQualifiedClassName)[this.metricNameConverter.translateMetricName(this.selectedMetric)];
        // Create the edge as per cytoscape representation
        let edge = {
          data: {
            id: i++,
            source: (fullyQualifiedClassName == '' ? "$" : fullyQualifiedClassName),
            target: (correspondingFullyQualifiedClassName == '' ? "$" : correspondingFullyQualifiedClassName),
            weight: weight
          }
        };
        edges.push(edge);
      }
    }
    return edges;
  }

  private getCorrespondingWeight(source: String, target: String): number {
    if (source == '$') {
      source = '';
    }
    if (target == '$') {
      target = '';
    }
    let weight = this.projectStructure.classStructures.get(source).relationMetricValues.get(target)[this.metricNameConverter.translateMetricName(this.selectedMetric)];
    return weight;
  }

  private changeMetricRepresentedInGraph(selectedMetric: String) {
    let newMetric = this.metricNameConverter.translateMetricName(selectedMetric.toString());
    let self = this;
    if (newMetric != null) {
      this.cy.startBatch();

      this.cy.edges().forEach(function( edge ){
        let source = edge.data('source');
        let target = edge.data('target');
        edge.data('weight', self.getCorrespondingWeight(source, target));
      });

      this.cy.endBatch();
    } else {
      console.error('Metric name is not in the translator.');
    }
  }
}
