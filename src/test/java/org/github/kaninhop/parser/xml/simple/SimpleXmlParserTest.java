package org.github.kaninhop.parser.xml.simple;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleXmlParserTest {

    @Test
    public void test_simple_parser() {
        AbstractParser parser = new SimpleXmlParser("/simple/test-content.xml");
        DataModel dataModel = parser.getModel();

        assertEquals("2019", dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(4, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().size());
    }
}