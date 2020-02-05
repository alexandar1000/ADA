package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceTree {
    /**
     * Information about the invocations of the elements from the other classes from this class
     */
    private HashMap<String, ClassDependenceInformation> outgoingDependenceInfo = new HashMap<>();

    /**
     * Information about the invocations of elements from this class by the other classes
     */
    private HashMap<String, ClassDependenceInformation> incomingDependenceInfo = new HashMap<>();

}
