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

  private _spacingFactor = new BehaviorSubject(1.0);
  spacingFactor$ = this._spacingFactor.asObservable();

  private _areZeroWeightedEdgesHidden = new BehaviorSubject(false);
  areZeroWeightedEdgesHidden$ = this._areZeroWeightedEdgesHidden.asObservable();

  private _areNeighbourlessNodesHidden = new BehaviorSubject(false);
  areNeighbourlessNodesHidden$ = this._areNeighbourlessNodesHidden.asObservable();

  private _areEdgeWeightsShownAsLabels = new BehaviorSubject(false);
  areEdgeWeightsShownAsLabels$ = this._areEdgeWeightsShownAsLabels.asObservable();

  private _areEdgesColourCoded = new BehaviorSubject(false);
  areEdgesColourCoded$ = this._areEdgesColourCoded.asObservable();

  private _selectedLayoutOption = new BehaviorSubject('circle');
  selectedLayoutOption$ = this._selectedLayoutOption.asObservable();

  private _isGraphViewToBeReset = new BehaviorSubject(false);
  isGraphViewToBeReset$ = this._isGraphViewToBeReset.asObservable();

  private _isGraphLayoutToBeReset = new BehaviorSubject(false);
  isGraphLayoutToBeReset$ = this._isGraphLayoutToBeReset.asObservable();

  get spacingFactor(): number {
    return this._spacingFactor.value;
  }

  get areZeroWeightedEdgesHidden(): boolean {
    return this._areZeroWeightedEdgesHidden.value
  }

  get areNeighbourlessNodesHidden(): boolean {
    return this._areNeighbourlessNodesHidden.value
  }

  get areEdgeWeightsShownAsLabels(): boolean {
    return this._areEdgeWeightsShownAsLabels.value
  }

  get areEdgesColourCoded(): boolean {
    return this._areEdgesColourCoded.value
  }

  get selectedLayoutOption(): string {
    return this._selectedLayoutOption.value
  }


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
    this._spacingFactor.next(value)
  }

  setAreZeroWeightedEdgesHidden(value: boolean): void {
    this._areZeroWeightedEdgesHidden.next(value);
  }

  setAreNeighbourlessNodesHidden(value: boolean): void {
    this._areNeighbourlessNodesHidden.next(value);
  }

  setAreEdgeWeightsShownAsLabels(value: boolean): void {
    this._areEdgeWeightsShownAsLabels.next(value);
  }

  setAreEdgesColourCoded(value: boolean): void {
    this._areEdgesColourCoded.next(value);
  }

  setSelectedLayoutOption(value: string): void {
    this._selectedLayoutOption.next(value);
  }

  setIsGraphViewToBeReset(value: boolean): void {
    this._isGraphViewToBeReset.next(value);
  }

  setIsGraphLayoutToBeReset(value: boolean) {
    this._isGraphLayoutToBeReset.next(value);
  }
}
