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
  private spacingFactor = new BehaviorSubject(this.initialSpacingFactor);
  spacingFactor$ = this.spacingFactor.asObservable();

  public areZeroWeightedEdgesHiddenInitially = false;
  private areZeroWeightedEdgesHidden = new BehaviorSubject(this.areZeroWeightedEdgesHiddenInitially);
  areZeroWeightedEdgesHidden$ = this.areZeroWeightedEdgesHidden.asObservable();

  public areNeighbourlessNodesHiddenInitially = false;
  private areNeighbourlessNodesHidden = new BehaviorSubject(this.areNeighbourlessNodesHiddenInitially);
  areNeighbourlessNodesHidden$ = this.areNeighbourlessNodesHidden.asObservable();

  public areEdgeWeightsShownAsLabelsInitially = false;
  private areEdgeWeightsShownAsLabels = new BehaviorSubject(this.areEdgeWeightsShownAsLabelsInitially);
  areEdgeWeightsShownAsLabels$ = this.areEdgeWeightsShownAsLabels.asObservable();

  public areEdgesColourCodedInitially = false;
  private areEdgesColourCoded = new BehaviorSubject(this.areEdgesColourCodedInitially);
  areEdgesColourCoded$ = this.areEdgesColourCoded.asObservable();

  public selectedLayoutOptionInitially = 'circle';
  private selectedLayoutOption = new BehaviorSubject(this.selectedLayoutOptionInitially);
  selectedLayoutOption$ = this.selectedLayoutOption.asObservable();

  public isGraphViewToBeResetInitially = false;
  private isGraphViewToBeReset = new BehaviorSubject(this.isGraphViewToBeResetInitially);
  isGraphViewToBeReset$ = this.isGraphViewToBeReset.asObservable();

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
        {value: 'cose', viewValue: 'Cose'}
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

}
