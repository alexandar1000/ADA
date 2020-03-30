import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {

  private areZeroWeightsHidden = false;
  private areNodesWithoutNeighboursHidden = false;
  @Output() hideZeroWeightsEvent = new EventEmitter();
  @Output() hideNodesWithoutEdgesEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  handleZeroWeightsChanged($event: any): void {
    this.areZeroWeightsHidden = $event.checked;
    this.hideZeroWeightsEvent.emit(this.areZeroWeightsHidden);
  }

  handleNodesWithoutNeighboursChanged($event: any): void {
    this.areNodesWithoutNeighboursHidden = $event.checked;
    this.hideNodesWithoutEdgesEvent.emit(this.areNodesWithoutNeighboursHidden);
  }
}
