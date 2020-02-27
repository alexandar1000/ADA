import {PackageInvocationInterface} from "./package-invocation-interface";
import {AttributeInvocationInterface} from "./attribute-invocation-interface";
import {ConstructorInvocationInterface} from "./constructor-invocation-interface";
import {MethodInvocationInterface} from "./method-invocation-interface";

export interface DependenceInfoInterface {
  packages: PackageInvocationInterface[];
  attributes: AttributeInvocationInterface[];
  constructors: ConstructorInvocationInterface[];
  methods: MethodInvocationInterface[];
}
