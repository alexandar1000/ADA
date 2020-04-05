import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SnapshotStyleService {

  private clickedSnapshot = new Subject<string[]>();

  clickedSnapshotHierarchy$ = this.clickedSnapshot.asObservable();

  constructor() { }

  sendClickedSnapshotToSidebar(entry: string[]) {
    this.clickedSnapshot.next(entry);
  }
}
