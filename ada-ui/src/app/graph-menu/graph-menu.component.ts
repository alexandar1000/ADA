import {Component, ElementRef, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {GraphComponent} from "../graph/graph.component";
import {FormControl} from "@angular/forms";

interface GraphLayoutOption {
  value: string;
  viewValue: string;
}

interface GraphLayoutGroup {
  disabled?: boolean;
  name: string;
  graphLayoutOptions: GraphLayoutOption[];
}

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

  @Input() areZeroWeightedEdgesHidden: boolean;
  @Input() areNeighbourlessNodesHidden: boolean;
  @Input() areEdgeWeightsShownAsLabels: boolean;
  @Input() areEdgesColourCoded: boolean;
  @Input() selectedLayoutOption: string;

  @Output() updateZeroWeightedEdgesRepresentationEvent = new EventEmitter();
  @Output() updateNeighbourlessNodesRepresentationEvent = new EventEmitter();
  @Output() updateEdgeWeightsAsLabelRepresentationEvent = new EventEmitter();
  @Output() updateEdgesColourCodingRepresentationEvent = new EventEmitter();
  @Output() updateSelectedLayoutOptionEvent = new EventEmitter();

  graphLayoutControl = new FormControl();
  public graphLayoutGroups: GraphLayoutGroup[] = [
    {
      name: 'Ungrouped',
      graphLayoutOptions: [
        {value: 'circle', viewValue: 'Circle'},
        {value: 'grid', viewValue: 'Grid'},
        {value: 'random', viewValue: 'Random'},
      ]
    },
    {
      name: 'Grouped',
      graphLayoutOptions: [
        {value: 'concentric', viewValue: 'Doughnut'},
        {value: 'cose', viewValue: 'Cose'}
      ]
    }

    ];

  constructor() { }

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

  handleSelectedLayoutOptionChange($event: any): void {
    this.selectedLayoutOption = $event.value;
    this.updateSelectedLayoutOptionEvent.emit(this.selectedLayoutOption);
  }
}
