package org.github.kaninhop.parser.xml.magnolia;

import com.google.common.io.Files;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * Parser for Magnolia CMS export XML
 */
public class MagnoliaXmlParser extends AbstractParser<MagnoliaXmlModel> {

    private static Logger logger = LoggerFactory.getLogger(MagnoliaXmlParser.class);

    private final String fileName;

    public MagnoliaXmlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected MagnoliaXmlModel parseData() {
        final File xmlFile = new File(getClass().getResource(fileName).getFile());

        String dataXml = "";
        try {
            dataXml = Files.toString(xmlFile, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("", e);
        }

        //removing all sv: namespace from elements and attributes
        dataXml = dataXml.replace("sv:", "");

        MagnoliaXmlModel magnoliaXmlModel = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MagnoliaXmlModel.class);
            magnoliaXmlModel = (MagnoliaXmlModel) jaxbContext.createUnmarshaller().unmarshal(new StringReader(dataXml));
        } catch (JAXBException e) {
            logger.error("Can't parse data", e);
        }
        return magnoliaXmlModel;
    }

    @Override
    protected IConverter getConverter() {
        return new MagnoliaXmlConverter();
    }
}