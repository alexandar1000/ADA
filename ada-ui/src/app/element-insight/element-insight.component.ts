import {Component, Input, OnInit} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {ElementInsightService} from "../element-insight.service";
import {ClassStructure} from "../classes/class-structure";

interface edgeInformation {
  id: number,
  source: string,
  target: string
}

@Component({
  selector: 'app-element-insight',
  templateUrl: './element-insight.component.html',
  styleUrls: ['./element-insight.component.css']
})
export class ElementInsightComponent implements OnInit {

  @Input() projectStructure: ProjectStructure;
  selectedNodes: string[];
  selectedEdges: edgeInformation[];

  private subscriptions = [];
  private subscriptionIndex = 0;

  constructor(private elementInsightService: ElementInsightService) { }

  ngOnInit() {
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

  /**
   * Extract the class name given a fullyQualifiedClassName
   * @param fullyQualifiedClassName a fully qualified class name from whcih the class name is to be extracted
   */
  public extractClassName(fullyQualifiedClassName: string): string {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    return (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);
  }
}
