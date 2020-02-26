import { Component, OnInit} from '@angular/core';
import { UserService } from '../user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private urlForm: string;
  private branchName: string;

  constructor(private userService: UserService, private _snackBar: MatSnackBar) { 
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.sendRepoForm(this.urlForm, this.branchName).subscribe(response => this.checkFormReponse(response));
  }

  checkFormReponse(response) {
    if (response) {
       
    }
    else { 
      this._snackBar.open('Error: Incorrect url or branch', 'Close', {
        duration: 10000,
      });
    }
  }
}
