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

  constructor(private queryService: QueryService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.queryService.sendQueryToGraph([this.selectedQueryType.toLowerCase(), this.queryText.trim()]);
  }

}
