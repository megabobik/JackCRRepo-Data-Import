package org.github.kaninhop.parser.simple.xml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.simple.SimpleParserTestBase;
import org.junit.Test;

public class SimpleXmlParserTest extends SimpleParserTestBase {

    @Test
    public void test_simple_parser() {
        AbstractParser parser = new SimpleXmlParser("/simple/test-content.xml");
        DataModel dataModel = parser.getModel();

        test_all(dataModel);
    }
}