import {ParameterDeclaration} from "./parameter-declaration";

export interface ConstructorDeclaration {
  name: string;
  modifierTypes: string[];
  parameters: ParameterDeclaration[];
}
