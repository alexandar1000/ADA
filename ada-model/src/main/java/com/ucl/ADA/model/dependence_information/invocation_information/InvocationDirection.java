package com.ucl.ADA.model.dependence_information.invocation_information;

/**
 * An enumerator which represents the direction of the dependence. It can be either an incoming invocation in the case
 * that an element is being invoked from the some class, or an outgoing invocation type if the element is invoked from
 * some class.
 */
public enum InvocationDirection {
    INCOMING, // A resource which I have is called from somewhere else
    OUTGOING // I call a resource which is somewhere else
}
