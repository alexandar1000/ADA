import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {

  private areZeroWeightsHidden = false;
  private areNeighboursHighlighted = true;
  @Output() hideZeroWeightsEvent = new EventEmitter();
  @Output() highlightNeighboursEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  handleZeroWeightsChanged($event: any): void {
    this.areZeroWeightsHidden = $event.checked;
    this.hideZeroWeightsEvent.emit(this.areZeroWeightsHidden);
  }

  handleNeighboursHighlighting($event: any): void {
    this.areNeighboursHighlighted = $event.checked;
    this.highlightNeighboursEvent.emit(this.areNeighboursHighlighted);
  }
}
