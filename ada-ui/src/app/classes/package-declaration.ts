export class PackageDeclaration {
  constructor(classDeclarationElementJSON: any) {
    if (classDeclarationElementJSON != null) {
      this.name = classDeclarationElementJSON['name'];
    } else {
      this.name = "";
    }
  }

  name: string;
}
