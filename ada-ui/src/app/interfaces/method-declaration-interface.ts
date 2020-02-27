import {ParameterDeclarationInterface} from "./parameter-declaration-interface";

export interface MethodDeclarationInterface {
  name: string;
  returnType: string;
  modifierTypes: string;
  parameters: ParameterDeclarationInterface[];
}
