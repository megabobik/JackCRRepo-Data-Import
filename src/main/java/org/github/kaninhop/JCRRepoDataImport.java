package org.github.kaninhop;

import nl.openweb.jcr.InMemoryJcrRepository;
import org.github.kaninhop.namespace.NodeTypeRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Create Jackrabbit repository with all data, must closed after use
 */
public class JCRRepoDataImport {

    private static Logger logger = LoggerFactory.getLogger(JCRRepoDataImport.class);

    private List<String> JACKRABBIT_REGISTERED_NAMESPACES = asList("jcr","nt", "mix","xml");

    public static final SimpleCredentials ADMIN_CREDENTIALS = new SimpleCredentials("admin", "admin".toCharArray());

    public InMemoryJcrRepository createRepositoryFromModel(DataModel dataModel) throws RepositoryException, IOException, URISyntaxException {
        return processModel(dataModel);
    }

    private InMemoryJcrRepository processModel(DataModel model) throws RepositoryException, IOException, URISyntaxException {
        InMemoryJcrRepository repository = new InMemoryJcrRepository();
        Workspace defaultWorkspace = repository.login(ADMIN_CREDENTIALS).getWorkspace();

        for(DataModel.Workspace modelWorkspace : model.getWorkspaces()){
            String workspaceName = modelWorkspace.getWorkspaceName();
            createWorkspace(defaultWorkspace, workspaceName);
            Session session = repository.login(ADMIN_CREDENTIALS, workspaceName);
            addNodes(session, session.getRootNode(), modelWorkspace.getNodes());
            session.save();
        }
        return repository;
    }

    private void addNodes(Session session, Node node, List<DataModel.Workspace.Node> nodeList) throws RepositoryException {
        if(nodeList == null) {
            return;
        }
        Workspace workspace = session.getWorkspace();

        for(DataModel.Workspace.Node x : nodeList) {
            final String name = x.getName();
            final String value = x.getValue();
            final String type = x.getType();

            if(x.isValueNode()) {
                node.setProperty(name, value);
                continue;
            }

            NodeTypeRegister.createNodeType(session, type);

            addNodes(session, node.addNode(name, type), x.getNodes());
        }
    }

    private void createWorkspace(Workspace workspace, String workspaceName) throws RepositoryException {
        workspace.createWorkspace(workspaceName);
    }
}