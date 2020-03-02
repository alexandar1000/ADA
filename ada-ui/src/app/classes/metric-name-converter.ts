export class MetricNameConverter {
  private dictionary = new Map<string, any>();

  constructor() {
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_INCOMING', { fieldName: 'numberOfPackageImportsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_OUTGOING', { fieldName: 'numberOfPackageImportsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_INCOMING', { fieldName: 'numberOfAttributeInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_OUTGOING', { fieldName: 'numberOfAttributeInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_INCOMING', { fieldName: 'numberOfMethodInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_OUTGOING', { fieldName: 'numberOfMethodInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_INCOMING', { fieldName: 'numberOfConstructorInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_OUTGOING', { fieldName: 'numberOfConstructorInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_PACKAGE_IMPORTS', { fieldName: 'bidirectionalNumberOfPackageImports', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS', { fieldName: 'bidirectionalNumberOfAttributeInvocations', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_METHOD_INVOCATIONS', { fieldName: 'bidirectionalNumberOfMethodInvocations', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS', { fieldName: 'bidirectionalNumberOfConstructorInvocations', arrowStyle: ['source-arrow-shape', 'none'] });

    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING', { fieldName: 'numberOfPackageImportsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING', { fieldName: 'numberOfPackageImportsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING', { fieldName: 'numberOfAttributeInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING', { fieldName: 'numberOfAttributeInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING', { fieldName: 'numberOfMethodInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING', { fieldName: 'numberOfMethodInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING', { fieldName: 'numberOfConstructorInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'] });
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING', { fieldName: 'numberOfConstructorInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS', { fieldName: 'bidirectionalNumberOfPackageImports', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS', { fieldName: 'bidirectionalNumberOfAttributeInvocations', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS', { fieldName: 'bidirectionalNumberOfMethodInvocations', arrowStyle: ['source-arrow-shape', 'none'] });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS', { fieldName: 'bidirectionalNumberOfConstructorInvocations', arrowStyle: ['source-arrow-shape', 'none'] });
  }

  public translateMetricName(metricDbName: string): string {
    if (this.dictionary.has(metricDbName)) {
      return this.dictionary.get(metricDbName)['fieldName'];
    } else {
      return null;
    }
  }

  public getArrowStyle(metricDbName: string) : string[] {
    if (this.dictionary.has(metricDbName)) {
      return this.dictionary.get(metricDbName)['arrowStyle'];
    } else {
      return ['source-arrow-shape', 'none'];
    }
  }
}
