package org.github.kaninhop.jcr;

import nl.openweb.jcr.InMemoryJcrRepository;
import org.github.kaninhop.parser.AbstractParser;
import org.github.kaninhop.parser.simple.xml.SimpleXmlParser;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.io.IOException;
import java.net.URISyntaxException;

import static javax.jcr.query.Query.JCR_SQL2;
import static org.github.kaninhop.Constants.ADMIN_CREDENTIALS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JCRRepoDataImportTest {

    private final int NUMBER_OF_PROPERTIES_IN_BLOG = 4;

    private final String CONTENT_TYPE = "jcr:content";
    private final String CONTENTNODE_TYPE = "jcr:contentNode";
    private final String YEAR_NODE_TYPE = "cztm:year-object";
    private final String BLOG_NAME_NODE_TYPE = "cztm:content-object";
    private final String GROUP_NODE_TYPE = "cztm:group";

    private final String WORKSPACE_1 = "chrizantema-content";
    private final String WORKSPACE_2 = "groups";
    private final String WORKSPACE_3 = "categories";
    private final String WORKSPACE_4 = "tags";
    private final String WORKSPACE_5 = "themes";

    private final String BLOG_NODE_NAME = "blogs";
    private final String YEAR_NODE_NAME = "2019";
    private final String BLOG_NAME_NODE_NAME = "Blog1";
    private final String TAGS_NODE_NAME = "tags";
    private final String GROUP_NODE_NAME = "group1";

    private final String CATEGORY_PROPERTY_NAME = "category";
    private final String GROUP_PROPERTY_NAME = "group";
    private final String DESCRIPTION_PROPERTY_NAME = "description";
    private final String THEME_PROPERTY_NAME = "theme1";

    private final String CATEGORY_PROPERTY_VALUE = "c673-a68";
    private final String GROUP_PROPERTY_VALUE = "d45a-y23";
    private final String DESCRIPTION_PROPERTY_VALUE = "microblog";
    private final String THEME_PROPERTY_VALUE = "themeTest";

    private static InMemoryJcrRepository repository;

    @BeforeClass
    public static void beforeClass() throws RepositoryException, IOException, URISyntaxException {
        JCRRepoDataImport dataImport = new JCRRepoDataImport();
        AbstractParser parser = new SimpleXmlParser("/simple/test-content.xml");
        repository = dataImport.createRepositoryFromModel(parser.getModel());
    }

    @Before
    public void before() {    }

    @Test
    public void test_node_from_xml_file() throws RepositoryException, IOException, URISyntaxException {
        Session session = repository.login(ADMIN_CREDENTIALS, WORKSPACE_1);
        QueryManager queryManager = session.getWorkspace().getQueryManager();

        Query query = queryManager.createQuery("select p.* from ["+CONTENT_TYPE+"] as p ", JCR_SQL2);
        QueryResult queryResult =  query.execute();

        assertNotNull(repository.login(ADMIN_CREDENTIALS, WORKSPACE_1));
        assertNotNull(repository.login(ADMIN_CREDENTIALS, WORKSPACE_2));
        assertNotNull(repository.login(ADMIN_CREDENTIALS, WORKSPACE_3));
        assertNotNull(repository.login(ADMIN_CREDENTIALS, WORKSPACE_4));
        assertNotNull(repository.login(ADMIN_CREDENTIALS, WORKSPACE_5));

        NodeIterator nodeIterator = queryResult.getNodes();

        assertEquals(1, nodeIterator.getSize());

        Node node = nodeIterator.nextNode();

        assertEquals(BLOG_NODE_NAME, node.getName());
        assertEquals(CONTENT_TYPE, node.getProperties().nextProperty().getString());

        node = node.getNodes().nextNode();

        assertEquals(YEAR_NODE_NAME, node.getName());
        assertEquals(YEAR_NODE_TYPE, node.getProperties().nextProperty().getString());

        node = node.getNodes().nextNode();

        assertEquals(BLOG_NAME_NODE_NAME, node.getName());
        assertEquals(BLOG_NAME_NODE_TYPE, node.getProperties("jcr:*").nextProperty().getString());

        assertEquals(NUMBER_OF_PROPERTIES_IN_BLOG, node.getProperties().getSize());

        Node node1 = node.getNodes().nextNode();

        assertEquals(TAGS_NODE_NAME, node1.getName());
        assertEquals(CONTENTNODE_TYPE, node1.getProperties().nextProperty().getString());

        assertEquals(CATEGORY_PROPERTY_NAME, node.getProperties(CATEGORY_PROPERTY_NAME).nextProperty().getName());
        assertEquals(CATEGORY_PROPERTY_VALUE, node.getProperties(CATEGORY_PROPERTY_NAME).nextProperty().getString());

        assertEquals(GROUP_PROPERTY_NAME, node.getProperties(GROUP_PROPERTY_NAME).nextProperty().getName());
        assertEquals(GROUP_PROPERTY_VALUE, node.getProperties(GROUP_PROPERTY_NAME).nextProperty().getString());

        assertEquals(DESCRIPTION_PROPERTY_NAME, node.getProperties(DESCRIPTION_PROPERTY_NAME).nextProperty().getName());
        assertEquals(DESCRIPTION_PROPERTY_VALUE, node.getProperties(DESCRIPTION_PROPERTY_NAME).nextProperty().getString());

        session = repository.login(ADMIN_CREDENTIALS, WORKSPACE_2);

        Node node2 = session.getRootNode();

        assertEquals(GROUP_NODE_NAME, node2.getNodes(GROUP_NODE_NAME).nextNode().getName());
        assertEquals(GROUP_NODE_TYPE, node2.getNodes(GROUP_NODE_NAME).nextNode().getProperties("jcr:*").nextProperty().getString());

        session = repository.login(ADMIN_CREDENTIALS, WORKSPACE_5);

        Node node3 = session.getRootNode();

        assertEquals(THEME_PROPERTY_NAME, node3.getProperties(THEME_PROPERTY_NAME).nextProperty().getName());
        assertEquals(THEME_PROPERTY_VALUE, node3.getProperties(THEME_PROPERTY_NAME).nextProperty().getString());
    }

    @AfterClass
    public static void afterClass() {
        repository.close();
    }
}