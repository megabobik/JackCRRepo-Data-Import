package org.github.kaninhop.parser.magnolia.yaml;

import com.google.common.io.Files;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Unimplemented
 */
public class MagnoliaYamlParser extends AbstractParser<MagnoliaYamlModel>{

    private static Logger logger = LoggerFactory.getLogger(MagnoliaYamlParser.class);

    private String fileName;

    public MagnoliaYamlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected MagnoliaYamlModel parseData() {
        final File yamlFile = new File(getClass().getResource(fileName).getFile());
        final Yaml yaml = new Yaml();

        String dataXml = "";
        try {
            dataXml = Files.toString(yamlFile, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("", e);
        }

        //removing all sv: namespace from elements and attributes
        dataXml = dataXml.replace("sv:", "");

        Map<String, Object> map = yaml.load(dataXml);

        return new MagnoliaYamlModel(map);
    }

    @Override
    protected IConverter getConverter() {
        return new MagnoliaYamlConverter();
    }
}