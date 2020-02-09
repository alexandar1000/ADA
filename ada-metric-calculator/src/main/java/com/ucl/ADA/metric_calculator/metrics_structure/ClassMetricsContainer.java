package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ClassDependenceTree;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassMetricsContainer {

    private HashMap<String, RelationMetricValue> relationMetricValues = new HashMap<>();

    private ClassMetricValue classMetricValues = new ClassMetricValue();

//    if (relationMetricValues.containsKey(correspondingClass)) {
//        relationMetricValues.get(correspondingClass).setNumberOfPackageImportsIncoming(metricValue);
//    } else {
//        ClassMetricValue classMetricValueObject = new ClassMetricValue();
//        classMetricValueObject.setNumberOfPackageImportsIncoming(metricValue);
//        relationMetricValues.put(correspondingClass, classMetricValueObject);
//    }

    public void calculateNumberOfClassPackageImportsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
            }
        }
        this.classMetricValues.setNumberOfPackageImportsIncoming(metricValue);
    }

    public void calculateNumberOfPackageImportsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setNumberOfPackageImportsOutgoing(metricValue);
    }

    public void calculateNumberOfAttributeInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfAttributeInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsOutgoing(metricValue);
    }

    public void calculateNumberOfMethodInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfMethodInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsOutgoing(metricValue);
    }

    public void calculateNumberOfConstructorInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfConstructorInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsOutgoing(metricValue);
    }

    public void calculateBidirectionalNumberOfPackageImports(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
            }
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfPackageImports(metricValue);
    }

    public void calculateBidirectionalNumberOfAttributeInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfAttributeInvocations(metricValue);
    }

    public void calculateBidirectionalNumberOfMethodInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
            }
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfMethodInvocations(metricValue);
    }

    public void calculateBidirectionalNumberOfConstructorInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfConstructorInvocations(metricValue);
    }
}
