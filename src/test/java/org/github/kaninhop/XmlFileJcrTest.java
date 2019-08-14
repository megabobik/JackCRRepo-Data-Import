package org.github.kaninhop;

import nl.openweb.jcr.InMemoryJcrRepository;
import org.junit.*;
import org.github.kaninhop.parser.DefaultXmlParser;
import org.github.kaninhop.parser.IParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;
import java.io.IOException;
import java.net.URISyntaxException;

import static javax.jcr.query.Query.JCR_SQL2;
import static org.junit.Assert.assertEquals;
import static org.github.kaninhop.JCRRepoDataImport.ADMIN_CREDENTIALS;

public class XmlFileJcrTest {

    private static Logger logger = LoggerFactory.getLogger(XmlFileJcrTest.class);

    private final String CONTENT_TYPE = "jcr:content";
    private final String CONTENTNODE_TYPE = "jcr:contentNode";

    private final String CONTENT_WORKSPACE = "chrizantema-content";
    private final String BLOG_NODE_NAME = "/blogs";
    private final String BLOG_TITLE_TYPE = "cztm:content-object";
    private final String BLOG_DESCRIPTION_PROPERRTY_NAME = "description";
    private final String BLOG_DESCRIPTION_PROPERTY_VALUE = "ololo";

    private final String CATEGORY_TO_FIND = "c673-a68";
    private final String TAG_TO_FIND = "e687-e5a";

    private static InMemoryJcrRepository repository;

    @BeforeClass
    public static void beforeClass() throws RepositoryException, IOException, URISyntaxException {
        JCRRepoDataImport asd = new JCRRepoDataImport();
        IParser parser = new DefaultXmlParser("/testInput.xml");
        repository = asd.createRepositoryFromModel(parser.getModel());
    }

    @Before
    public void before() {    }

    @Test
    public void test_node_from_xml_file() throws RepositoryException, IOException, URISyntaxException {
        Session blogSession = repository.login(ADMIN_CREDENTIALS, CONTENT_WORKSPACE );
        QueryManager queryManager = blogSession.getWorkspace().getQueryManager();

        Query query = queryManager.createQuery("select p.* from ["+BLOG_TITLE_TYPE+"] as p " +
                " INNER JOIN ["+CONTENTNODE_TYPE+"] as k on ISCHILDNODE(k, p) " +
                " INNER JOIN ["+CONTENTNODE_TYPE+"] as n on ISCHILDNODE(n, k) " +
                " WHERE ISDESCENDANTNODE (p, '"+BLOG_NODE_NAME+"') " +
                " AND n.[tags]='"+ TAG_TO_FIND +"' "
                , JCR_SQL2);

        QueryResult queryResult =  query.execute();

        RowIterator rowIterator = queryResult.getRows();
//        while(rowIterator.hasNext()) {
//            Node adr = rowIterator.nextRow().getNode("p");
//            logger.warn(adr.getProperty("description").getString());
//        }
        assertEquals(1, rowIterator.getSize());
        assertEquals(BLOG_DESCRIPTION_PROPERTY_VALUE, rowIterator.nextRow().getNode("p").getProperty(BLOG_DESCRIPTION_PROPERRTY_NAME).getString());
    }

    @After
    public void after() {    }

    @AfterClass
    public static void afterClass() {
        repository.close();
    }
}