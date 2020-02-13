import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../classes/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import {AnalyserService} from "../analyser.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private urlForm: string;
  private branchName: string;
  private  graphPromise: Observable<any>;
  @Output() receivedUser = new EventEmitter<User>();

  constructor(private analyserService: AnalyserService, private _snackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.analyserService.doAnalysis(this.urlForm, this.branchName).subscribe(graph => this.graphPromise = graph);
    this.router.navigate(['/repository-graph']);
  }
}
