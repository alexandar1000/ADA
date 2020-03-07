import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Snapshot} from "./classes/snapshot";

@Injectable({
  providedIn: 'root'
})
export class AnalyserService {

  public analysisEndpointUrl = 'http://localhost:8080/analyser';
  public repoUrl: string;
  public repoBranch: string;

  public snapshots: Snapshot[] = [
    new Snapshot(78, '2020-03-01 15:14'),
    new Snapshot(12, '2019-09-21 13:00'),
    new Snapshot(0, '2019-07-28 01:02')
  ];
  public metrics = [
    'NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING',
    'NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING',
    'NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING',
    'NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING',
    'NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING',
    'NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS',
    'BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS'
  ];

  constructor() {
  }
}
