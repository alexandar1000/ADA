import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  private receivedQuery = new Subject<string[]>();

  receivedQueryEvent$ = this.receivedQuery.asObservable();

  constructor() { }

  sendQueryToGraph(query: string[]) {
    this.receivedQuery.next(query);
  }
}
