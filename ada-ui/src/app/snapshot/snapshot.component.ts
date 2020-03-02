import { Component, OnInit, Input } from '@angular/core';

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

  constructor() { }

  ngOnInit() {
  }

}
