package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ClassDependenceTree;
import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassMetricsContainer {

    /**
     * All of the metric values for the link between the current class and the linking classes.
     */
    private HashMap<String, RelationMetricValue> relationMetricValues = new HashMap<>();

    /**
     * The metrics corresponding to the current class.
     */
    private ClassMetricValue classMetricValues = new ClassMetricValue();


    /**
     * Calculates the number of class package imports invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassPackageImportsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
            }
        }
        this.classMetricValues.setNumberOfPackageImportsIncoming(metricValue);
    }

    /**
     * Calculates the number of class package imports invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassPackageImportsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setNumberOfPackageImportsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassAttributeInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassAttributeInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class methods invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassMethodInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class method invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassMethodInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class constructors invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassConstructorInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class constructor invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassConstructorInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue += classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class package imports both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassPackageImports(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
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

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassAttributeInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
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

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassMethodInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
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

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassConstructorInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue = 0F;
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

    /**
     * Calculates the number of package imports for all of the linking classes, which are invoked by other classes from
     * the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationPackageImportsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
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

    /**
     * Calculates the number of package imports for all of the linking classes, which are invoked by the current
     * class from the other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationPackageImportsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();

                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfPackageImportsOutgoing(metricValue);
            }
        }
    }

    /**
     * Calculates the number of attribute invocations for all of the linking classes, which are invoked by other classes
     * from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationAttributeInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();

                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfAttributeInvocationsIncoming(metricValue);
            }
        }
    }

    /**
     * Calculates the number of attribute invocations for all of the linking classes, which are invoked by the current
     * class from the other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationAttributeInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();

                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfAttributeInvocationsOutgoing(metricValue);
            }
        }
    }

    /**
     * Calculates the number of method invocations for all of the linking classes, which are invoked by other classes
     * from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationMethodInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();


                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfMethodInvocationsIncoming(metricValue);
            }
        }
    }

    /**
     * Calculates the number of method invocations for all of the linking classes, which are invoked by the current
     * class from the other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationMethodInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();

                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfMethodInvocationsOutgoing(metricValue);
            }
        }
    }

    /**
     * Calculates the number of constructor invocations for all of the linking classes, which are invoked by other classes
     * from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationConstructorInvocationsIncoming(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();


                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfConstructorInvocationsIncoming(metricValue);
            }
        }
    }

    /**
     * Calculates the number of constructors invocations for all of the linking classes, which are invoked by the current
     * class from the other classes.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationConstructorInvocationsOutgoing(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();

                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                relationMetricValues.get(key).setNumberOfConstructorInvocationsOutgoing(metricValue);
            }
        }
    }

    /**
     * Calculates the number of package imports for all of the linking classes, both invoked by the current class from
     * other classes, and invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationPackageImports(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getPackages().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float previousMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfPackageImports();
                relationMetricValues.get(key).setBidirectionalNumberOfPackageImports(previousMetricValue + metricValue);
            }
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getPackages().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float previousMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfPackageImports();
                relationMetricValues.get(key).setBidirectionalNumberOfPackageImports(previousMetricValue + metricValue);
            }
        }
    }

    /**
     * Calculates the number of class attribute invocations for all of the linking classes, both invoked by the current
     * class from other classes, and invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationAttributeInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getAttributes().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfAttributeInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfAttributeInvocations(currentMetricValue + metricValue);
            }
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getAttributes().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfAttributeInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfAttributeInvocations(currentMetricValue + metricValue);
            }
        }
    }

    /**
     * Calculates the number of class method invocations for all of the linking classes, both invoked by the current
     * class from other classes, and invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationMethodInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getMethods().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfMethodInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfMethodInvocations( currentMetricValue + metricValue);
            }
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getMethods().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfMethodInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfMethodInvocations(currentMetricValue + metricValue);
            }
        }
    }

    /**
     * Calculates the number of class constructor invocations for all of the linking classes, both invoked by the current
     * class from other classes, and invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationConstructorInvocations(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectDependenceTree.getClassDependenceTrees().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassDependenceTree classDependenceTree = projectDependenceTree.getClassDependenceTrees().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classDependenceTree.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getOutgoingDependenceInfo().get(key).getConstructors().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfConstructorInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfConstructorInvocations(currentMetricValue + metricValue);
            }
            for (String key : classDependenceTree.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classDependenceTree.getIncomingDependenceInfo().get(key).getConstructors().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfConstructorInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfConstructorInvocations(currentMetricValue + metricValue);
            }
        }
    }

    /**
     * Computes all of the metrics for a class.
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void computeAllClassMetrics(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        this.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, projectDependenceTree);

        this.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, projectDependenceTree);

        this.calculateNumberOfClassPackageImportsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, projectDependenceTree);
    }

    /**
     * Computes all of the metrics for the relation between the current class and all of the relating classes..
     * @param correspondingClass the class in question
     * @param projectDependenceTree corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void computeAllRelationMetrics(String correspondingClass, ProjectDependenceTree projectDependenceTree) {
        this.calculateBidirectionalNumberOfRelationPackageImports(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfRelationAttributeInvocations(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfRelationConstructorInvocations(correspondingClass, projectDependenceTree);
        this.calculateBidirectionalNumberOfRelationMethodInvocations(correspondingClass, projectDependenceTree);

        this.calculateNumberOfRelationPackageImportsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationAttributeInvocationsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationConstructorInvocationsOutgoing(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationMethodInvocationsOutgoing(correspondingClass, projectDependenceTree);

        this.calculateNumberOfRelationPackageImportsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationAttributeInvocationsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationConstructorInvocationsIncoming(correspondingClass, projectDependenceTree);
        this.calculateNumberOfRelationMethodInvocationsIncoming(correspondingClass, projectDependenceTree);
    }
}
