package org.github.kaninhop.parser.magnolia;

import org.github.kaninhop.jcr.DataModel;

import static org.junit.Assert.assertEquals;

public abstract class MagnoliaParserTestBase {

    private final String MGNL_CONTENT_TYPE = "mgnl:content";
    private final String MGNL_CONTENTNODE_TYPE = "mgnl:contentNode";
    private final String STRING_TYPE = "String";
    private final String YEAR_NODE_TYPE = "mgnl:mellerud-content-year-object";
    private final String BLOG_NAME_NODE_TYPE = "mgnl:mellerud-content-object";
    private final String GROUP_NODE_TYPE = "cztm:group";

    private final String BLOG_NODE_NAME = "blogs";
    private final String YEAR_NODE_NAME = "2019";
    private final String BLOG_NAME_NODE_NAME = "blog1";
    private final String TAGS_NODE_NAME = "tags";
    private final String GROUP_NODE_NAME = "group1";

    private final String CATEGORY_PROPERTY_NAME = "category";
    private final String GROUP_PROPERTY_NAME = "group";
    private final String DESCRIPTION_PROPERTY_NAME = "description";

    private final String CATEGORY_PROPERTY_VALUE = "a58a7040-a0fa-407e-a069-532d9b06e513";
    private final String GROUP_PROPERTY_VALUE = "776c6baa-cb94-46b9-b784-aaee7a08144e";
    private final String DESCRIPTION_PROPERTY_VALUE = "callducks better than rabbits";

    protected void test_root(DataModel dataModel) {
        test_1(dataModel);

        assertEquals(GROUP_NODE_NAME,       dataModel.getWorkspaces().get(0).getNodes().get(1).getName());
        assertEquals(null,         dataModel.getWorkspaces().get(0).getNodes().get(1).getValue());
        assertEquals(GROUP_NODE_TYPE,       dataModel.getWorkspaces().get(0).getNodes().get(1).getType());
    }

    protected void test_element(DataModel dataModel) {
        test_1(dataModel);

    }

    protected void test_1(DataModel dataModel) {
        assertEquals(BLOG_NODE_NAME,            dataModel.getWorkspaces().get(0).getNodes().get(0).getName());
        assertEquals(null,             dataModel.getWorkspaces().get(0).getNodes().get(0).getValue());
        assertEquals(MGNL_CONTENT_TYPE,         dataModel.getWorkspaces().get(0).getNodes().get(0).getType());

        assertEquals(YEAR_NODE_NAME,            dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getName());
        assertEquals(null,             dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getValue());
        assertEquals(YEAR_NODE_TYPE,            dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getType());

        assertEquals(BLOG_NAME_NODE_NAME,       dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getName());
        assertEquals(null,             dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getValue());
        assertEquals(BLOG_NAME_NODE_TYPE,       dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getType());

        assertEquals(TAGS_NODE_NAME,            dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(11).getName());
        assertEquals(null,             dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(11).getValue());
        assertEquals(MGNL_CONTENTNODE_TYPE,     dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(11).getType());

        assertEquals(CATEGORY_PROPERTY_NAME,    dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(1).getName());
        assertEquals(CATEGORY_PROPERTY_VALUE,   dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(1).getValue());
        assertEquals(STRING_TYPE,               dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(1).getType());

        assertEquals(GROUP_PROPERTY_NAME,       dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(4).getName());
        assertEquals(GROUP_PROPERTY_VALUE,      dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(4).getValue());
        assertEquals(STRING_TYPE,               dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(4).getType());

        assertEquals(DESCRIPTION_PROPERTY_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(3).getName());
        assertEquals(DESCRIPTION_PROPERTY_VALUE,dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(3).getValue());
        assertEquals(STRING_TYPE,               dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(8).getNodes().get(5).getNodes().get(3).getType());
    }
}
