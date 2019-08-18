package org.github.kaninhop.parser.yaml.simple;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleYamlParserTest {

    private static final String WORKSPACE = "chrizantema-content";

    @Test
    public void test_yaml_parser() {
        AbstractParser parser = new SimpleYamlParser("/simple/test-content.yaml");
        DataModel dataModel = parser.getModel();

        assertEquals("2019", dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(4, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().size());
    }
}
