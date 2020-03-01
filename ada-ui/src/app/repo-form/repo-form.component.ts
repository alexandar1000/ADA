import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../classes/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import {AnalyserService} from "../analyser.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private urlForm: string;
  private branchName: string;
  @Output() receivedUser = new EventEmitter<User>();

  constructor(private userService: UserService, private _snackBar: MatSnackBar, private analyserService: AnalyserService, private router: Router) {
  }

  ngOnInit() {

  }

  onSubmit() {
    this.userService.getUser(this.urlForm, this.branchName).subscribe(user => this.checkUserResponse(user));
    // this.analyserService.doAnalysis(this.urlForm, this.branchName);
    this.analyserService.doAnalysis('https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0', 'master');
    // this.analyserService.doAnalysis('https://github.com/mockito/mockito', 'master');
    this.router.navigate(['/graph']);
  }

  checkUserResponse(user: User) {
    if (user) {
      console.log(user);
      this.receivedUser.emit(user);
      this.userService.addUserToNavigation(user);
    }
    else {
      this._snackBar.open('Error: Incorrect url or branch', 'Close', {
        duration: 10000,
      });
    }
  }
}
