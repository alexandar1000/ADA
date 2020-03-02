import {PassedParameter} from "./passed-parameter";

export class MethodInvocation {
  constructor(globalMethodJSON: any) {
    this.name = globalMethodJSON['name'];
    for (let passedParameterJSON of globalMethodJSON['passedParameters']) {
      this.passedParameters.push(new PassedParameter(passedParameterJSON));
    }
  }

  name: string;
  passedParameters: PassedParameter[] = [];
}
