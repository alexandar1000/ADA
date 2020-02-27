import {PackageInvocation} from "./package-invocation";
import {AttributeInvocation} from "./attribute-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {MethodInvocation} from "./method-invocation";

export class DependenceInfo {
  constructor(dependenceInfoJSON: any) {
    for (let packageInvocationJSON of dependenceInfoJSON['packages']) {
      this.packages.push(new PackageInvocation(packageInvocationJSON));
    }
    for (let methodInvocationJSON of dependenceInfoJSON['methods']) {
      this.methods.push(new MethodInvocation(methodInvocationJSON));
    }
    for (let constructorInvocationJSON of dependenceInfoJSON['constructors']) {
      this.constructors.push(new ConstructorInvocation(constructorInvocationJSON));
    }
    for (let attributeInvocationJSON of dependenceInfoJSON['attributes']) {
      this.attributes.push(new AttributeInvocation(attributeInvocationJSON));
    }
  }

  packages: PackageInvocation[] = [];
  attributes: AttributeInvocation[] = [];
  constructors: ConstructorInvocation[] = [];
  methods: MethodInvocation[] = [];
}
