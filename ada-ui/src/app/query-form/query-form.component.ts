import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { QueryService } from '../query.service';

@Component({
  selector: 'app-query-form',
  templateUrl: './query-form.component.html',
  styleUrls: ['./query-form.component.css']
})
export class QueryFormComponent implements OnInit {

  selectedQueryType: string;
  queryTypes = ['Class', 'Package'];
  queryText: string;
  seePackageHigherClasses: boolean;
  queryMessage;

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
