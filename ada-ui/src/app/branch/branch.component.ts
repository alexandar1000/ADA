import { Component, OnInit, Input } from '@angular/core';
import { OwnerService } from '../owner.service';

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

  constructor(private ownerService: OwnerService) { }

  ngOnInit() {
    this.snapshots = [];
    this.clicked = false;
  }

  getSnapshotsList(owner: string, repository: string, branch: string): void {
    if (!this.clicked) {
      this.ownerService.getSnapshotsList(owner, repository, branch).subscribe(snapshots => {
        this.snapshots = [];
        snapshots.forEach(snapshot => {
          this.snapshots.push(snapshot.timestamp);
        });
      });
      this.clicked = true;
    }
    else {
      this.snapshots = [];
      this.clicked = false;
    }
    
  }

}
