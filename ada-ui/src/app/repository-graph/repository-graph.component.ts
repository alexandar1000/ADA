import { Component, OnInit } from '@angular/core';
import { sigma } from 'sigma'
import { AnalyserService} from "../analyser.service";
import {UserService} from "../user.service";

@Component({
  selector: 'app-repository-graph',
  templateUrl: './repository-graph.component.html',
  styleUrls: ['./repository-graph.component.css']
})
export class RepositoryGraphComponent implements OnInit {

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    // var sigmaJs = new sigma();
    console.log(this.analyserService.getAnalysis('https://github.com/alexandar1000/ADA-test-simple-JAVA-project-0', 'master'));
  }

}
