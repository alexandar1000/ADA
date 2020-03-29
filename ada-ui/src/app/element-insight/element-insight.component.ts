import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";

@Component({
  selector: 'app-element-insight',
  templateUrl: './element-insight.component.html',
  styleUrls: ['./element-insight.component.css']
})
export class ElementInsightComponent implements OnInit {

  @Input() projectStructure: ProjectStructure;
  @Input() selectedNode: string;
  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.selectedNode) {
      this.displayCorrespondingInformation(this.selectedNode);
    }
  }

  private displayCorrespondingInformation(nodeId: string): void {

  }

}
