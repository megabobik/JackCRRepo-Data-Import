package org.github.kaninhop.parser.magnolia.yaml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.magnolia.MagnoliaParserTestBase;
import org.junit.Test;

public class MagnoliaYamlParserTest extends MagnoliaParserTestBase {

    @Test
    public void test_parser(){
        AbstractParser parser = new MagnoliaYamlParser("/magnolia/fuzzylop-mgnl-content.yaml");
        DataModel dataModel = parser.getModel();

        test_all(dataModel);
    }
}