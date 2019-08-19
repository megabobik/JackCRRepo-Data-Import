package org.github.kaninhop.parser.simple.yaml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.simple.SimpleParserTestBase;
import org.junit.Test;

public class SimpleYamlParserTest extends SimpleParserTestBase {

    @Test
    public void test_yaml_parser() {
        AbstractParser parser = new SimpleYamlParser("/simple/test-content.yaml");
        DataModel dataModel = parser.getModel();

        test_all(dataModel);
    }
}
