package com.ucl.ADA.parser.parsed_data_placeholder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class MetricInformationPlaceholder {

    // For data and control flow coupling:
    private Integer invokesDataAmount = 0;      // number of input data parameters
    private ArrayList<String> invokesDataList = new ArrayList<>();

    private Integer invokesMethodsAmount = 0;   // number of input control parameters
    private ArrayList<String> invokesMethodsList = new ArrayList<>();

    private Integer invokedDataAmount = 0;     // number of output data parameters
    private ArrayList<String> invokedDataList = new ArrayList<>();

    private Integer invokedMethodsAmount = 0;  // number of output control parameters
    private ArrayList<String> invokedMethodsList = new ArrayList<>();

    // For global coupling:
    private Integer globalDataAmount = 0;    // number of global variables used as data
    private ArrayList<String> globalDataList = new ArrayList<>();

    private Integer globalMethodsAmount = 0; // number of global variables used as control
    private ArrayList<String> globalMethodsList = new ArrayList<>();

    // For environmental coupling:
    private Integer importedModulesAmount = 0; // number of modules called (fan-out)
    private ArrayList<String> importedModulesList = new ArrayList<>();

    private Integer exportedModulesAmount = 0; // number of modules calling the module under consideration (fan-in)
    private ArrayList<String> exportedModulesList = new ArrayList<>();

    public MetricInformationPlaceholder() {
    }
}
