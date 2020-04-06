import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

interface edgeInformation {
  id: number,
  source: string,
  target: string
}

@Injectable({
  providedIn: 'root'
})
export class ElementInsightService {
  private _selectedNodes = new BehaviorSubject<string[]>([]);
  selectedNodes$ = this._selectedNodes.asObservable();

  private _selectedEdges = new BehaviorSubject<edgeInformation[]>([]);
  selectedEdges$ = this._selectedEdges.asObservable();

  get selectedNodes(): string[] {
    return this._selectedNodes.value;
  }

  get selectedEdges(): edgeInformation[] {
    return this._selectedEdges.value;
  }

  constructor() { }

  addSelectedNode(nodeId: string): void {
    let nodeIdArray = this._selectedNodes.value;
    nodeIdArray.push(nodeId);
    this._selectedNodes.next(nodeIdArray);
  }

  addSelectedEdge(edgeId: number, source: string, target: string): void {
    let newEdge: edgeInformation = {
      id: edgeId,
      source: source,
      target: target
    };
    let edgeIdArray = this._selectedEdges.value;
    edgeIdArray.push(newEdge);
    this._selectedEdges.next(edgeIdArray);
  }

  removeSelectedNode(nodeId: string): void {
    let nodeIdArray = this._selectedNodes.value;
    nodeIdArray = nodeIdArray.filter(function (value, index, arr) {
      return nodeId != value;
    });
    this._selectedNodes.next(nodeIdArray);
  }

  removeSelectedEdge(edgeId: number): void {
    let edgeIdArray = this._selectedEdges.value;
    edgeIdArray = edgeIdArray.filter(function (value, index, arr) {
      return value.id != edgeId;
    });
    this._selectedEdges.next(edgeIdArray);
  }

  clearSelectedNodes(): void {
    this._selectedNodes.next([]);
  }

  clearSelectedEdges(): void {
    this._selectedEdges.next([]);
  }
}
