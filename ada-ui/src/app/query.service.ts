import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  private receivedQuery = new Subject<any>();

  receivedQueryEvent$ = this.receivedQuery.asObservable();

  constructor() { }

  sendQueryToGraph(query: (string|boolean)[]) {
    this.receivedQuery.next(query);
  }
}
