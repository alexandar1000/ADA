import {AttributeInvocation1} from "../attribute-invocation1";
import {MethodInvocation1} from "../method-invocation1";
import {PackageDeclaration} from "./package-declaration";
import {AttributeDeclaration} from "./attribute-declaration";
import {ConstructorDeclaration} from "./constructor-declaration";
import {MethodDeclaration} from "./method-declaration";
import {DependenceInfo} from "./dependence-info";
import {PackageInvocation} from "./package-invocation";
import {MethodInvocation} from "./method-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {AttributeInvocation} from "./attribute-invocation";
import {RelationMetricValue} from "./relation-metric-value";
import {ClassMetricValue} from "./class-metric-value";

export class ClassStructure {
  packageDeclaration: PackageDeclaration;
  attributeDeclarations: AttributeDeclaration[];
  constructorDeclarations: ConstructorDeclaration[];
  methodDeclarations: MethodDeclaration[];

  outgoingDependenceInfo: Map<String, DependenceInfo>;
  incomingDependenceInfo: Map<String, DependenceInfo>;

  globalData: AttributeInvocation1[];
  globalMethods: MethodInvocation1[];

  externalPackageInvocations: PackageInvocation[];
  externalMethodInvocations: MethodInvocation[];
  externalConstructorInvocations: ConstructorInvocation[];
  externalAttributeInvocations: AttributeInvocation[];

  relationMetricValues: Map<String, RelationMetricValue>;
  classMetricValues: Map<String, ClassMetricValue>;
}
