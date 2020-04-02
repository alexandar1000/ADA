import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GraphOptionsService {

  private spacingFactor = new BehaviorSubject(1.0);
  sharedSpacingFactor$ = this.spacingFactor.asObservable();

  constructor() { }

  setSpacingFactor(value: number) {
    this.spacingFactor.next(value)
  }
}
