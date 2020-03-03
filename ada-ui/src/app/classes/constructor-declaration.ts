import {ParameterDeclaration} from "./parameter-declaration";

export class ConstructorDeclaration {
  constructor(constructorDeclarationJSON: any) {
    this.name = constructorDeclarationJSON['name'];
    this.modifierTypes = constructorDeclarationJSON['modifierTypes'];
    for (let parameterJSON of constructorDeclarationJSON['parameters']) {
      this.parameters.push(new ParameterDeclaration(parameterJSON));
    }
  }

  name: string;
  modifierTypes: string[] = [];
  parameters: ParameterDeclaration[] = [];


}
