import {ClassStructure} from "./class-structure";
import {ClassStructureInterface} from "../interfaces/class-structure-interface";

export class ProjectStructure {
  classStructures: Map<String, ClassStructure>;

  constructor(classStructures: any) {
    this.classStructures = new Map<String, ClassStructure>();
    for (let classStructureName in classStructures) {
      this.classStructures.set(classStructureName, new ClassStructure(classStructures[classStructureName]));
    }
  }
}
