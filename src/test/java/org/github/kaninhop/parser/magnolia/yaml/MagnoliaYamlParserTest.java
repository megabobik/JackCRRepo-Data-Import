package org.github.kaninhop.parser.magnolia.yaml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.magnolia.MagnoliaParserTestBase;
import org.junit.Test;

public class MagnoliaYamlParserTest extends MagnoliaParserTestBase {

    @Test
    public void test_yaml_parser(){
        AbstractParser parser = new MagnoliaYamlParser("/magnolia/fuzzylop-mgnl-content.yaml");
        DataModel dataModel = parser.getModel();

        test_root(dataModel);
    }

    @Test
    public void test_yaml_part_parser() {
        AbstractParser parser = new MagnoliaYamlParser("/magnolia/fuzzylop-mgnl-content.blogs.yaml");
        DataModel dataModel = parser.getModel();

        test_element(dataModel);
    }
}