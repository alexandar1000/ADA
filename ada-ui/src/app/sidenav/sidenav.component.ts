import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { SidebarService } from '../sidebar.service';
import { NewEntryService } from '../new-entry.service';
import { SnapshotStyleService } from '../snapshot-style.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;
  private owners: string[];
  private entry: string[];
  private highglightSnapshot: string[];
  private previousHighlightSnapshot: string[];

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher,
    private sidebarService: SidebarService, private newEntryService: NewEntryService,
    private snapshotStyleService: SnapshotStyleService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);

    if (newEntryService.newEntryConfirmed$) {
      newEntryService.newEntryConfirmed$.subscribe(
        entry => {
          this.addNewEntry(entry);
        }
      );
    }

    if (snapshotStyleService.clickedSnapshotHierarchy$) {
      snapshotStyleService.clickedSnapshotHierarchy$.subscribe(
        entry => {
          this.sendOwnerHighlightSnapshot(entry);
        }
      );
    }

  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.getOwnerList();
  }

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  getOwnerList(): void {
    this.owners = [];
    this.entry = [];
    let snapshotToUnHighlight = this.highglightSnapshot;
    this.highglightSnapshot = [];
    this.previousHighlightSnapshot = snapshotToUnHighlight;
    this.sidebarService.getOwnersList().subscribe(ownerNames => {
      ownerNames.forEach(ownerName => {
        this.owners.push(ownerName);
      });
    });
  }

  addOwnerToList(owner: string): void {
    if (!this.checkIfOwnerInList(owner)) {
      this.owners.push(owner);
    }
  }

  checkIfOwnerInList(owner: string): boolean {
    for (let index=0; index < this.owners.length; index++) {
      if (owner === this.owners[index]) {
        return true;
      }
    }
    return false
  }

  addNewEntry(entry: string[]): void {
    this.entry = entry;
    let owner = entry[0];
    this.addOwnerToList(owner)
  }

  sendOwnerHighlightSnapshot(entry: string[]): void {
    if (this.highglightSnapshot) {
      this.previousHighlightSnapshot = this.highglightSnapshot;
    }
    this.highglightSnapshot = entry;
  }
}


/**  Copyright 2019 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */
