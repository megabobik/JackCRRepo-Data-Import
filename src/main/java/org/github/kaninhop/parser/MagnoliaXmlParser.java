package org.github.kaninhop.parser;

import com.google.common.io.Files;
import org.github.kaninhop.parser.magnolia.MagnoliaXmlConverter;
import org.github.kaninhop.parser.magnolia.MagnoliaXmlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.github.kaninhop.DataModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * Parser for Magnolia CMS export XML
 */
public class MagnoliaXmlParser implements IParser {

    private static Logger logger = LoggerFactory.getLogger(MagnoliaXmlParser.class);

    private final String fileName;

    public MagnoliaXmlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public DataModel getModel() {
        final File xmlFile = new File(getClass().getResource(fileName).getFile());
        final MagnoliaXmlConverter converter = new MagnoliaXmlConverter();

        String dataXml = "";
        try {
            dataXml = Files.toString(xmlFile, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("", e);
        }

        //removing all sv: namespace from elements and attributes
        dataXml = dataXml.replace("sv:", "");

        DataModel dataModel = null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(MagnoliaXmlNode.class);
            MagnoliaXmlNode magnoliaNodes = (MagnoliaXmlNode) jaxbContext.createUnmarshaller().unmarshal(new StringReader(dataXml));
            dataModel = converter.getDataModel(magnoliaNodes);
            dataXml.toString();
        } catch (JAXBException e) {
            logger.error("", e);
        }

        return dataModel;
    }
}