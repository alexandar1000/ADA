import {ParameterDeclarationInterface} from "./parameter-declaration-interface";

export interface ConstructorDeclarationInterface {
  name: string;
  modifierTypes: string[];
  parameters: ParameterDeclarationInterface[];
}
