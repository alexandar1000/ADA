import {PassedParameter} from "./passed-parameter";

export interface MethodInvocation {
  name: string;
  passedParameters: PassedParameter[];
}
