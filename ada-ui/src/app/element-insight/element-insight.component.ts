import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {ElementInsightService} from "../element-insight.service";
import {ClassStructure} from "../classes/class-structure";

@Component({
  selector: 'app-element-insight',
  templateUrl: './element-insight.component.html',
  styleUrls: ['./element-insight.component.css']
})
export class ElementInsightComponent implements OnInit {

  @Input() projectStructure: ProjectStructure;
  selectedNodes: string[];
  selectedEdges: number[];

  private subscriptions = [];
  private subscriptionIndex = 0;

  constructor(private elementInsightService: ElementInsightService) { }

  ngOnInit() {
    console.log(this.projectStructure);
    if (this.elementInsightService.selectedNodes$) {
      this.subscriptions[this.subscriptionIndex++] = this.elementInsightService.selectedNodes$.subscribe(
        value => {
          this.selectedNodes = value;
        }
      )
    }
    if (this.elementInsightService.selectedEdges$) {
      this.subscriptions[this.subscriptionIndex++] = this.elementInsightService.selectedEdges$.subscribe(
        value => {
          this.selectedEdges = value;
        }
      )
    }
  }

  ngOnDestroy() {
    this.subscriptions.forEach( function (subscription) {
      subscription.unsubscribe();
    });
  }

  retrieveNodeInformation(nodeId): ClassStructure {
    return this.projectStructure.classStructures.get(nodeId);
  }
}
