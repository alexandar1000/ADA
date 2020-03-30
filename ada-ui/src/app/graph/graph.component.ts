import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import * as cytoscape from 'cytoscape';
import {MetricNameConverter} from "../classes/metric-name-converter";
import {ProjectStructure} from "../classes/project-structure";

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  private cy = null;
  @Input() projectStructure: ProjectStructure;
  @Input() selectedMetric: string;
  @Input() hideZeroEdges = false;
  @Input() highlightNeighbours = true;
  private metricNameConverter = new MetricNameConverter();


  constructor() { }

  ngOnInit() {
    this.initCytoscape();
    this.initEventHandlers();
    this.repopulateGraph();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.selectedMetric && !changes.selectedMetric.firstChange) {
      this.changeMetricRepresentedInGraph();
    }
    if (changes.hideZeroEdges && this.cy != null) {
      this.updateDisplayOfZeroEdges(this.hideZeroEdges);
      // this.changeMetricRepresentedInGraph();
    }
    if (changes.highlightNeighbours) {
      // this.changeMetricRepresentedInGraph();
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
          },
          {
            selector: 'node.highlight',
            style: {
              'border-color': '#ff3b00',
              'border-width': '5px'
            }
          },
          {
            selector: 'node.unhighlight',
            style: {
              'opacity': 0.4
            }
          },
          {
            selector: 'edge.highlight',
            style: {
              'line-color': '#ff3b00'
            }
          },
          {
            selector: 'edge.unhighlight',
            style: {
              'opacity': 0.2
            }
          }
        ]
      });
  }

  private repopulateGraph(): void {
    this.cy.elements().remove();
    let elements = this.getElements();
    this.cy.add(elements);

    this.updateArrowStyle();

    this.updateDisplayOfZeroEdges(this.hideZeroEdges);

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

  private changeMetricRepresentedInGraph() {
    let newMetric = this.metricNameConverter.translateMetricName(this.selectedMetric.toString());

    let self = this;
    if (newMetric != null) {
      this.cy.batch(function(){
        self.cy.edges().forEach(function( edge ){
          let source = edge.data('source');
          let target = edge.data('target');
          edge.data('weight', self.getCorrespondingWeight(source, target));
        });
        self.updateArrowStyle();
      });
    } else {
      console.error('Metric name is not in the translator.');
    }
    this.updateDisplayOfZeroEdges(this.hideZeroEdges);
  }

  private updateArrowStyle (): void {
    let arrowStyle = this.metricNameConverter.getArrowStyle(this.selectedMetric.toString());
    let arrowStyleKey = arrowStyle[0];
    let arrowStyleValue = arrowStyle[1];

    if (arrowStyleKey == 'source-arrow-shape') {
      this.cy.style()
        .selector('edge')
        .style({
          'target-arrow-shape': 'none',
          'source-arrow-shape': arrowStyleValue
        })
        .update();
    } else {
      this.cy.style()
        .selector('edge')
        .style({
          'source-arrow-shape': 'none',
          'target-arrow-shape': arrowStyleValue
        })
        .update();
    }
  }

  private updateDisplayOfZeroEdges(hideEdges: boolean): void {
    let self = this;
    this.cy.batch(function() {
      // Show only edges with a weight other than zero
      if (hideEdges == true) {
        self.cy.edges().forEach(function (edge) {
          let weight = edge.data('weight');
          if (weight != 0) {
            edge.style({
              'display': 'element'
            })
          } else {
            edge.style({
              'display': 'none'
            })
          }
        });
      // Show all edges
      } else {
        self.cy.edges().forEach(function (edge) {
          edge.style(
            {
              'display': 'element'
            })
        });
      }
    });
  }

  private highlightNodeNeighbourhood(node: any): void {
    let neighbourhood = node.neighborhood();
    this.cy.batch(function(){
      neighbourhood.addClass('highlight');
    });
  }

  private unhighlightNodeNeighbourhood(node: any): void {
    let neighbourhood = node.neighborhood();
    this.cy.batch(function(){
      neighbourhood.removeClass('highlight');
    });
  }

  private highlightEdgeNeighbourhood(edge: any): void {
    let connectedNodes = edge.connectedNodes();
    this.cy.batch(function(){
      edge.addClass('highlight');
      connectedNodes.addClass('highlight');
    });
  }

  private unhighlightEdgeNeighbourhood(edge: any): void {
    let connectedNodes = edge.connectedNodes();
    this.cy.batch(function(){
      edge.removeClass('highlight');
      connectedNodes.removeClass('highlight');
    });
  }

  private handleSelectNode() : void {
    let self = this;
    this.cy.on('select', 'node', function(evt){
      self.highlightNodeNeighbourhood(evt.target);
    });
  }

  private handleUnselectNode() : void {
    let self = this;
    this.cy.on('unselect', 'node', function(evt){
      self.unhighlightNodeNeighbourhood(evt.target);
    });
  }

  private handleSelectEdge() : void {
    let self = this;
    this.cy.on('select', 'edge', function(evt){
      self.highlightEdgeNeighbourhood(evt.target);
    });
  }

  private handleUnselectEdge() : void {
    let self = this;
    this.cy.on('unselect', 'edge', function(evt){
      self.unhighlightEdgeNeighbourhood(evt.target);
    });
  }

  private initEventHandlers(): void {
    this.handleSelectNode();
    this.handleUnselectNode();
    this.handleSelectEdge();
    this.handleUnselectEdge();
  }
}
