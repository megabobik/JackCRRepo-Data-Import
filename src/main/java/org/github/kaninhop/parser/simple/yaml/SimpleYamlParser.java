package org.github.kaninhop.parser.simple.yaml;

import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * Parse for yaml files
 */
public class SimpleYamlParser extends AbstractParser<SimpleYamlModel> {

    private static Logger logger = LoggerFactory.getLogger(SimpleYamlParser.class);

    private String fileName;

    public SimpleYamlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected SimpleYamlModel parseData() {
        final File yamlFile = new File(getClass().getResource(fileName).getFile());
        Yaml yaml = new Yaml();

        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(yamlFile);
        } catch (FileNotFoundException e) {
            logger.error("Can't find a file", e);
        }

        Map<String, Object> map = yaml.load(inputStream);

        return new SimpleYamlModel(map);
    }

    @Override
    protected IConverter getConverter() {
        return new SimpleYamlConverter();
    }
}