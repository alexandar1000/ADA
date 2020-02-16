import { Component, OnInit } from '@angular/core';
import { sigma } from 'sigma'
import { AnalyserService} from "../analyser.service";
import {UserService} from "../user.service";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-repository-graph',
  templateUrl: './repository-graph.component.html',
  styleUrls: ['./repository-graph.component.css']
})
export class RepositoryGraphComponent implements OnInit {
  private graphData: any;

  constructor(private analyserService: AnalyserService) { }

  ngOnInit() {
    this.analyserService.getAnalysis()
      .pipe(
      tap(_ => console.log('tapped'))
      ).subscribe(data => this.graphData = data);
  }

}
