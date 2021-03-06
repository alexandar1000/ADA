import {Injectable, Input} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {AnalyserService} from "./analyser.service";
import {MetricNameConverter} from "./classes/metric-name-converter";

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

  public metrics = new MetricNameConverter();

  private _spacingFactor = new BehaviorSubject(1.0);
  spacingFactor$ = this._spacingFactor.asObservable();

  private _areEdgesBellowWeightThresholdHidden = new BehaviorSubject(false);
  areEdgesBellowWeightThresholdHidden$ = this._areEdgesBellowWeightThresholdHidden.asObservable();

  private _areNeighbourlessNodesHidden = new BehaviorSubject(false);
  areNeighbourlessNodesHidden$ = this._areNeighbourlessNodesHidden.asObservable();

  private _areEdgeWeightsShownAsLabels = new BehaviorSubject(false);
  areEdgeWeightsShownAsLabels$ = this._areEdgeWeightsShownAsLabels.asObservable();

  private _areEdgesColourCoded = new BehaviorSubject(true);
  areEdgesColourCoded$ = this._areEdgesColourCoded.asObservable();

  private _selectedLayoutOption = new BehaviorSubject('circle');
  selectedLayoutOption$ = this._selectedLayoutOption.asObservable();

  private _isGraphViewToBeReset = new BehaviorSubject(false);
  isGraphViewToBeReset$ = this._isGraphViewToBeReset.asObservable();

  private _isGraphLayoutToBeReset = new BehaviorSubject(false);
  isGraphLayoutToBeReset$ = this._isGraphLayoutToBeReset.asObservable();

  private _graphEdgeWeightThreshold = new BehaviorSubject(0.0);
  graphEdgeWeightThreshold$ = this._graphEdgeWeightThreshold.asObservable();

  private _selectedMetric = new BehaviorSubject(this.metrics.getInitialValue());
  selectedMetric$ = this._selectedMetric.asObservable();

  get spacingFactor(): number {
    return this._spacingFactor.value;
  }

  get areEdgesBellowWeightThresholdHidden(): boolean {
    return this._areEdgesBellowWeightThresholdHidden.value
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

  get graphEdgeWeightThreshold(): number {
    return this._graphEdgeWeightThreshold.value
  }

  get selectedMetric(): string {
    return this._selectedMetric.value;
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

  constructor(private analyserService: AnalyserService) { }

  setSpacingFactor(value: number) {
    this._spacingFactor.next(value)
  }

  setAreEdgesBellowWeightThresholdHidden(value: boolean): void {
    this._areEdgesBellowWeightThresholdHidden.next(value);
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

  setIsGraphLayoutToBeReset(value: boolean): void {
    this._isGraphLayoutToBeReset.next(value);
  }

  setGraphEdgeWeightThreshold(value: number): void {
    this._graphEdgeWeightThreshold.next(value);
  }

  setSelectedMetric(value: string): void {
    this._selectedMetric.next(value);
  }
}
