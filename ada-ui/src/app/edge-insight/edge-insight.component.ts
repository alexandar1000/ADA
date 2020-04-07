import {Component, Input, OnInit} from '@angular/core';
import {ClassStructure} from "../classes/class-structure";
import {DependenceInfo} from "../classes/dependence-info";

@Component({
  selector: 'app-edge-insight',
  templateUrl: './edge-insight.component.html',
  styleUrls: ['./edge-insight.component.css']
})
export class EdgeInsightComponent implements OnInit {

  @Input() firstClassStructure: ClassStructure;
  @Input() secondClassStructure: ClassStructure;
  @Input() firstFullyQualifiedClassName: string;
  @Input() secondFullyQualifiedClassName: string;
  public firstDependenceInfo: DependenceInfo = null;
  public secondDependenceInfo: DependenceInfo = null;

  constructor() {}

  ngOnInit() {
    if (this.firstClassStructure.outgoingDependenceInfo.has(this.secondFullyQualifiedClassName)) {
      this.firstDependenceInfo = this.firstClassStructure.outgoingDependenceInfo.get(this.secondFullyQualifiedClassName);
    }
    if (this.secondClassStructure.outgoingDependenceInfo.has(this.firstFullyQualifiedClassName)) {
      this.secondDependenceInfo = this.secondClassStructure.outgoingDependenceInfo.get(this.firstFullyQualifiedClassName);
    }
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
