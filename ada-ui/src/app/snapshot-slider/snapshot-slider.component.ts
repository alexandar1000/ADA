import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {MatSliderChange} from "@angular/material/slider";
import {Snapshot} from "../classes/snapshot";

@Component({
  selector: 'app-snapshot-slider',
  templateUrl: './snapshot-slider.component.html',
  styleUrls: ['./snapshot-slider.component.css']
})
export class SnapshotSliderComponent implements OnInit {

  @Input() snapshots: Snapshot[] = [];
  timestamp: string = 'THERE';
  minValue: number;
  maxValue: number;
  initialValue: number;

  constructor() { }

  ngOnInit() {
    this.timestamp = (this.snapshots.length > 0 ? this.snapshots[this.snapshots.length-1].timestamp : 'No snapshots');
    this.setMinValue();
    this.setMaxValue();
    this.setInitialValue();
  }

  // ngOnChanges(changes: SimpleChanges) {
  //   this.setMinValue();
  //   this.setMaxValue();
  //   this.setInitialValue();
  // }

  // Is zero to consolidate with the zero-indexing of the arrays in TS
  setMinValue(): void {
    this.minValue = 0;
  }

  // Will return the length of the snapshots-1 in order to consolidate with the zero-indexing of the arrays in TS. Also,
  // the slider works only with two or more elements, and otherwise will not be displayed
  setMaxValue(): void {
    this.maxValue = this.snapshots.length - 1;
  }

  setInitialValue() {
    this.initialValue =  this.snapshots.length - 1;
  }

  getCorrespondingDate(index: number): string {
    return this.snapshots[index].timestamp;
  }

  updateTimestamp(index: MatSliderChange) {
    this.timestamp = this.getCorrespondingDate(index.value);
  }

}
