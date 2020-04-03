import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GraphComponent} from "../graph/graph.component";
import {FormControl} from "@angular/forms";
import {GraphOptionsService} from "../graph-options.service";

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
  public graphLayoutGroups = null;

  constructor(private graphOptionsService: GraphOptionsService) {}

  ngOnInit() {
    this.graphLayoutSpacing = this.graphOptionsService.spacingFactor.value;
    this.areZeroWeightedEdgesHidden = this.graphOptionsService.areZeroWeightedEdgesHidden.value;
    this.areNeighbourlessNodesHidden = this.graphOptionsService.areNeighbourlessNodesHidden.value;
    this.areEdgeWeightsShownAsLabels = this.graphOptionsService.areEdgeWeightsShownAsLabels.value;
    this.areEdgesColourCoded = this.graphOptionsService.areEdgesColourCoded.value;
    this.selectedLayoutOption = this.graphOptionsService.selectedLayoutOption.value;
    this.graphLayoutGroups = this.graphOptionsService.graphLayoutGroups;
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
  }

  handleNodesWithoutNeighboursRepresentationChange($event: any): void {
    this.graphOptionsService.setAreNeighbourlessNodesHidden($event.checked);
  }

  handleEdgeWeightsAsLabelRepresentationChange($event: any): void {
    this.graphOptionsService.setAreEdgeWeightsShownAsLabels($event.checked);
  }

  handleEdgesColourCodingRepresentationChange($event: any): void {
    this.graphOptionsService.setAreEdgesColourCoded($event.checked);
  }

  handleSelectedLayoutOptionChange($event: any): void {
    this.graphOptionsService.setSelectedLayoutOption($event.value);
  }

  handleResetGraphViewButtonPressed($event: MouseEvent) {
    this.graphOptionsService.setIsGraphViewToBeReset(true);
  }

  handleResetGraphLayoutButtonPressed($event: MouseEvent) {
    this.graphOptionsService.setIsGraphLayoutToBeReset(true);
  }

  handleLayoutSpacingChange($event: any): void {
    this.graphOptionsService.setSpacingFactor($event.value/10);
  }

  formatSliderLabel(value: number) {
    return (value/10).toFixed(1);
  }
}
