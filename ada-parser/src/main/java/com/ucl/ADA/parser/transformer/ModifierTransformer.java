package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.static_information.declaration_information.ModifierType;

import java.util.HashSet;
import java.util.Set;

class ModifierTransformer {

    /**
     * transfer a set of string modifiers to a set of ModifierType enum
     *
     * @param modifiers a set of string modifiers
     * @return a set of ModifierType enum
     */
    protected static Set<ModifierType> getModifierTypes(Set<String> modifiers) {
        Set<ModifierType> modifierTypes = new HashSet<>();

        if (modifiers.contains("public")) modifierTypes.add(ModifierType.PUBLIC);
        else if (modifiers.contains("private")) modifierTypes.add(ModifierType.PRIVATE);
        else if (modifiers.contains("protected")) modifierTypes.add(ModifierType.PROTECTED);
        else if (modifiers.contains("default")) modifierTypes.add(ModifierType.DEFAULT);
        else if (modifiers.contains("static")) modifierTypes.add(ModifierType.STATIC);
        else if (modifiers.contains("final")) modifierTypes.add(ModifierType.FINAL);
        else if (modifiers.contains("abstract")) modifierTypes.add(ModifierType.ABSTRACT);
        else modifierTypes.add(ModifierType.UNRESOLVED);

        return modifierTypes;
    }

}
