package org.github.kaninhop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;

public class NodeTreeViewer
{
    private static Logger logger = LoggerFactory.getLogger(NodeTreeViewer.class);

    public void showTree(Node node) throws RepositoryException {
        showNode(node, 0);
    }

    private void showNode(Node node, int depth) throws RepositoryException {
        logger.info(getSpace(depth) + String.format("|---> %s | %s", node.getPath(), getNodeType(node.getProperties())));

        int newDepth = ++depth;

        PropertyIterator propertyIterator1 = node.getProperties();
        while(propertyIterator1.hasNext()) {
            Property prop = propertyIterator1.nextProperty();
            logger.info(getSpace(newDepth) + String.format("|->> %s | %s  " , prop.getName(), prop.getValue().getString()));
        }

        NodeIterator nodeIterator = node.getNodes();
        while(nodeIterator.hasNext()) {
            Node nod = nodeIterator.nextNode();
            showNode(nod, newDepth);
        }
    }

    private String getSpace(int depth) {
        StringBuilder buffer = new StringBuilder();
        for (int i =0; i< depth; i++) {
            buffer.append("  ");
        }
        return buffer.toString();
    }

    private String getNodeType(PropertyIterator propertyIterator) throws RepositoryException {
        String type = "";
        while(propertyIterator.hasNext()) {
            Property prop = propertyIterator.nextProperty();
            if( prop.getName().equalsIgnoreCase("jcr:primaryType")) {
                type = prop.getValue().getString();
            }
        }
        return type;
    }
}