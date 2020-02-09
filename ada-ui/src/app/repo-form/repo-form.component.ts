import { Component, OnInit } from '@angular/core';
import { RepoForm } from '../classes/repoform';
import { UserService } from '../user.service';
import { User } from '../classes/user';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private repositoryForm: RepoForm;

  constructor(private userService: UserService, private _snackBar: MatSnackBar) { 
    this.repositoryForm = new RepoForm();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.getUser(this.repositoryForm).subscribe(user => this.checkUserResponse(user));
  }

  checkUserResponse(user: User) {
    if (user) {
      // all good
    }
    else { 
      this._snackBar.open('Error: Incorrect url or branch', 'Close', {
        duration: 10000,
      });
    }
  }
}
