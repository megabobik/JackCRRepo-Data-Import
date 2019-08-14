package org.github.kaninhop.parser;

import org.junit.Test;
import org.github.kaninhop.DataModel;

import static org.junit.Assert.*;

public class MagnoliaXmlParserTest {

    @Test
    public void getModel() throws Exception {
        IParser parser = new MagnoliaXmlParser("/testMagnoliaExport.xml");
        DataModel dataModel = parser.getModel();

        assertEquals("2019", dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(4, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().size());
    }

}