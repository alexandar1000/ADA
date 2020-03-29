import { Component, OnInit, Input } from '@angular/core';
import { SnapshotStyleService } from '../snapshot-style.service';

@Component({
  selector: 'app-snapshot',
  templateUrl: './snapshot.component.html',
  styleUrls: ['./snapshot.component.css']
})
export class SnapshotComponent implements OnInit {
  @Input() owner: string;
  @Input() repository: string;
  @Input() branch: string;
  @Input() snapshot: string;
  private highlighted: boolean;

  @Input()
  set toHighlightSnapshot(entry: string[]) {
    if (entry) {
      let snapshotToHighlight = entry[3];
      if (snapshotToHighlight === this.snapshot) {
        this.highlighted = true;
      }
    }
  }

  @Input()
  set toUnHighlightSnapshot(entry: string[]) {
    if (entry) {
      let snapshot = entry[3];
      if (snapshot === this.snapshot) {
        this.highlighted = false;
      }
    }
  }

  constructor(private snapshotStyleService: SnapshotStyleService) { }

  ngOnInit() {
  }

  sendClickEventToSidebar(): void {
    let entry = [this.owner, this.repository, this.branch, this.snapshot];
    this.snapshotStyleService.sendClickedSnapshotToSidebar(entry);
  }

}
