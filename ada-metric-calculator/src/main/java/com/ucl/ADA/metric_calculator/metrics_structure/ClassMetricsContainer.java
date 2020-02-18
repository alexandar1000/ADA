package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.metrics.class_metrics.ClassMetricValue;
import com.ucl.ADA.model.metrics.relation_metrics.RelationMetricValue;
import com.ucl.ADA.model.project_structure.ProjectStructure;
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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassPackageImportsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getPackages().size();
            }
        }
        this.classMetricValues.setNumberOfPackageImportsIncoming(metricValue);
    }

    /**
     * Calculates the number of class package imports invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassPackageImportsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setNumberOfPackageImportsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassAttributeInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassAttributeInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setNumberOfAttributeInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class methods invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassMethodInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class method invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassMethodInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setNumberOfMethodInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class constructors invocations invoked by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassConstructorInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsIncoming(metricValue);
    }

    /**
     * Calculates the number of class constructor invocations invoked by the current class from other classes.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfClassConstructorInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setNumberOfConstructorInvocationsOutgoing(metricValue);
    }

    /**
     * Calculates the number of class package imports both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassPackageImports(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getPackages().size();
            }
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getPackages().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfPackageImports(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassAttributeInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getAttributes().size();
            }
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getAttributes().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfAttributeInvocations(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassMethodInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getMethods().size();
            }
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getMethods().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfMethodInvocations(metricValue);
    }

    /**
     * Calculates the number of class attribute invocations both invoked by the current class from other classes, and invoked
     * by other classes from the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfClassConstructorInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue = 0F;
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue += classStructure.getIncomingDependenceInfo().get(key).getConstructors().size();
            }
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue += classStructure.getOutgoingDependenceInfo().get(key).getConstructors().size();
            }
        }

        this.classMetricValues.setBidirectionalNumberOfConstructorInvocations(metricValue);
    }

    /**
     * Calculates the number of package imports for all of the linking classes, which are invoked by other classes from
     * the current class.
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationPackageImportsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getPackages().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationPackageImportsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getPackages().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationAttributeInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getAttributes().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationAttributeInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getAttributes().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationMethodInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getMethods().size();


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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationMethodInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getMethods().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationConstructorInvocationsIncoming(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getConstructors().size();


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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateNumberOfRelationConstructorInvocationsOutgoing(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getConstructors().size();

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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationPackageImports(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getPackages().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float previousMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfPackageImports();
                relationMetricValues.get(key).setBidirectionalNumberOfPackageImports(previousMetricValue + metricValue);
            }
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getPackages().size();
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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationAttributeInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getAttributes().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfAttributeInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfAttributeInvocations(currentMetricValue + metricValue);
            }
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getAttributes().size();
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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationMethodInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getMethods().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfMethodInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfMethodInvocations( currentMetricValue + metricValue);
            }
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getMethods().size();
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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void calculateBidirectionalNumberOfRelationConstructorInvocations(String correspondingClass, ProjectStructure projectStructure) {
        float metricValue;
        // Check if the corresponding class is present in the project dependence tree
        if (projectStructure.getClassStructures().containsKey(correspondingClass)) {
            // Get corresponding class from the ProjectDependenceTree
            ClassStructure classStructure = projectStructure.getClassStructures().get(correspondingClass);
            // For all of the relating classes get the corresponding metrics
            for (String key : classStructure.getOutgoingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getOutgoingDependenceInfo().get(key).getConstructors().size();
                // Check if the relation metrics for the class have already been computed
                if (!relationMetricValues.containsKey(key)) {
                    RelationMetricValue relationMetricValueObject = new RelationMetricValue();
                    relationMetricValues.put(key, relationMetricValueObject);
                }
                float currentMetricValue = relationMetricValues.get(key).getBidirectionalNumberOfConstructorInvocations();
                relationMetricValues.get(key).setBidirectionalNumberOfConstructorInvocations(currentMetricValue + metricValue);
            }
            for (String key : classStructure.getIncomingDependenceInfo().keySet()) {
                metricValue = (float) classStructure.getIncomingDependenceInfo().get(key).getConstructors().size();
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
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void computeAllClassMetrics(String correspondingClass, ProjectStructure projectStructure) {
        this.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, projectStructure);

        this.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, projectStructure);

        this.calculateNumberOfClassPackageImportsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, projectStructure);
    }

    /**
     * Computes all of the metrics for the relation between the current class and all of the relating classes..
     * @param correspondingClass the class in question
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    protected void computeAllRelationMetrics(String correspondingClass, ProjectStructure projectStructure) {
        this.calculateBidirectionalNumberOfRelationPackageImports(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationAttributeInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationConstructorInvocations(correspondingClass, projectStructure);
        this.calculateBidirectionalNumberOfRelationMethodInvocations(correspondingClass, projectStructure);

        this.calculateNumberOfRelationPackageImportsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationAttributeInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationConstructorInvocationsOutgoing(correspondingClass, projectStructure);
        this.calculateNumberOfRelationMethodInvocationsOutgoing(correspondingClass, projectStructure);

        this.calculateNumberOfRelationPackageImportsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationAttributeInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationConstructorInvocationsIncoming(correspondingClass, projectStructure);
        this.calculateNumberOfRelationMethodInvocationsIncoming(correspondingClass, projectStructure);
    }
}
