import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { OwnerService } from '../owner.service';
import { Owner } from '../classes/owner';
import { Repository } from '../classes/repository';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;
  private owners = []

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private ownerService: OwnerService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.getOwnerList();
  }

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  getOwnerList(): void {
    this.ownerService.getOwnersList().subscribe(ownerNames => {
      ownerNames.forEach(ownerName => {
        let owner = new Owner();
        owner.repositories = new Array<Repository>();
        owner.repositories.push(new Repository());
        owner.name = ownerName;
        this.owners.push(owner);
      });
    });
  }

  getReposList(ownerName: string): void {
    this.ownerService.getReposList(ownerName).subscribe(repoNames => {
      let owner = this.getOwner(ownerName);
      owner.repositories = []
      repoNames.forEach(repoName => {
        let repository = new Repository();
        repository.name = repoName;
        owner.repositories.push(repository);
      });
    });
  }

  getOwner(ownerName: string): Owner {
    this.owners.forEach(owner => {
      if (ownerName === owner.name) {
        return owner;
      }
    });
    return null;
  }

  replaceOwner(ownerToAdd: Owner): void {
    for (let i = 0; i < this.owners.length; i++) {
      if (ownerToAdd.name === this.owners[i]) {
        this.owners.splice(i, 1, ownerToAdd);
      }
    }
  }
}


/**  Copyright 2019 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */
