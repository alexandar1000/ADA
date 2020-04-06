import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ElementInsightService {
  private _selectedNodes = new BehaviorSubject<string[]>([]);
  selectedNodes$ = this._selectedNodes.asObservable();

  private _selectedEdges = new BehaviorSubject<number[]>([]);
  selectedEdges$ = this._selectedEdges.asObservable();

  get selectedNodes(): string[] {
    return this._selectedNodes.value;
  }

  get selectedEdges(): number[] {
    return this._selectedEdges.value;
  }

  constructor() { }

  addSelectedNode(nodeId: string) {
    let nodeIdArray = this._selectedNodes.value;
    nodeIdArray.push(nodeId);
    this._selectedNodes.next(nodeIdArray);
  }

  addSelectedEdge(edgeId: number) {
    let edgeIdArray = this._selectedEdges.value;
    edgeIdArray.push(edgeId);
    this._selectedEdges.next(edgeIdArray);
  }

  removeSelectedNode(nodeId: string) {
    let nodeIdArray = this._selectedNodes.value;
    nodeIdArray = nodeIdArray.filter(function (value, index, arr) {
      return nodeId != value;
    });
    this._selectedNodes.next(nodeIdArray);
  }

  removeSelectedEdge(edgeId: number) {
    let edgeIdArray = this._selectedEdges.value;
    edgeIdArray = edgeIdArray.filter(function (value, index, arr) {
      return edgeId != value;
    });
    this._selectedEdges.next(edgeIdArray);
  }
}
