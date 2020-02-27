import {PassedParameterInterface} from "./passed-parameter-interface";

export interface MethodInvocationInterface {
  name: string;
  passedParameters: PassedParameterInterface[];
}
