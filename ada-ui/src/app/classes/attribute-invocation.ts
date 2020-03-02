export class AttributeInvocation {
  constructor(globalDataJSON: any) {
    this.name = globalDataJSON['name']
  }

  name: string;
}
