import { Component, OnInit, Input } from '@angular/core';
import { SidebarService } from '../sidebar.service';

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit {
  @Input() owner: string;
  @Input() repository: string;
  @Input() branch: string;
  private snapshots: string[];
  private clicked: boolean;
  private cashed: boolean;

  @Input()
  set newEntrySnapshot(entry: string[]) {
    let owner = entry[0];
    let repository = entry[1];
    let branch = entry[2];
    let snapshot = entry[3];
    if (!this.snapshots) {
      this.snapshots = [];
    }
    if (branch === this.branch) {
      if (!this.cashed) {
        this.getSnapshotsList(owner, repository, branch);
      }
      else {
        this.clicked = true;
      }
      if (!this.isSnapshotInList(snapshot)) {
        this.snapshots.push(snapshot);
      }
    }
  }

  constructor(private sidebarService: SidebarService) { }

  ngOnInit() {
    this.clicked = false;
    this.cashed = false;
  }

  getSnapshotsList(owner: string, repository: string, branch: string): void {
    if (!this.clicked && !this.cashed) {
      this.sidebarService.getSnapshotsList(owner, repository, branch).subscribe(snapshots => {
        this.snapshots = [];
        snapshots.forEach(snapshot => {
          this.snapshots.push(snapshot.timestamp);
        });
        this.clicked = true;
        this.cashed = true;
      });
    }
    else if (this.cashed && this.clicked) {
      this.clicked = false;
    }
    else if (this.cashed && !this.clicked) {
      this.clicked = true;
    }
  }

  isSnapshotInList(snapshot: string): boolean {
    for (let index=0; index<this.snapshots.length; index++) {
      if (this.snapshots[index] === snapshot) {
        return true;
      }
    }
    return false;
  }

}
