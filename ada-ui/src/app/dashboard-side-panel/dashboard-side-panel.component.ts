import {Component, Input, OnInit} from '@angular/core';
import {ProjectStructure} from "../classes/project-structure";

@Component({
  selector: 'app-dashboard-side-panel',
  templateUrl: './dashboard-side-panel.component.html',
  styleUrls: ['./dashboard-side-panel.component.css']
})
export class DashboardSidePanelComponent implements OnInit {

  isMenuOpen = false;
  @Input() projectStructure: ProjectStructure;

  constructor() { }

  ngOnInit() {
  }

}
