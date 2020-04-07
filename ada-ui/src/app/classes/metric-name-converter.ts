interface MetricInfo {
  fieldName: string,
  arrowStyle: string[],
  menuName: string,
  metricType: string,
  valueType: string
}

interface GraphMetricOption {
  value: string;
  viewValue: string;
}

interface GraphMetricGroup {
  disabled?: boolean;
  name: string;
  graphMetricOptions: GraphMetricOption[];
}

export class MetricNameConverter {

  public metricOptions: GraphMetricGroup[] = [];

  private dictionary = new Map<string, MetricInfo>();

  constructor() {
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_INCOMING', { fieldName: 'numberOfPackageImportsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Incoming Package Imports', valueType: 'non-normalised' });
    this.dictionary.set('NUMBER_OF_CLASS_PACKAGE_IMPORTS_OUTGOING', { fieldName: 'numberOfPackageImportsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Outgoing Package Imports', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_INCOMING', { fieldName: 'numberOfAttributeInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Incoming Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_OUTGOING', { fieldName: 'numberOfAttributeInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Outgoing Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_INCOMING', { fieldName: 'numberOfMethodInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Incoming Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_METHOD_INVOCATIONS_OUTGOING', { fieldName: 'numberOfMethodInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Outgoing Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_INCOMING', { fieldName: 'numberOfConstructorInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Incoming Constructor Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_OUTGOING', { fieldName: 'numberOfConstructorInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'class', menuName: 'Outgoing Constructor Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_PACKAGE_IMPORTS', { fieldName: 'bidirectionalNumberOfPackageImports', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'class', menuName: 'Total Package Imports', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS', { fieldName: 'bidirectionalNumberOfAttributeInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'class', menuName: 'Total Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_METHOD_INVOCATIONS', { fieldName: 'bidirectionalNumberOfMethodInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'class', menuName: 'Total Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS', { fieldName: 'bidirectionalNumberOfConstructorInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'class', menuName: 'Total Constructor Invocations', valueType: 'non-normalised'});

    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING', { fieldName: 'numberOfPackageImportsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Incoming Package Imports', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_PACKAGE_IMPORTS_OUTGOING', { fieldName: 'numberOfPackageImportsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Outgoing Package Imports', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_INCOMING', { fieldName: 'numberOfAttributeInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Incoming Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS_OUTGOING', { fieldName: 'numberOfAttributeInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Outgoing Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_INCOMING', { fieldName: 'numberOfMethodInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Incoming Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_METHOD_INVOCATIONS_OUTGOING', { fieldName: 'numberOfMethodInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Outgoing Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_INCOMING', { fieldName: 'numberOfConstructorInvocationsIncoming', arrowStyle: ['source-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Incoming Constructor Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS_OUTGOING', { fieldName: 'numberOfConstructorInvocationsOutgoing', arrowStyle: ['target-arrow-shape', 'triangle'], metricType: 'relation', menuName: 'Outgoing Constructor Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_PACKAGE_IMPORTS', { fieldName: 'bidirectionalNumberOfPackageImports', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'relation', menuName: 'Total Package Imports', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_ATTRIBUTE_INVOCATIONS', { fieldName: 'bidirectionalNumberOfAttributeInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'relation', menuName: 'Total Attribute Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_METHOD_INVOCATIONS', { fieldName: 'bidirectionalNumberOfMethodInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'relation', menuName: 'Total Method Invocations', valueType: 'non-normalised'  });
    this.dictionary.set('BIDIRECTIONAL_NUMBER_OF_RELATION_CONSTRUCTOR_INVOCATIONS', { fieldName: 'bidirectionalNumberOfConstructorInvocations', arrowStyle: ['source-arrow-shape', 'none'], metricType: 'relation', menuName: 'Total Constructor Invocations', valueType: 'non-normalised'  });

    let normalised: GraphMetricOption[] = [];
    let nonNormalised: GraphMetricOption[] = [];
    let menuOption: GraphMetricOption;
    this.dictionary.forEach(function (value, key, map) {
      if (value.metricType === 'relation') {
        menuOption = {
          value: key,
          viewValue: value.menuName
        };

        if (value.valueType === 'normalised') {
          normalised.push(menuOption);
        }
        if (value.valueType === 'non-normalised') {
          nonNormalised.push(menuOption);
        }
      }
    }.bind(this));

    this.metricOptions = [
      {
        name: 'Normalised',
        graphMetricOptions: normalised
      },
      {
        name: 'Non Normalised',
        graphMetricOptions: nonNormalised
      }
    ];
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
