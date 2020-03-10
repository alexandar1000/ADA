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

  outgoingDependenceInfo: Map<String, DependenceInfo> = new Map<String, DependenceInfo>();
  incomingDependenceInfo: Map<String, DependenceInfo> = new Map<String, DependenceInfo>();

  globalData: AttributeInvocation[] = [];
  globalMethods: MethodInvocation[] = [];

  externalPackageInvocations: PackageInvocation[] = [];
  externalMethodInvocations: MethodInvocation[] = [];
  externalConstructorInvocations: ConstructorInvocation[] = [];
  externalAttributeInvocations: AttributeInvocation[] = [];

  relationMetricValues: Map<String, RelationMetricValue> = new Map<String, RelationMetricValue>();
  classMetricValues: ClassMetricValue;


  constructor(classDeclaration: JSON) {
    this.packageDeclaration = new PackageDeclaration(classDeclaration['currentPackage']);

    let attributeDeclarationsJSON = classDeclaration['attributeDeclarations'];
    for (let attributeDeclarationJSON of attributeDeclarationsJSON) {
      this.attributeDeclarations.push(new AttributeDeclaration(attributeDeclarationJSON));
    }

    for (let constructorDeclaration of classDeclaration['constructorDeclarations']) {
      this.constructorDeclarations.push(new ConstructorDeclaration(constructorDeclaration));
    }

    for (let methodDeclaration of classDeclaration['methodsDeclarations']) {
      this.methodDeclarations.push(new MethodDeclaration(methodDeclaration));
    }

    if (classDeclaration.hasOwnProperty('incomingDependenceInfo')) {
      let outgoingDependenceInfoJSON = classDeclaration['outgoingDependenceInfo'];
      for (let outgoingDependenceInfoClassName in outgoingDependenceInfoJSON) {
        if (outgoingDependenceInfoJSON.hasOwnProperty(outgoingDependenceInfoClassName)) {
          this.outgoingDependenceInfo.set(outgoingDependenceInfoClassName, new DependenceInfo(outgoingDependenceInfoJSON[outgoingDependenceInfoClassName]));
        }
      }
    }


    if (classDeclaration.hasOwnProperty('incomingDependenceInfo')) {
      let incomingDependenceInfoJSON = classDeclaration['incomingDependenceInfo'];
      for (let incomingDependenceInfoClassName in incomingDependenceInfoJSON) {
        if (incomingDependenceInfoJSON.hasOwnProperty(incomingDependenceInfoClassName)) {
          this.incomingDependenceInfo.set(incomingDependenceInfoClassName, new DependenceInfo(incomingDependenceInfoJSON[incomingDependenceInfoClassName]));
        }
      }
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


    let relationMetricValuesJSON = classDeclaration['relationMetricValues'];
    for (let classNameForRelationMetricValue in relationMetricValuesJSON) {
      if (relationMetricValuesJSON.hasOwnProperty(classNameForRelationMetricValue)) {
        this.relationMetricValues.set(classNameForRelationMetricValue, new RelationMetricValue(relationMetricValuesJSON[classNameForRelationMetricValue]));
      }
    }
    this.classMetricValues = new ClassMetricValue(classDeclaration['classMetricValues']);
  }
}
