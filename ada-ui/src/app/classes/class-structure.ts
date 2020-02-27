import {AttributeDeclaration} from "./attribute-declaration";
import {ConstructorDeclaration} from "./constructor-declaration";
import {PackageDeclaration} from "./package-declaration";
import {MethodDeclaration} from "./method-declaration";
import {DependenceInfo} from "./dependence-info";
import {AttributeInvocation} from "./attribute-invocation";
import {MethodInvocation} from "./method-invocation";
import {PackageInvocation} from "./package-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {RelationMetricValue} from "./relation-metric-value";
import {ClassMetricValue} from "./class-metric-value";

export class ClassStructure {
  packageDeclaration: PackageDeclaration;
  attributeDeclarations: AttributeDeclaration[] = [];
  constructorDeclarations: ConstructorDeclaration[] = [];
  methodDeclarations: MethodDeclaration[] = [];

  outgoingDependenceInfo: Map<String, DependenceInfo>;
  incomingDependenceInfo: Map<String, DependenceInfo>;

  globalData: AttributeInvocation[] = [];
  globalMethods: MethodInvocation[] = [];

  externalPackageInvocations: PackageInvocation[] = [];
  externalMethodInvocations: MethodInvocation[] = [];
  externalConstructorInvocations: ConstructorInvocation[] = [];
  externalAttributeInvocations: AttributeInvocation[] = [];

  relationMetricValues: Map<String, RelationMetricValue>;
  classMetricValues: Map<String, ClassMetricValue>;


  constructor(classDeclaration: JSON) {
    this.packageDeclaration = classDeclaration['packageDeclaration'];
    for (let attributeDeclaration of classDeclaration['attributeDeclarations']) {
      this.attributeDeclarations.push(new AttributeDeclaration(attributeDeclaration));
    }
    for (let constructorDeclaration of classDeclaration['constructorDeclarations']) {
      this.constructorDeclarations.push(new ConstructorDeclaration(constructorDeclaration));
    }
    for (let methodDeclaration of classDeclaration['methodsDeclarations']) {
      this.methodDeclarations.push(new MethodDeclaration(methodDeclaration));
    }

    this.outgoingDependenceInfo = new Map<String, DependenceInfo>();
    for (let outgoingDependenceInfoClassName in classDeclaration['outgoingDependenceInfo']) {
      this.outgoingDependenceInfo.set(outgoingDependenceInfoClassName, new DependenceInfo(classDeclaration[outgoingDependenceInfoClassName]));
    }
    this.incomingDependenceInfo = new Map<String, DependenceInfo>();
    for (let incomingDependenceInfoClassName in classDeclaration['incomingDependenceInfo']) {
      console.log('HERE');
      console.log(incomingDependenceInfoClassName);
      console.log(classDeclaration[incomingDependenceInfoClassName]);
      this.incomingDependenceInfo.set(incomingDependenceInfoClassName, new DependenceInfo(classDeclaration[incomingDependenceInfoClassName]));
    }


    for (let globalDataJSON of classDeclaration['globalData']) {
      this.globalData.push(new AttributeInvocation(globalDataJSON));
    }
    for (let globalMethodJSON of classDeclaration['globalMethods']) {
      this.globalMethods.push(new MethodInvocation(globalMethodJSON));
    }


    for (let externalPackageInvocationsJSON of classDeclaration['externalPackageImports']) {
      this.externalPackageInvocations.push(new PackageInvocation(externalPackageInvocationsJSON));
    }
    for (let externalMethodInvocationsJSON of classDeclaration['externalMethodInvocations']) {
      this.externalMethodInvocations.push(new MethodInvocation(externalMethodInvocationsJSON));
    }
    for (let externalConstructorInvocationsJSON of classDeclaration['externalConstructorInvocations']) {
      this.externalConstructorInvocations.push(new ConstructorInvocation(externalConstructorInvocationsJSON));
    }
    for (let externalAttributeInvocationsJSON of classDeclaration['externalAttributeInvocations']) {
      this.externalAttributeInvocations.push(new AttributeInvocation(externalAttributeInvocationsJSON));
    }

    // this.relationMetricValues = classDeclaration['relationMetricValues'];
    // this.classMetricValues = classDeclaration['classMetricValues'];
  }
}
