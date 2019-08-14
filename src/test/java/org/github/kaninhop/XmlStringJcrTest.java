package org.github.kaninhop;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.net.URISyntaxException;

public class XmlStringJcrTest {

    private static Logger logger = LoggerFactory.getLogger(XmlStringJcrTest.class);

    @BeforeClass
    public static void beforeClass() {    }

    @Before
    public void before() {    }

    @Test
    public void test_node_from_xml() throws RepositoryException, IOException, URISyntaxException {
        JCRRepoDataImport asd = new JCRRepoDataImport();
    }

    @After
    public void after() {    }

    @AfterClass
    public static void afterClass() {    }
}