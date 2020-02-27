import {CustomMap} from "./custom-map";
import {PackageDeclaration1} from "./package-declaration1";
import {AttributeDeclaration1} from "./attribute-declaration1";
import {ConstructorDeclaration1} from "./constructor-declaration1";
import {MethodDeclaration1} from "./method-declaration1";
import {AttributeInvocation1} from "./attribute-invocation1";
import {ConstructorInvocation1} from "./constructor-invocation1";
import {MethodInvocation1} from "./method-invocation1";
import {PackageInvocation1} from "./package-invocation1";
import {ClassMetricValue1} from "./class-metric-value1";
import {RelationMetricValue1} from "./relation-metric-value1";
import {DependenceInfo1} from "./dependence-info1";

export interface ClassStructure1 {
  packageDeclaration: PackageDeclaration1;
  attributeDeclarations: AttributeDeclaration1[];
  constructorDeclarations: ConstructorDeclaration1[];
  methodDeclarations: MethodDeclaration1[];

  outgoingDependenceInfo: CustomMap<DependenceInfo1>;
  incomingDependenceInfo: CustomMap<DependenceInfo1>;

  globalData: AttributeInvocation1[];
  globalMethods: MethodInvocation1[];

  externalPackageInvocations: PackageInvocation1[];
  externalMethodInvocations: MethodInvocation1[];
  externalConstructorInvocations: ConstructorInvocation1[];
  externalAttributeInvocations: AttributeInvocation1[];

  relationMetricValues: CustomMap<RelationMetricValue1>
  classMetricValues: CustomMap<ClassMetricValue1>
}
