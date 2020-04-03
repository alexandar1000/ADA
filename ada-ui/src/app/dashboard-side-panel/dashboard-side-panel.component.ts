import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-side-panel',
  templateUrl: './dashboard-side-panel.component.html',
  styleUrls: ['./dashboard-side-panel.component.css']
})
export class DashboardSidePanelComponent implements OnInit {

  panelOpenState = false;

  constructor() { }

  ngOnInit() {
  }

}
