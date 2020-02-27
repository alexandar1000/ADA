import {PackageInvocation1} from "./package-invocation1";
import {AttributeInvocation1} from "./attribute-invocation1";
import {ConstructorInvocation1} from "./constructor-invocation1";
import {MethodInvocation1} from "./method-invocation1";

export interface DependenceInfo1 {
  packages: PackageInvocation1[];
  attributes: AttributeInvocation1[];
  constructors: ConstructorInvocation1[];
  methods: MethodInvocation1[];
}
