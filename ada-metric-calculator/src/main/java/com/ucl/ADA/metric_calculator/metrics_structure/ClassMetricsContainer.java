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
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
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
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
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
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
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
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
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
}
