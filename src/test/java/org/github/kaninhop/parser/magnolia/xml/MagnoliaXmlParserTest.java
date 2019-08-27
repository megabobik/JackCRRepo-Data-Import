package org.github.kaninhop.parser.magnolia.xml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.magnolia.MagnoliaParserTestBase;
import org.junit.Test;

public class MagnoliaXmlParserTest extends MagnoliaParserTestBase{

    @Test
    public void test_xml_parser() {
        AbstractParser parser = new MagnoliaXmlParser("/magnolia/fuzzylop-mgnl-content.xml");
        DataModel dataModel = parser.getModel();

        test_root(dataModel);
    }

    @Test
    public void test_xml_part_parser() {
        AbstractParser parser = new MagnoliaXmlParser("/magnolia/fuzzylop-mgnl-content.blogs.xml");
        DataModel dataModel = parser.getModel();

        test_element(dataModel);
    }
}