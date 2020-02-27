import {PassedParameter} from "./passed-parameter";
import {AttributeDeclaration} from "./attribute-declaration";
import {ParameterDeclaration} from "./parameter-declaration";

export class MethodInvocation {
  constructor(globalMethodJSON: any) {
    this.name = globalMethodJSON['name'];
    for (let passedParameterJSON of globalMethodJSON['passedParameters']) {
      this.passedParameters.push(new PassedParameter(passedParameterJSON));
    }
  }

  name: string;
  passedParameters: PassedParameter[];
}
