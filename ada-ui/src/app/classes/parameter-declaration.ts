export class ParameterDeclaration {
  name: string;
  type: string;

  constructor(parameterDeclarationJSON) {
    this.name = parameterDeclarationJSON['name'];
    this.type = parameterDeclarationJSON['type'];
  }
}
