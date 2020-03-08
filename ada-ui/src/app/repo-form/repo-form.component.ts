import { Component, OnInit} from '@angular/core';
import { UserService } from '../user.service';
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

  constructor(private userService: UserService, private _snackBar: MatSnackBar, private analyserService: AnalyserService, private router: Router) {
  }

  ngOnInit() {

  }

  onSubmit() {
    this.urlForm = this.urlForm.trim();
    this.branchName = this.branchName.trim();
    if (this.branchError()) {
      this._snackBar.open('Error: No spaces allowed in the name of the branch', 'Close', {
        duration: 10000,
      });
    }
    else {
    this.analyserService.repoUrl = this.urlForm;
    this.analyserService.repoBranch = this.branchName;
    // this.analyserService.doAnalysis('https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0', 'master');
    // this.analyserService.doAnalysis('https://github.com/mockito/mockito', 'master');
    // this.analyserService.doAnalysis('https://github.com/alexandar1000/ADA', 'master');
    this.router.navigate(['/dashboard/current']);
    }
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

  branchError(): boolean {
    if (this.branchName.split(' ').length > 1) {
      return true;
    }
    return false;
  }
}
