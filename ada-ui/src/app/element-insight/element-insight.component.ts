import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {ElementInsightService} from "../element-insight.service";

@Component({
  selector: 'app-element-insight',
  templateUrl: './element-insight.component.html',
  styleUrls: ['./element-insight.component.css']
})
export class ElementInsightComponent implements OnInit {

  @Input() projectStructure: ProjectStructure;
  selectedNode: string;
  selectedEdge: number;

  private subscriptions = [];
  private subscriptionIndex = 0;

  constructor(private elementInsightService: ElementInsightService) { }

  ngOnInit() {
    if (this.elementInsightService.selectedNode$) {
      this.subscriptions[this.subscriptionIndex++] = this.elementInsightService.selectedNode$.subscribe(
        value => {
          this.selectedNode = value;
        }
      )
    }
    if (this.elementInsightService.selectedEdge$) {
      this.subscriptions[this.subscriptionIndex++] = this.elementInsightService.selectedEdge$.subscribe(
        value => {
          this.selectedEdge = value;
        }
      )
    }
  }

  ngOnDestroy() {
    this.subscriptions.forEach( function (subscription) {
      subscription.unsubscribe();
    });
  }
}
