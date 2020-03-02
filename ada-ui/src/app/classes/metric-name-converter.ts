export class MetricNameConverter {
  private dictionary = new Map<string, string>();

  constructor() {
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_INCOMING', 'numberOfPackageImportsIncoming');
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_OUTGOING', 'numberOfPackageImportsOutgoing');
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_INCOMING', 'numberOfAttributeInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_OUTGOING', 'numberOfAttributeInvocationsOutgoing');
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_INCOMING', 'numberOfMethodInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_OUTGOING', 'numberOfMethodInvocationsOutgoing');
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_INCOMING', 'numberOfConstructorInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_OUTGOING', 'numberOfConstructorInvocationsOutgoing');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_PACKAGE_IMPORTS', 'bidirectionalNumberOfPackageImports');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS', 'bidirectionalNumberOfAttributeInvocations');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_METHOD_INVOCATIONS', 'bidirectionalNumberOfMethodInvocations');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS', 'bidirectionalNumberOfConstructorInvocations');

    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING', 'numberOfPackageImportsIncoming');
    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING', 'numberOfPackageImportsOutgoing');
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING', 'numberOfAttributeInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING', 'numberOfAttributeInvocationsOutgoing');
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING', 'numberOfMethodInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING', 'numberOfMethodInvocationsOutgoing');
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING', 'numberOfConstructorInvocationsIncoming');
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING', 'numberOfConstructorInvocationsOutgoing');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS', 'bidirectionalNumberOfPackageImports');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS', 'bidirectionalNumberOfAttributeInvocations');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS', 'bidirectionalNumberOfMethodInvocations');
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS', 'bidirectionalNumberOfConstructorInvocations');
  }

  public translateMetricName(metricDbName: string): string {
    if (this.dictionary.has(metricDbName)) {
      return this.dictionary.get(metricDbName);
    } else {
      return null;
    }
  }
}
