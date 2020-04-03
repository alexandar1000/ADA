import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  private receivedQuery = new Subject<any>();
  private receivedQueryMessage = new Subject<string>();

  receivedQueryEvent$ = this.receivedQuery.asObservable();
  receivedQueryMessageEvent$ = this.receivedQueryMessage.asObservable();

  constructor() { }

  sendQueryToGraph(query: (string|boolean)[]) {
    this.receivedQuery.next(query);
  }

  sendQueryMessageToQueryForm(message: string) {
    this.receivedQueryMessage.next(message);
  }
}
