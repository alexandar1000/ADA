import {ClassStructureInterface} from "./class-structure-interface";
import {CustomMapInterface} from "./custom-map-interface";

export interface ProjectStructureInterface {
  classStructures: CustomMapInterface<ClassStructureInterface>
}
