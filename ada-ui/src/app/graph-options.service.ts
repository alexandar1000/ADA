import {Injectable, Input} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GraphOptionsService {

  private spacingFactor = new BehaviorSubject(1.0);
  spacingFactor$ = this.spacingFactor.asObservable();

  private areZeroWeightedEdgesHidden = new BehaviorSubject(false);
  areZeroWeightedEdgesHidden$ = this.areZeroWeightedEdgesHidden.asObservable();

  private areNeighbourlessNodesHidden = new BehaviorSubject(false);
  areNeighbourlessNodesHidden$ = this.areNeighbourlessNodesHidden.asObservable();

  private areEdgeWeightsShownAsLabels = new BehaviorSubject(false);
  areEdgeWeightsShownAsLabels$ = this.areEdgeWeightsShownAsLabels.asObservable();

  private areEdgesColourCoded = new BehaviorSubject(false);
  areEdgesColourCoded$ = this.areEdgesColourCoded.asObservable();

  private selectedLayoutOption = new BehaviorSubject('circle');
  selectedLayoutOption$ = this.selectedLayoutOption.asObservable();

  private isGraphViewToBeReset = new BehaviorSubject(false);
  isGraphViewToBeReset$ = this.isGraphViewToBeReset.asObservable();

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
