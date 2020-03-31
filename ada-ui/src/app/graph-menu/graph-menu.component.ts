import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {

  @Input() areZeroWeightedEdgesHidden = false;
  @Input() areNodesWithoutNeighboursHidden = false;
  @Input() areEdgeWeightsShownAsLabels = false;
  @Input() areEdgesColourCoded = false;

  @Output() updateZeroWeightedEdgesRepresentationEvent = new EventEmitter();
  @Output() updateNeighbourlessNodesRepresentationEvent = new EventEmitter();
  @Output() updateEdgeWeightsAsLabelRepresentationEvent = new EventEmitter();
  @Output() updateEdgesColourCodingRepresentationEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  handleZeroWeightedEdgesRepresentationChange($event: any): void {
    this.areZeroWeightedEdgesHidden = $event.checked;
    this.updateZeroWeightedEdgesRepresentationEvent.emit(this.areZeroWeightedEdgesHidden);
  }

  handleNodesWithoutNeighboursRepresentationChange($event: any): void {
    this.areNodesWithoutNeighboursHidden = $event.checked;
    this.updateNeighbourlessNodesRepresentationEvent.emit(this.areNodesWithoutNeighboursHidden);
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
