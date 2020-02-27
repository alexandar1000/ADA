import {ClassStructure} from "./class-structure";

export class ProjectStructure {
  classStructures: Map<String, ClassStructure> = new Map<String, ClassStructure>();

  constructor(projectStructureJSON: JSON) {
    let classStructuresJSON = projectStructureJSON['classStructures'];
    for (let classStructureName in classStructuresJSON) {
      if (classStructuresJSON.hasOwnProperty(classStructureName)) {
        this.classStructures.set(classStructureName, new ClassStructure(classStructuresJSON[classStructureName]));
      }
    }
  }
}
