import {Component, Input, OnInit} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";

@Component({
  selector: 'app-element-insight',
  templateUrl: './element-insight.component.html',
  styleUrls: ['./element-insight.component.css']
})
export class ElementInsightComponent implements OnInit {

  @Input() projectStructure: ProjectStructure;
  constructor() { }

  ngOnInit() {
  }

}
