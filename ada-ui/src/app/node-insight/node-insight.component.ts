import {Component, Input, OnInit} from '@angular/core';
import {ClassStructure} from "../classes/class-structure";

@Component({
  selector: 'app-node-insight',
  templateUrl: './node-insight.component.html',
  styleUrls: ['./node-insight.component.css']
})
export class NodeInsightComponent implements OnInit {

  @Input() classStructure: ClassStructure;
  @Input() fullyQualifiedClassName: string;
  public outgoingRelatingClassNames: String[];
  public outgoingDependenceInfos: any[] = [];

  constructor() { }

  ngOnInit() {
    this.outgoingRelatingClassNames = Array.from(this.classStructure.outgoingDependenceInfo.keys());
    let className;
    for (let i = 0; i < this.outgoingRelatingClassNames.length; i++) {
      className = this.outgoingRelatingClassNames[i];
      this.outgoingDependenceInfos[className.toString()] = this.classStructure.outgoingDependenceInfo.get(className)
    }
  }

  /**
   * Extract the class name given a fullyQualifiedClassName
   * @param fullyQualifiedClassName a fully qualified class name from whcih the class name is to be extracted
   */
  public extractClassName(fullyQualifiedClassName: String): String {
    let lastIndex = fullyQualifiedClassName.lastIndexOf('.');
    return (lastIndex > 0 ? fullyQualifiedClassName.substr(lastIndex + 1, fullyQualifiedClassName.length - 1) : fullyQualifiedClassName);
  }
}
