import {Component, ElementRef, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {GraphComponent} from "../graph/graph.component";
import {FormControl} from "@angular/forms";
import {GraphOptionsService} from "../graph-options.service";

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

  private graphLayoutSpacing: number;
  private areZeroWeightedEdgesHidden: boolean;
  private areNeighbourlessNodesHidden: boolean;
  private areEdgeWeightsShownAsLabels: boolean;
  private areEdgesColourCoded: boolean;
  private selectedLayoutOption: string;


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

  constructor(private graphOptionsService: GraphOptionsService) {}

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
    this.graphOptionsService.setAreZeroWeightedEdgesHidden($event.checked);
    this.areZeroWeightedEdgesHidden = $event.checked;
  }

  handleNodesWithoutNeighboursRepresentationChange($event: any): void {
    this.graphOptionsService.setAreNeighbourlessNodesHidden($event.checked);
    this.areNeighbourlessNodesHidden = $event.checked;
  }

  handleEdgeWeightsAsLabelRepresentationChange($event: any): void {
    this.graphOptionsService.setAreEdgeWeightsShownAsLabels($event.checked);
    this.areEdgeWeightsShownAsLabels = $event.checked;
  }

  handleEdgesColourCodingRepresentationChange($event: any): void {
    this.graphOptionsService.setAreEdgesColourCoded($event.checked);
    this.areEdgesColourCoded = $event.checked;
  }

  handleSelectedLayoutOptionChange($event: any): void {
    this.graphOptionsService.setSelectedLayoutOption($event.value);
    this.selectedLayoutOption = $event.value;
  }

  handleResetGraphViewButtonPressed($event: MouseEvent) {
    this.graphOptionsService.setIsGraphViewToBeReset(true);
  }

  handleLayoutSpacingChange($event: any): void {
    this.graphOptionsService.setSpacingFactor($event.value/10);
  }

  formatSliderLabel(value: number) {
    return (value/10).toFixed(1);
  }

}
