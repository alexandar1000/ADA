import {CustomMapInterface} from "./custom-map-interface";
import {PackageDeclarationInterface} from "./package-declaration-interface";
import {AttributeDeclarationInterface} from "./attribute-declaration-interface";
import {ConstructorDeclarationInterface} from "./constructor-declaration-interface";
import {MethodDeclarationInterface} from "./method-declaration-interface";
import {AttributeInvocationInterface} from "./attribute-invocation-interface";
import {ConstructorInvocationInterface} from "./constructor-invocation-interface";
import {MethodInvocationInterface} from "./method-invocation-interface";
import {PackageInvocationInterface} from "./package-invocation-interface";
import {ClassMetricValueInterface} from "./class-metric-value-interface";
import {RelationMetricValueInterface} from "./relation-metric-value-interface";
import {DependenceInfoInterface} from "./dependence-info-interface";

export interface ClassStructureInterface {
  packageDeclaration: PackageDeclarationInterface;
  attributeDeclarations: AttributeDeclarationInterface[];
  constructorDeclarations: ConstructorDeclarationInterface[];
  methodDeclarations: MethodDeclarationInterface[];

  outgoingDependenceInfo: CustomMapInterface<DependenceInfoInterface>;
  incomingDependenceInfo: CustomMapInterface<DependenceInfoInterface>;

  globalData: AttributeInvocationInterface[];
  globalMethods: MethodInvocationInterface[];

  externalPackageInvocations: PackageInvocationInterface[];
  externalMethodInvocations: MethodInvocationInterface[];
  externalConstructorInvocations: ConstructorInvocationInterface[];
  externalAttributeInvocations: AttributeInvocationInterface[];

  relationMetricValues: CustomMapInterface<RelationMetricValueInterface>
  classMetricValues: CustomMapInterface<ClassMetricValueInterface>
}
