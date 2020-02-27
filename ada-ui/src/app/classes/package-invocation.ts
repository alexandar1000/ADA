export class PackageInvocation {
  constructor(externalPackageInvocationsJSON: any) {
    this.name = externalPackageInvocationsJSON['name'];
  }

  name: string;
}
