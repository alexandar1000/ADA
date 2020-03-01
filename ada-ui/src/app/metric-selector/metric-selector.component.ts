import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-metric-selector',
  templateUrl: './metric-selector.component.html',
  styleUrls: ['./metric-selector.component.css']
})
export class MetricSelectorComponent implements OnInit {

  @Input() metrics: any;
  selectedMetric: string;
  constructor() { }

  ngOnInit() {
  }

}
