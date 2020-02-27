export class ClassMetricValue {
  constructor(classDeclarationElementJSON: JSON) {
    this.numberOfPackageImportsIncoming = classDeclarationElementJSON['numberOfPackageImportsIncoming'];
    this.numberOfPackageImportsOutgoing = classDeclarationElementJSON['numberOfPackageImportsOutgoing'];

    this.numberOfAttributeInvocationsIncoming = classDeclarationElementJSON['numberOfAttributeInvocationsIncoming'];
    this.numberOfAttributeInvocationsOutgoing = classDeclarationElementJSON['numberOfAttributeInvocationsOutgoing'];

    this.numberOfMethodInvocationsIncoming = classDeclarationElementJSON['numberOfMethodInvocationsIncoming'];
    this.numberOfMethodInvocationsOutgoing = classDeclarationElementJSON['numberOfMethodInvocationsOutgoing'];

    this.numberOfConstructorInvocationsIncoming = classDeclarationElementJSON['numberOfConstructorInvocationsIncoming'];
    this.numberOfConstructorInvocationsOutgoing = classDeclarationElementJSON['numberOfConstructorInvocationsOutgoing'];

    this.bidirectionalNumberOfPackageImports = classDeclarationElementJSON['bidirectionalNumberOfPackageImports'];

    this.bidirectionalNumberOfAttributeInvocations = classDeclarationElementJSON['bidirectionalNumberOfAttributeInvocations'];

    this.bidirectionalNumberOfMethodInvocations = classDeclarationElementJSON['bidirectionalNumberOfMethodInvocations'];

    this.bidirectionalNumberOfConstructorInvocations = classDeclarationElementJSON['bidirectionalNumberOfConstructorInvocations'];

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
}
