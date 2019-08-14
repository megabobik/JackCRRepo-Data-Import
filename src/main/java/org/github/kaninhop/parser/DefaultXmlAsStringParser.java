package org.github.kaninhop.parser;

import org.github.kaninhop.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

public class DefaultXmlAsStringParser implements IParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultXmlAsStringParser.class);

    private final String xmlString;

    public DefaultXmlAsStringParser(String xmlString) {
        this.xmlString = xmlString;
    }

    @Override
    public DataModel getModel() {
        DataModel dataModel = null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataModel.class);
            dataModel = (DataModel) jaxbContext.createUnmarshaller().unmarshal(new StringReader(xmlString));
        } catch (JAXBException e) {
            logger.error("", e);
        }
        return dataModel;
    }
}
