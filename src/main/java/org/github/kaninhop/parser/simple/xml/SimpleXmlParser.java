package org.github.kaninhop.parser.simple.xml;

import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Parser for default XML
 */
public class SimpleXmlParser extends AbstractParser<SimpleXmlModel> {

    private static Logger logger = LoggerFactory.getLogger(SimpleXmlParser.class);

    private final String fileName;

    public SimpleXmlParser(String fileName){
        this.fileName = fileName;
    }

    @Override
    protected SimpleXmlModel parseData() {
        final File xmlFile = new File(getClass().getResource(fileName).getFile());

        SimpleXmlModel defaultXmlModel = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SimpleXmlModel.class);
            defaultXmlModel = (SimpleXmlModel) jaxbContext.createUnmarshaller().unmarshal(xmlFile);
        } catch (JAXBException e) {
            logger.error("Can't parse data", e);
        }
        return defaultXmlModel;
    }

    @Override
    protected IConverter getConverter() {
        return new SimpleXmlConverter();
    }
}