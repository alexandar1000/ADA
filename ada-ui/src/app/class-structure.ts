import {CustomMap} from "./custom-map";
import {PackageDeclaration} from "./package-declaration";
import {AttributeDeclaration} from "./attribute-declaration";
import {ConstructorDeclaration} from "./constructor-declaration";
import {MethodDeclaration} from "./method-declaration";
import {AttributeInvocation} from "./attribute-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {MethodInvocation} from "./method-invocation";
import {PackageInvocation} from "./package-invocation";
import {ClassMetricValue} from "./class-metric-value";
import {RelationMetricValue} from "./relation-metric-value";

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
