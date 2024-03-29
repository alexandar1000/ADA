import {Component, OnInit} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";
import {AnalyserService} from "../analyser.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {NewEntryService} from '../new-entry.service';
import {SnapshotStyleService} from '../snapshot-style.service';
import {MatSnackBar} from "@angular/material/snack-bar";
import {CountdownConfig} from "ngx-countdown";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  projectStructure: ProjectStructure;
  snapshots = this.analyserService.snapshots;


  constructor(public analyserService: AnalyserService,
              private newEntryService: NewEntryService,
              private snapshotStyleService: SnapshotStyleService,
              private route: ActivatedRoute,
              private router: Router, private snackBar: MatSnackBar) {
  }


  ngOnInit() {
    if (this.router.url == '/dashboard/current') {
      this.analyserService.isLoading = true;
      this.analyserService.doAnalysis().subscribe(dataJson => {
        this.updateProjectStructure(dataJson);
        let owner = dataJson['gitRepoInfo'].owner;
        let repository = dataJson['gitRepoInfo'].repository;
        let branch = dataJson['gitRepoInfo'].branch;
        let snapshot = dataJson['gitRepoInfo'].timestamp;
        this.sendNewEntry(owner, repository, branch, snapshot);
      });

    } else {
      this.route.paramMap.subscribe(
        (params: ParamMap) =>
          this.getPreviousAnalysis(params.get('owner'), params.get('repository'), params.get('branch'), params.get('snapshot')),
        (error) => {
          console.log("Did not find")
        }
      );
    }
  }


  getPreviousAnalysis(owner: string, repository: string, branch: string, snapshot: string): void {
    this.analyserService.isLoading = true;
    this.analyserService.getPreviousAnalysis(owner, repository, branch, snapshot).subscribe(
      (dataJson) => {
        this.updateProjectStructure(dataJson)
      },
      (error) => {
        this.errorSnackBar("Given Repository Graph Is Not Available. Check the Selection!!")
        setTimeout(() => {
          this.router.navigateByUrl('/repo-form');
        }, 2000);
      }
    )
  }

  private updateProjectStructure(data: JSON): void {
    this.analyserService.isLoading = false;
    this.projectStructure = new ProjectStructure(data);
  }

  sendNewEntry(owner: string, repository: string, branch: string, snapshot: string) {
    this.newEntryService.confirmNewEntry([owner, repository, branch, snapshot]);
    this.snapshotStyleService.sendClickedSnapshotToSidebar([owner, repository, branch, snapshot]);
  }

  private errorSnackBar(error)
    :
    void {
    this.snackBar.open(error, 'Close', {
      duration: 5000,
      verticalPosition: 'bottom',
      horizontalPosition: 'start',
    });

  }
}
