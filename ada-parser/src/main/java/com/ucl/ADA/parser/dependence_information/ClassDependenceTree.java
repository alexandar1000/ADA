package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceTree {
    private HashMap<String, ClassDependenceInformation> invokesDependenceInfo = new HashMap<>();
    private HashMap<String, ClassDependenceInformation> invokedDependenceInfo = new HashMap<>();

}
