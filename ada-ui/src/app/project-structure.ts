import {ClassStructure} from "./class-structure";
import {CustomMap} from "./custom-map";

export interface ProjectStructure {
  classStructures: CustomMap<ClassStructure>
}
