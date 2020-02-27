import {ParameterDeclaration} from "./parameter-declaration";

export interface MethodDeclaration {
  name: string;
  returnType: string;
  modifierTypes: string;
  parameters: ParameterDeclaration[];
}
