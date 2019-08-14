package org.github.kaninhop.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.github.kaninhop.DataModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Parser for default XML
 */
public class DefaultXmlParser implements IParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultXmlParser.class);

    private final String fileName;

    public DefaultXmlParser(String fileName){
        this.fileName = fileName;
    }

    @Override
    public DataModel getModel() {
        final File xmlFile = new File(getClass().getResource(fileName).getFile());

        DataModel dataModel = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataModel.class);
            dataModel = (DataModel) jaxbContext.createUnmarshaller().unmarshal(xmlFile);
        } catch (JAXBException e) {
            logger.error("", e);
        };

        return dataModel;
    }
}