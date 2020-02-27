import {PassedParameter} from "./passed-parameter";

export interface ConstructorInvocation {
  name: string;
  passedParameters: PassedParameter[];
}
