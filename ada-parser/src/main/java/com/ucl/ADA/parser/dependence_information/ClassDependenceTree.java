package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceTree {
    private HashMap<String, ClassDependenceInformation> invokesInfo = new HashMap<>();
    private HashMap<String, ClassDependenceInformation> invokedInfo = new HashMap<>();

    public ClassDependenceTree randomParsedDataOfClassPlaceholder(String currentClass, ArrayList<String> classNames) {
        Random random = new Random();
        for (String className : classNames) {
            if (!currentClass.equals(className) && random.nextBoolean()) {
                invokesInfo.put(className, new ClassDependenceInformation().assignRandomValues());
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "\n\t\tParsedDataOfClassPlaceholder{" +
                "\n\t\tinvokesInfo=" + invokesInfo +
                ", \n\t\tinvokedInfo=" + invokedInfo +
                "\n\t\t}";
    }
}
