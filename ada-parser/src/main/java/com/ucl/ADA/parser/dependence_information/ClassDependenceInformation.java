package com.ucl.ADA.parser.dependence_information;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceInformation {

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


    public ClassDependenceInformation assignRandomValues() {
        Faker faker = new Faker();

        invokesDataAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < invokesDataAmount; i++) {
            invokesDataList.add(faker.rickAndMorty().character());
        }

        invokesMethodsAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < invokesMethodsAmount; i++) {
            invokesMethodsList.add(faker.rickAndMorty().character() + "()");
        }

        invokedDataAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < invokedDataAmount; i++) {
            invokedDataList.add(faker.rickAndMorty().character());
        }

        invokedMethodsAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < invokedMethodsAmount; i++) {
            invokedMethodsList.add(faker.rickAndMorty().character() + "()");
        }

        globalDataAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < globalDataAmount; i++) {
            globalDataList.add(faker.rickAndMorty().character());
        }

        globalMethodsAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < globalMethodsAmount; i++) {
             globalMethodsList.add(faker.rickAndMorty().character() + "()");
        }

        importedModulesAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < importedModulesAmount; i++) {
            importedModulesList.add(faker.rickAndMorty().character());
        }

        exportedModulesAmount = faker.number().numberBetween(0, 3);
        for (int i = 0; i < exportedModulesAmount; i++) {
            exportedModulesList.add(faker.rickAndMorty().character());
        }

        return this;
    }

    public ClassDependenceInformation assignHardcodedValues() {

        invokesDataAmount = 1;
        for (int i = 0; i < invokesDataAmount; i++) {
            invokesDataList.add( "invokesData" + i );
        }

        invokesMethodsAmount = 2;
        for (int i = 0; i < invokesMethodsAmount; i++) {
            invokesMethodsList.add( "invokesMethod" + i  + "()");
        }

        invokedDataAmount = 4;
        for (int i = 0; i < invokedDataAmount; i++) {
            invokedDataList.add( "invokedData" + i );
        }

        invokedMethodsAmount = 2;
        for (int i = 0; i < invokedMethodsAmount; i++) {
            invokedMethodsList.add( "invokedMethod" + i  + "()");
        }

        globalDataAmount = 2;
        for (int i = 0; i < globalDataAmount; i++) {
            globalDataList.add( "globalData" + i );
        }

        globalMethodsAmount = 0;

        importedModulesAmount = 5;
        for (int i = 0; i < importedModulesAmount; i++) {
            importedModulesList.add( "importedModule" + i );
        }

        exportedModulesAmount = 5;
        for (int i = 0; i < exportedModulesAmount; i++) {
            exportedModulesList.add( "exportedModule" + i );
        }

        return this;
    }

    public void addInvokesDataList(String dataName) {
        this.invokesDataList.add(dataName);
    }

    public void addInvokesMethodsList(String methodName) {
        this.invokesMethodsList.add(methodName);
    }

    public void addInvokedDataList(String dataName) {
        this.invokedDataList.add(dataName);
    }

    public void addInvokedMethodsList(String methodName) {
        this.invokedMethodsList.add(methodName);
    }

    public void addGlobalDataList(String dataName) {
        this.globalDataList.add(dataName);
    }

    public void addGlobalMethodsList(String methodName) {
        this.globalMethodsList.add(methodName);
    }

    public void addImportedModulesList(String moduleName) {
        this.importedModulesList.add(moduleName);
    }

    public void addExportedModulesList(String moduleName) {
        this.exportedModulesList.add(moduleName);
    }
}
