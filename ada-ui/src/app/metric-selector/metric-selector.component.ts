import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatSelectChange} from "@angular/material/select";

@Component({
  selector: 'app-metric-selector',
  templateUrl: './metric-selector.component.html',
  styleUrls: ['./metric-selector.component.css']
})
export class MetricSelectorComponent implements OnInit {

  @Input() metrics: any;
  @Input() selectedMetric: String;
  @Output() selectedMetricChanged: EventEmitter<String> = new EventEmitter();
  constructor() { }

  ngOnInit() {
  }

  emitSelectedMetricChange($event: MatSelectChange) {
    this.selectedMetricChanged.emit($event.value);
  }
}
