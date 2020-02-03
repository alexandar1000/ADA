package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceTree {
    private HashMap<String, ClassDependenceInformation> invokesDependenceInfo = new HashMap<>();
    private HashMap<String, ClassDependenceInformation> invokedDependenceInfo = new HashMap<>();

    public ClassDependenceTree randomParsedDataOfClassPlaceholder(String currentClass, ArrayList<String> classNames) {
        Random random = new Random();
        for (String className : classNames) {
            if (!currentClass.equals(className) && random.nextBoolean()) {
                invokesDependenceInfo.put(className, new ClassDependenceInformation().assignRandomValues());
            }
        }

        return this;
    }
}
