export class RelationMetricValue {
  constructor(relationMetricValuesJSON: JSON) {
    this.numberOfPackageImportsIncoming = relationMetricValuesJSON['numberOfPackageImportsIncoming'];
    this.numberOfPackageImportsOutgoing = relationMetricValuesJSON['numberOfPackageImportsOutgoing'];

    this.numberOfAttributeInvocationsIncoming = relationMetricValuesJSON['numberOfAttributeInvocationsIncoming'];
    this.numberOfAttributeInvocationsOutgoing = relationMetricValuesJSON['numberOfAttributeInvocationsOutgoing'];

    this.numberOfMethodInvocationsIncoming = relationMetricValuesJSON['numberOfMethodInvocationsIncoming'];
    this.numberOfMethodInvocationsOutgoing = relationMetricValuesJSON['numberOfMethodInvocationsOutgoing'];

    this.numberOfConstructorInvocationsIncoming = relationMetricValuesJSON['numberOfConstructorInvocationsIncoming'];
    this.numberOfConstructorInvocationsOutgoing = relationMetricValuesJSON['numberOfConstructorInvocationsOutgoing'];

    this.bidirectionalNumberOfPackageImports = relationMetricValuesJSON['bidirectionalNumberOfPackageImports'];

    this.bidirectionalNumberOfAttributeInvocations = relationMetricValuesJSON['bidirectionalNumberOfAttributeInvocations'];

    this.bidirectionalNumberOfMethodInvocations = relationMetricValuesJSON['bidirectionalNumberOfMethodInvocations'];

    this.bidirectionalNumberOfConstructorInvocations = relationMetricValuesJSON['bidirectionalNumberOfConstructorInvocations'];

    this.generalCumulativeNormalisedBidirectional = relationMetricValuesJSON['generalCumulativeNormalisedBidirectional'].toFixed(3);
  }

  numberOfPackageImportsIncoming: number;
  numberOfPackageImportsOutgoing: number;

  numberOfAttributeInvocationsIncoming: number;
  numberOfAttributeInvocationsOutgoing: number;

  numberOfMethodInvocationsIncoming: number;
  numberOfMethodInvocationsOutgoing: number;

  numberOfConstructorInvocationsIncoming: number;
  numberOfConstructorInvocationsOutgoing: number;

  bidirectionalNumberOfPackageImports: number;

  bidirectionalNumberOfAttributeInvocations: number;

  bidirectionalNumberOfMethodInvocations: number;

  bidirectionalNumberOfConstructorInvocations: number;

  generalCumulativeNormalisedBidirectional: number;
}
