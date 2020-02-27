import {PackageInvocation} from "./package-invocation";
import {AttributeInvocation} from "./attribute-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {MethodInvocation} from "./method-invocation";

export class DependenceInfo {
  constructor(dependenceInfoJSON: JSON) {
    let packageInvocationsJSON = dependenceInfoJSON['packages'];
    for (let packageInvocationJSON of packageInvocationsJSON) {
      this.packages.push(new PackageInvocation(packageInvocationJSON));
    }

    let methodInvocationsJSON = dependenceInfoJSON['methods'];
    for (let methodInvocationJSON of methodInvocationsJSON) {
      this.methods.push(new MethodInvocation(methodInvocationJSON));
    }

    let constructorInvocationsJSON = dependenceInfoJSON['constructors'];
    for (let constructorInvocationJSON of constructorInvocationsJSON) {
      this.constructors.push(new ConstructorInvocation(constructorInvocationJSON));
    }

    let attributeInvocationsJSON = dependenceInfoJSON['attributes'];
    for (let attributeInvocationJSON of attributeInvocationsJSON) {
      this.attributes.push(new AttributeInvocation(attributeInvocationJSON));
    }
  }

  packages: PackageInvocation[] = [];
  attributes: AttributeInvocation[] = [];
  constructors: ConstructorInvocation[] = [];
  methods: MethodInvocation[] = [];
}
