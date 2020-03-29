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
  private highlightSnapshot: string[];
  private previousHighlightSnapshot: string[];
  private highlighted: boolean;

  @Input()
  set newEntrySnapshot(entry: string[]) {
    if (entry) {
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
  }

  @Input()
  set toHighlightSnapshot(entry: string[]) {
    if (entry) {
      let ownerToHighlight = entry[0];
      let repositoryToHighlight = entry[1];
      let branchToHighlight = entry[2];
      this.highlightSnapshot = entry;
      if (branchToHighlight === this.branch && repositoryToHighlight === this.repository && ownerToHighlight === this.owner) {
        this.highlighted = true;
      }
    }
  }

  @Input()
  set toUnHighlightSnapshot(entry: string[]) {
    if (entry) {
      let branchToUnHighlight = entry[2];
      this.previousHighlightSnapshot = entry;
      if (branchToUnHighlight === this.branch) {
        if (this.highlightSnapshot) {
          if (this.branch !== this.highlightSnapshot[2]) {
            this.highlighted = false;
          }

          if (this.branch === this.highlightSnapshot[2] && this.owner !== this.highlightSnapshot[0]) {
            this.highlighted = false;
          }
        }
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
