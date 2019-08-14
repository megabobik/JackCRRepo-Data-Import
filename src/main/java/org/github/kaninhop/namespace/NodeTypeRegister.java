package org.github.kaninhop.namespace;

import org.apache.jackrabbit.core.SessionImpl;
import org.apache.jackrabbit.core.nodetype.NodeTypeDefinitionImpl;
import org.apache.jackrabbit.spi.Name;
import org.apache.jackrabbit.spi.NameFactory;
import org.apache.jackrabbit.spi.QNodeDefinition;
import org.apache.jackrabbit.spi.QPropertyDefinition;
import org.apache.jackrabbit.spi.commons.QNodeTypeDefinitionImpl;
import org.apache.jackrabbit.spi.commons.name.NameFactoryImpl;

import javax.jcr.NamespaceRegistry;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFactory;
import javax.jcr.nodetype.NodeTypeDefinition;
import javax.jcr.nodetype.NodeTypeManager;

import static org.apache.jackrabbit.spi.commons.name.NameConstants.NT_BASE;

public class NodeTypeRegister {

    private static final int COLON = 58;

    private NodeTypeRegister() {    }

    public static void createNodeType(Session session, String nodeType) throws RepositoryException {
        NodeTypeManager nodeTypeManager = session.getWorkspace().getNodeTypeManager();
        if (!nodeTypeManager.hasNodeType(nodeType)) {
            registerNodeType(session, nodeType);
        }
    }

    private static void registerNodeType(Session session, String nodeType) throws RepositoryException {
        NodeTypeManager nodeTypeManager = session.getWorkspace().getNodeTypeManager();
        ValueFactory valueFactory = session.getValueFactory();
        NameFactory nameFactory = NameFactoryImpl.getInstance();

        String uri = getUriOrRegisterNamespace(session, nodeType);
        Name name = nameFactory.create(uri, getLocalName(nodeType));

        Name[] superTypes = new Name[]{NT_BASE};

        QNodeTypeDefinitionImpl ntd = new QNodeTypeDefinitionImpl(name, superTypes, new Name[0], false, false, true, true, (Name)null, new QPropertyDefinition[0], new QNodeDefinition[0]);

        NodeTypeDefinition nodeTypeDefinition = new NodeTypeDefinitionImpl(ntd, (SessionImpl)session, valueFactory);

        nodeTypeManager.registerNodeType(nodeTypeDefinition, false);
    }


    private static String getUriOrRegisterNamespace(Session session, String nodeTypeName) throws RepositoryException {
        String uri = null;

        if (nodeTypeName.indexOf(COLON) > -1) {
            String prefix = nodeTypeName.substring(0, nodeTypeName.indexOf(COLON));
            NamespaceRegistry namespaceRegistry = session.getWorkspace().getNamespaceRegistry();

            try {
                uri = namespaceRegistry.getURI(prefix);
            } catch (RepositoryException var6) {
                uri = String.format("https://www.quack-quack.duck/%s/nt/1.0", prefix);
                namespaceRegistry.registerNamespace(prefix, uri);
            }
        }
        return uri;
    }

    private static String getLocalName(String nodeType) {
        return nodeType.substring(nodeType.indexOf(58) + 1);
    }
}