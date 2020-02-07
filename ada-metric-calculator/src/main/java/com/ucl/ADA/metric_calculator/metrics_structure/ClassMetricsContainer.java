package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ClassDependenceTree;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassMetricsContainer {

    private HashMap<String, MetricValue> metricValues = new HashMap<>();

    public void calculateNumberOfPackageImportsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfPackageImportsIncoming(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfPackageImportsIncoming(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfPackageImportsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfPackageImportsOutgoing(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfPackageImportsOutgoing(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfAttributeInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfAttributeInvocationsIncoming(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfAttributeInvocationsIncoming(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfAttributeInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfAttributeInvocationsOutgoing(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfAttributeInvocationsOutgoing(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfMethodInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfMethodInvocationsIncoming(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfMethodInvocationsIncoming(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfMethodInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfMethodInvocationsOutgoing(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfMethodInvocationsOutgoing(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfConstructorInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfConstructorInvocationsIncoming(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfConstructorInvocationsIncoming(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }

    public void calculateNumberOfConstructorInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        Float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setNumberOfConstructorInvocationsOutgoing(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setNumberOfConstructorInvocationsOutgoing(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
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

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setBidirectionalNumberOfPackageImports(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setBidirectionalNumberOfPackageImports(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
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

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setBidirectionalNumberOfAttributeInvocations(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setBidirectionalNumberOfAttributeInvocations(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
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

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setBidirectionalNumberOfMethodInvocations(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setBidirectionalNumberOfMethodInvocations(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
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

        if (metricValues.containsKey(correspondingClass)) {
            metricValues.get(correspondingClass).setBidirectionalNumberOfConstructorInvocations(metricValue);
        } else {
            MetricValue metricValueObject = new MetricValue();
            metricValueObject.setBidirectionalNumberOfConstructorInvocations(metricValue);
            metricValues.put(correspondingClass, metricValueObject);
        }
    }
}
