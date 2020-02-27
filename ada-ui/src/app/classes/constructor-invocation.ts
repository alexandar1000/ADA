import {PassedParameter} from "./passed-parameter";

export class ConstructorInvocation {
  constructor(externalConstructorInvocationsJSON: any) {
    this.name = externalConstructorInvocationsJSON['name'];
    for (let passedParameterJSON of externalConstructorInvocationsJSON['passedParameters']) {
      this.passedParameters.push(new PassedParameter(passedParameterJSON));
    }
  }

  name: string;
  passedParameters: PassedParameter[] = [];
}
