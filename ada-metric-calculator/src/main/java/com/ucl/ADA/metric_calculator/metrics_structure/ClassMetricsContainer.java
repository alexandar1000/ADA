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

    public void calculateNumberOfClassPackageImportsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setNumberOfPackageImportsOutgoing(metricValue);
    }

    public void calculateNumberOfClassAttributeInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfClassAttributeInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsOutgoing(metricValue);
    }

    public void calculateNumberOfClassMethodInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfClassMethodInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsOutgoing(metricValue);
    }

    public void calculateNumberOfClassConstructorInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsIncoming(metricValue);
    }

    public void calculateNumberOfClassConstructorInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsOutgoing(metricValue);
    }

    public void calculateBidirectionalNumberOfClassPackageImports(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
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

    public void calculateBidirectionalNumberOfClassAttributeInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
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

    public void calculateBidirectionalNumberOfClassMethodInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
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

    public void calculateBidirectionalNumberOfClassConstructorInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
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

    public void calculateNumberOfRelationPackageImportsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();


                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfPackageImportsIncoming(metricValue);
            }
        }
    }
}
