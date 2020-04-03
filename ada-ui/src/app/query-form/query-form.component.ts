import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { QueryService } from '../query.service';

@Component({
  selector: 'app-query-form',
  templateUrl: './query-form.component.html',
  styleUrls: ['./query-form.component.css']
})
export class QueryFormComponent implements OnInit {

  private selectedQueryType: string;
  private queryTypes = ['Class', 'Package'];
  private queryText: string;
  private seePackageHigherClasses: boolean;
  private queryMessage;

  constructor(private queryService: QueryService) { 
    if (this.queryService.receivedQueryMessageEvent$) {
      this.queryService.receivedQueryMessageEvent$.subscribe(
        queryMessage => {
          this.processMessage(queryMessage);
        }
      )
    }
  }

  ngOnInit() {
    this.seePackageHigherClasses = false;
  }

  isPackageQuery(): boolean {
    if (this.selectedQueryType) {
      if(this.selectedQueryType.toLowerCase() === 'package') {
        return true;
      }
    }
    return false;
  }

  processMessage(queryMessage: string) {
    this.queryMessage = queryMessage;
  }

  onSubmit() {
    this.queryService.sendQueryToGraph([this.selectedQueryType.toLowerCase(), this.queryText.trim(), this.seePackageHigherClasses]);
  }

}
