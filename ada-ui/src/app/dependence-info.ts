import {PackageInvocation} from "./package-invocation";
import {AttributeInvocation} from "./attribute-invocation";
import {ConstructorInvocation} from "./constructor-invocation";
import {MethodInvocation} from "./method-invocation";

export interface DependenceInfo {
  packages: PackageInvocation[];
  attributes: AttributeInvocation[];
  constructors: ConstructorInvocation[];
  methods: MethodInvocation[];
}
