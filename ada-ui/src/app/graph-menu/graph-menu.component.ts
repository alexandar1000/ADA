import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GraphComponent} from "../graph/graph.component";
import {FormControl, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {GraphOptionsService} from "../graph-options.service";
import {ErrorStateMatcher} from "@angular/material/core";
import {AnalyserService} from "../analyser.service";
import {MetricNameConverter} from "../classes/metric-name-converter";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {
  matcher = new MyErrorStateMatcher();

  private canvas: ElementRef<HTMLCanvasElement>;

  @ViewChild('colourcodingLegend', { static: false }) public set content(content: ElementRef<HTMLCanvasElement>) {
    this.canvas = content;
    if (this.canvas != undefined) {
      this.createColourcodingLegend();
    }
  };
  private ctx: CanvasRenderingContext2D;

  graphLayoutSpacing: number;
  areEdgesBellowWeightThresholdHidden: boolean;
  areNeighbourlessNodesHidden: boolean;
  areEdgeWeightsShownAsLabels: boolean;
  areEdgesColourCoded: boolean;
  selectedLayoutOption: string;
  graphEdgeWeightThreshold: number;
  weightThresholdFormControl: any;

  selectedMetric: string;
  graphMetricMenuItems = new MetricNameConverter().metricOptions;


  graphLayoutControl = new FormControl();
  metricLayoutControl = new FormControl();
  public graphLayoutGroups = null;

  constructor(public graphOptionsService: GraphOptionsService) {}

  ngOnInit() {
    this.graphLayoutSpacing = this.graphOptionsService.spacingFactor;
    this.areEdgesBellowWeightThresholdHidden = this.graphOptionsService.areEdgesBellowWeightThresholdHidden;
    this.areNeighbourlessNodesHidden = this.graphOptionsService.areNeighbourlessNodesHidden;
    this.areEdgeWeightsShownAsLabels = this.graphOptionsService.areEdgeWeightsShownAsLabels;
    this.areEdgesColourCoded = this.graphOptionsService.areEdgesColourCoded;
    this.selectedLayoutOption = this.graphOptionsService.selectedLayoutOption;
    this.graphLayoutControl.setValue(this.selectedLayoutOption);
    this.graphEdgeWeightThreshold = this.graphOptionsService.graphEdgeWeightThreshold;
    this.graphLayoutGroups = this.graphOptionsService.graphLayoutGroups;
    this.weightThresholdFormControl = new FormControl(this.graphEdgeWeightThreshold, [
      Validators.required,
      Validators.min(0.00),
    ]);
    this.selectedMetric = this.graphOptionsService.selectedMetric;
    this.metricLayoutControl.setValue(this.selectedMetric);
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

  handleAreEdgesBellowWeightThresholdRepresentationChange($event: any): void {
    this.graphOptionsService.setAreEdgesBellowWeightThresholdHidden($event.checked);
    this.areEdgesBellowWeightThresholdHidden = $event.checked;
  }

  handleGraphEdgeWeightThresholdChange($event: any): void {
    let value = $event.target.value;
    if (value >= 0) {
      this.graphOptionsService.setGraphEdgeWeightThreshold(value);
      this.graphEdgeWeightThreshold = value;
    }
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

  handleResetGraphLayoutButtonPressed($event: MouseEvent) {
    this.graphOptionsService.setIsGraphLayoutToBeReset(true);
  }

  handleLayoutSpacingChange($event: any): void {
    this.graphOptionsService.setSpacingFactor($event.value/10);
    this.graphLayoutSpacing = $event.value/10;
  }

  handleUpdateSelectedMetric(newMetric: string): void {
    this.graphOptionsService.setSelectedMetric(newMetric);
  }

  formatSliderLabel(value: number) {
    return (value/10).toFixed(1);
  }
}
