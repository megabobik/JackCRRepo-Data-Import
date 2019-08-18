package org.github.kaninhop.parser.xml.simple;

import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

public class SimpleXmlAsStringParser extends AbstractParser<SimpleXmlModel> {

    private static Logger logger = LoggerFactory.getLogger(SimpleXmlAsStringParser.class);

    private final StringReader stringReader;

    public SimpleXmlAsStringParser(String xmlString) {
        this.stringReader = new StringReader(xmlString);
    }

    public SimpleXmlAsStringParser(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    @Override
    protected SimpleXmlModel parseData() {
        SimpleXmlModel xmlModel = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SimpleXmlModel.class);
            xmlModel = (SimpleXmlModel) jaxbContext.createUnmarshaller().unmarshal(stringReader);
        } catch (JAXBException e) {
            logger.error("Can't parse data", e);
        }
        return xmlModel;
    }

    @Override
    protected IConverter getConverter() {
        return new SimpleXmlConverter();
    }
}