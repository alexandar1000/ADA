import {ParameterDeclaration} from "./parameter-declaration";

export class MethodDeclaration {
  constructor(methodDeclarationJSON: any) {
    this.name = methodDeclarationJSON['name'];
    this.modifierTypes = methodDeclarationJSON['modifierTypes'];
    this.returnType = methodDeclarationJSON['returnType'];
    for (let parameterJSON of methodDeclarationJSON['parameters']) {
      this.parameters.push(new ParameterDeclaration(parameterJSON));
    }
  }

  name: string;
  returnType: string;
  modifierTypes: string;
  parameters: ParameterDeclaration[] = [];
}
