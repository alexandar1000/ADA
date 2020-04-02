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

  constructor(private queryService: QueryService) { }

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

  onSubmit() {
    this.queryService.sendQueryToGraph([this.selectedQueryType.toLowerCase(), this.queryText.trim(), this.seePackageHigherClasses]);
  }

}
