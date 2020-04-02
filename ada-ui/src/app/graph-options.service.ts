import {Injectable, Input} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GraphOptionsService {

  private spacingFactor = new BehaviorSubject(1.0);
  sharedSpacingFactor$ = this.spacingFactor.asObservable();

  private areZeroWeightedEdgesHidden = new BehaviorSubject(false);
  sharedAreZeroWeightedEdgesHidden$ = this.areZeroWeightedEdgesHidden.asObservable();

  private areNeighbourlessNodesHidden = new BehaviorSubject(false);
  sharedAreNeighbourlessNodesHidden$ = this.areNeighbourlessNodesHidden.asObservable();

  private areEdgeWeightsShownAsLabels = new BehaviorSubject(false);
  sharedAreEdgeWeightsShownAsLabels$ = this.areEdgeWeightsShownAsLabels.asObservable();

  private areEdgesColourCoded = new BehaviorSubject(false);
  sharedAreEdgesColourCoded$ = this.areEdgesColourCoded.asObservable();

  private selectedLayoutOption = new BehaviorSubject('circle');
  sharedSelectedLayoutOption$ = this.selectedLayoutOption.asObservable();

  private isGraphViewToBeReset = new BehaviorSubject(false);
  sharedAsGraphViewToBeReset$ = this.isGraphViewToBeReset.asObservable();

  constructor() { }

  setSpacingFactor(value: number) {
    this.spacingFactor.next(value)
  }

  setAreZeroWeightedEdgesHidden(value: boolean): void {
    this.areZeroWeightedEdgesHidden.next(value);
  }

  setAreNeighbourlessNodesHidden(value: boolean): void {
    this.areNeighbourlessNodesHidden.next(value);
  }

  setAreEdgeWeightsShownAsLabels(value: boolean): void {
    this.areEdgeWeightsShownAsLabels.next(value);
  }

  setAreEdgesColourCoded(value: boolean): void {
    this.areEdgesColourCoded.next(value);
  }

  setSelectedLayoutOption(value: string): void {
    this.selectedLayoutOption.next(value);
  }

  setIsGraphViewToBeReset(value: boolean): void {
    this.isGraphViewToBeReset.next(value);
  }

}
