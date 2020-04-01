import {Component, ElementRef, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {GraphComponent} from "../graph/graph.component";
import { QueryService } from '../query.service';

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {

  private canvas: ElementRef<HTMLCanvasElement>;
  @ViewChild('colourcodingLegend', { static: false }) public set content(content: ElementRef<HTMLCanvasElement>) {
    this.canvas = content;
    if (this.canvas != undefined) {
      this.createColourcodingLegend();
    }
  };

  private ctx: CanvasRenderingContext2D;
  // private query: string[];

  @Input() areZeroWeightedEdgesHidden: boolean;
  @Input() areNeighbourlessNodesHidden: boolean;
  @Input() areEdgeWeightsShownAsLabels: boolean;
  @Input() areEdgesColourCoded: boolean;

  @Output() updateZeroWeightedEdgesRepresentationEvent = new EventEmitter();
  @Output() updateNeighbourlessNodesRepresentationEvent = new EventEmitter();
  @Output() updateEdgeWeightsAsLabelRepresentationEvent = new EventEmitter();
  @Output() updateEdgesColourCodingRepresentationEvent = new EventEmitter();

  constructor() {}

  ngOnInit() {
  }

  createColourcodingLegend(): void {
    const canvasElement = this.canvas.nativeElement;
    this.ctx = canvasElement.getContext('2d');
    // Create gradient
    var grd = this.ctx.createLinearGradient(0, 0, 200, 0);
    for (let i = 0; i < GraphComponent.gradients.length; i++) {
      grd.addColorStop(i*(1/12), GraphComponent.gradients[i]);
    }

    // Fill with gradient
    this.ctx.fillStyle = grd;
    this.ctx.fillRect(0, 0, 200, 20);
  }

  handleZeroWeightedEdgesRepresentationChange($event: any): void {
    this.areZeroWeightedEdgesHidden = $event.checked;
    this.updateZeroWeightedEdgesRepresentationEvent.emit(this.areZeroWeightedEdgesHidden);
  }

  handleNodesWithoutNeighboursRepresentationChange($event: any): void {
    this.areNeighbourlessNodesHidden = $event.checked;
    this.updateNeighbourlessNodesRepresentationEvent.emit(this.areNeighbourlessNodesHidden);
  }

  handleEdgeWeightsAsLabelRepresentationChange($event: any): void {
    this.areEdgeWeightsShownAsLabels = $event.checked;
    this.updateEdgeWeightsAsLabelRepresentationEvent.emit(this.areEdgeWeightsShownAsLabels);
  }

  handleEdgesColourCodingRepresentationChange($event: any): void {
    this.areEdgesColourCoded = $event.checked;
    this.updateEdgesColourCodingRepresentationEvent.emit(this.areEdgesColourCoded);
  }
}
