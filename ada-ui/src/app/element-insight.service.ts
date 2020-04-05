import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ElementInsightService {
  private _selectedNode = new BehaviorSubject(null);
  selectedNode$ = this._selectedNode.asObservable();

  private _selectedEdge = new BehaviorSubject(null);
  selectedEdge$ = this._selectedEdge.asObservable();

  get selectedNode(): number {
    return this._selectedNode.value;
  }

  get selectedEdge(): number {
    return this._selectedEdge.value;
  }

  constructor() { }

  setSelectedNode(value: string) {
    this._selectedNode.next(value);
  }

  setSelectedEdge(value: number) {
    this._selectedEdge.next(value);
  }

  resetSelectedNode(value: string) {
    this._selectedNode.next(null);
  }

  resetSelectedEdge(value: number) {
    this._selectedEdge.next(null);
  }
}
