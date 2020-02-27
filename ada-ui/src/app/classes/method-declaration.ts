import {ParameterDeclaration} from "./parameter-declaration";

export class MethodDeclaration {
  name: string;
  returnType: string;
  modifierTypes: string;
  parameters: ParameterDeclaration[];
}
