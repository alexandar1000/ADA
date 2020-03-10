import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewEntryService {

  private newEntryConfirmedSource = new Subject<string[]>();

  newEntryConfirmed$ = this.newEntryConfirmedSource.asObservable();


  constructor() { }

  confirmNewEntry(entry: string[]) {
    this.newEntryConfirmedSource.next(entry);
  }
}
