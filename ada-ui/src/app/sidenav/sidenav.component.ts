import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../classes/user';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;

  userList: User[];

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private userService: UserService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.userList = [];
    this.getUserList();
    this.userService.currentUser.subscribe(user => this.addUser(user));
  }

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  getUserList(): void {
    this.userService.getUserList().subscribe(users => this.addUsers(users));
  }

  addUsers(users): void {
    users.forEach(user => {
      this.userList.push(user);
    });
  }

  addUser(user: User) {
    if (user.userName) {
      if (!this.checkExistingUsers(user)) {
        this.userList.push(user);
      }
    }
  }

  checkExistingUsers(user: User): boolean {
    for (let index = 0; index < this.userList.length; index++) {
      const element = this.userList[index];
      if (user.userName === element.userName) {
        this.userList.splice(index, 1, user);
        return true;
      }
    }
    return false
  }
}


/**  Copyright 2019 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */
