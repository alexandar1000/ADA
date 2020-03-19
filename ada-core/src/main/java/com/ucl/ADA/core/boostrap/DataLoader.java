package com.ucl.ADA.core.boostrap;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ParserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class DataLoader implements CommandLineRunner {

//    private final String test_dir = "ada-parser/src/main/resources/simple";
    private final String test_dir = "ada-parser/src/main/resources/source_to_parse";

    private final ProjectStructureService projectStructureService;
    private final ParserServices parserServices;

    public DataLoader(ProjectStructureService projectStructureService, ParserServices parserServices) {
        this.projectStructureService = projectStructureService;
        this.parserServices = parserServices;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO: This is a 'integration testing call and should be omitted for ow'
//        loadData();
    }

    private void loadData() throws FileNotFoundException {
        ProjectStructure projectStructureToSave = parserServices.parseRepository(test_dir);
        projectStructureService.save(projectStructureToSave);

//        ProjectStructure projectStructureRetrieved = projectStructureService.findById(1L);


    }
}
