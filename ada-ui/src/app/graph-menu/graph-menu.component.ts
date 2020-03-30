import {Component, EventEmitter, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-graph-menu',
  templateUrl: './graph-menu.component.html',
  styleUrls: ['./graph-menu.component.css']
})
export class GraphMenuComponent implements OnInit {

  private areZeroWeightsHidden = false;
  private areNeighboursHighlighted = true;
  @Output() optionChangedEvent = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  handleZeroWeightsChanged(): void {
    console.log(this.areZeroWeightsHidden);
  }

  handleNeighboursHighlighting(): void {
      console.log(this.areNeighboursHighlighted);
    }

}
