package org.github.kaninhop.parser.simple;

import org.github.kaninhop.jcr.DataModel;

import static org.junit.Assert.assertEquals;

public abstract class SimpleParserTestBase {

    private final int NUMBER_OF_WORKSPACES = 5;
    private final int NUMBER_OF_PROPERTIES_IN_BLOG = 4;

    private final String CONTENT_TYPE = "jcr:content";
    private final String CONTENTNODE_TYPE = "jcr:contentNode";
    private final String STRING_TYPE = "String";
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

    private final String CATEGORY_PROPERTY_NAME = "category";
    private final String GROUP_PROPERTY_NAME = "group";
    private final String DESCRIPTION_PROPERTY_NAME = "description";
    private final String THEME_PROPERTY_NAME = "theme1";

    private final String CATEGORY_PROPERTY_VALUE = "c673-a68";
    private final String GROUP_PROPERTY_VALUE = "d45a-y23";
    private final String DESCRIPTION_PROPERTY_VALUE = "microblog";
    private final String THEME_PROPERTY_VALUE = "themeTest";

    protected void test_all(DataModel dataModel){
        assertEquals(NUMBER_OF_WORKSPACES, dataModel.getWorkspaces().size());

        assertEquals(WORKSPACE_1, dataModel.getWorkspaces().get(0).getWorkspaceName());
        assertEquals(WORKSPACE_2, dataModel.getWorkspaces().get(1).getWorkspaceName());
        assertEquals(WORKSPACE_3, dataModel.getWorkspaces().get(2).getWorkspaceName());
        assertEquals(WORKSPACE_4, dataModel.getWorkspaces().get(3).getWorkspaceName());
        assertEquals(WORKSPACE_5, dataModel.getWorkspaces().get(4).getWorkspaceName());

        assertEquals(BLOG_NODE_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getName());
        assertEquals(null, dataModel.getWorkspaces().get(0).getNodes().get(0).getValue());
        assertEquals(CONTENT_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getType());

        assertEquals(YEAR_NODE_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(null, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getValue());
        assertEquals(YEAR_NODE_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getType());

        assertEquals(BLOG_NAME_NODE_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(null, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getValue());
        assertEquals(BLOG_NAME_NODE_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getType());

        assertEquals(NUMBER_OF_PROPERTIES_IN_BLOG, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().size());

        assertEquals(TAGS_NODE_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getName());
        assertEquals(null, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getValue());
        assertEquals(CONTENTNODE_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getType());

        assertEquals(CATEGORY_PROPERTY_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(1).getName());
        assertEquals(CATEGORY_PROPERTY_VALUE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(1).getValue());
        assertEquals(STRING_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(1).getType());

        assertEquals(GROUP_PROPERTY_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(2).getName());
        assertEquals(GROUP_PROPERTY_VALUE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(2).getValue());
        assertEquals(STRING_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(2).getType());

        assertEquals(DESCRIPTION_PROPERTY_NAME, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(3).getName());
        assertEquals(DESCRIPTION_PROPERTY_VALUE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(3).getValue());
        assertEquals(STRING_TYPE, dataModel.getWorkspaces().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(0).getNodes().get(3).getType());

        assertEquals(THEME_PROPERTY_NAME, dataModel.getWorkspaces().get(4).getNodes().get(0).getName());
        assertEquals(THEME_PROPERTY_VALUE, dataModel.getWorkspaces().get(4).getNodes().get(0).getValue());
        assertEquals(STRING_TYPE, dataModel.getWorkspaces().get(4).getNodes().get(0).getType());

    }
}