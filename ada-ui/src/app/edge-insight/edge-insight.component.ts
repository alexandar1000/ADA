import {Component, Input, OnInit} from '@angular/core';
import {ClassStructure} from "../classes/class-structure";

@Component({
  selector: 'app-edge-insight',
  templateUrl: './edge-insight.component.html',
  styleUrls: ['./edge-insight.component.css']
})
export class EdgeInsightComponent implements OnInit {

  @Input() firstClassStructure: ClassStructure;
  @Input() secondClassStructure: ClassStructure;

  constructor() {}

  ngOnInit() {
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
