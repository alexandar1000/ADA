package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransformerTest {

    private Transformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new Transformer();
    }

    @Test
    void transform() {
        String src_dir = System.getProperty("user.dir")+"/src/main/resources/source_to_parse";
        assert transformer.transform(src_dir).getClass() == ProjectStructure.class;
    }
}
