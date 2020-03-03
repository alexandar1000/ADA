export class PassedParameter {
  constructor(passedParameterJSON: any) {
    this.name = passedParameterJSON['name'];
  }

  name: string;
}
