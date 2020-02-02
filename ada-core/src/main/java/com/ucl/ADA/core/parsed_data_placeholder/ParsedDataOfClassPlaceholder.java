package com.ucl.ADA.core.parsed_data_placeholder;

public class ParsedDataOfClassPlaceholder {
//    For data and control flow coupling:
    private Integer dataInput = 0;      // number of input data parameters
    private Integer controlInput = 0;   // number of input control parameters
    private Integer dataOutput = 0;     // number of output data parameters
    private Integer controlOutput = 0;  // number of output control parameters

//    For global coupling:
    private Integer globalDataVariables = 0;    // number of global variables used as data
    private Integer globalControlVariables = 0; // number of global variables used as control

//    For environmental coupling:
    private Integer importedModules = 0; // number of modules called (fan-out)
    private Integer moduleInvokations = 0; // number of modules calling the module under consideration (fan-in)
}
