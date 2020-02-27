export class AttributeDeclaration {
  name: string;
  type: string;
  value: string;
  modifierTypes: string[];

  constructor(attributeDeclarationJSON) {
    this.name = attributeDeclarationJSON['name'];
    this.type = attributeDeclarationJSON['type'];
    this.value = attributeDeclarationJSON['value'];
    this.modifierTypes = attributeDeclarationJSON['modifierTypes'];
  }
}
