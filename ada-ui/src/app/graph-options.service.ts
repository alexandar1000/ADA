import {Injectable, Input} from '@angular/core';
import {BehaviorSubject} from "rxjs";

interface GraphLayoutOption {
  value: string;
  viewValue: string;
}

interface GraphLayoutGroup {
  disabled?: boolean;
  name: string;
  graphLayoutOptions: GraphLayoutOption[];
}

@Injectable({
  providedIn: 'root'
})
export class GraphOptionsService {

  public initialSpacingFactor = 1.0;
  public spacingFactor = new BehaviorSubject(this.initialSpacingFactor);
  spacingFactor$ = this.spacingFactor.asObservable();

  public areZeroWeightedEdgesHiddenInitially = false;
  public areZeroWeightedEdgesHidden = new BehaviorSubject(this.areZeroWeightedEdgesHiddenInitially);
  areZeroWeightedEdgesHidden$ = this.areZeroWeightedEdgesHidden.asObservable();

  public areNeighbourlessNodesHiddenInitially = false;
  public areNeighbourlessNodesHidden = new BehaviorSubject(this.areNeighbourlessNodesHiddenInitially);
  areNeighbourlessNodesHidden$ = this.areNeighbourlessNodesHidden.asObservable();

  public areEdgeWeightsShownAsLabelsInitially = false;
  public areEdgeWeightsShownAsLabels = new BehaviorSubject(this.areEdgeWeightsShownAsLabelsInitially);
  areEdgeWeightsShownAsLabels$ = this.areEdgeWeightsShownAsLabels.asObservable();

  public areEdgesColourCodedInitially = false;
  public areEdgesColourCoded = new BehaviorSubject(this.areEdgesColourCodedInitially);
  areEdgesColourCoded$ = this.areEdgesColourCoded.asObservable();

  public selectedLayoutOptionInitially = 'circle';
  public selectedLayoutOption = new BehaviorSubject(this.selectedLayoutOptionInitially);
  selectedLayoutOption$ = this.selectedLayoutOption.asObservable();

  public isGraphViewToBeResetInitially = false;
  public isGraphViewToBeReset = new BehaviorSubject(this.isGraphViewToBeResetInitially);
  isGraphViewToBeReset$ = this.isGraphViewToBeReset.asObservable();

  public isGraphLayoutToBeResetInitially = false;
  public isGraphLayoutToBeReset = new BehaviorSubject(this.isGraphLayoutToBeResetInitially);
  isGraphLayoutToBeReset$ = this.isGraphLayoutToBeReset.asObservable();

  public graphLayoutGroups: GraphLayoutGroup[] = [
    {
      name: 'Ungrouped',
      graphLayoutOptions: [
        {value: 'circle', viewValue: 'Circle'},
        {value: 'grid', viewValue: 'Grid'},
        {value: 'random', viewValue: 'Random'},
      ]
    },
    {
      name: 'Grouped',
      graphLayoutOptions: [
        {value: 'concentric', viewValue: 'Doughnut'},
        {value: 'fcose', viewValue: 'Fcose'}
      ]
    }
  ];

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

  setIsGraphLayoutToBeReset(value: boolean) {
    this.isGraphLayoutToBeReset.next(value);
  }
}
