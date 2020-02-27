import {CustomMap} from "./custom-map";

export interface ClassStructure {
  packageDeclaration: PackageDeclaration;
  attributeDeclarations: AttributeDeclaration[];
  constructorDeclarations: ConstructorDeclaration[];
  methodDeclarations: MethodDeclaration[];

  outgoingDependenceInfo: CustomMap<DependenceInfo>;
  incomingDependenceInfo: CustomMap<DependenceInfo>;

  globalData: AttributeInvocation[];
  globalMethods: MethodInvocation[];

  externalPackageInvocations: PackageInvocation[];
  externalMethodInvocations: MethodInvocation[];
  externalConstructorInvocations: ConstructorInvocation[];
  externalAttributeInvocations: AttributeInvocation[];

  relationMetricValues: CustomMap<RelationMetricValue>
  classMetricValues: CustomMap<ClassMetricValue>


}
