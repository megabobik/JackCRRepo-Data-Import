package org.github.kaninhop.parser.magnolia;

import org.github.kaninhop.Constants;
import org.github.kaninhop.DataModel;

import java.util.Optional;

public class MagnoliaXmlConverter {

    public DataModel getDataModel(MagnoliaXmlNode rootNode) {
        DataModel dataModel = new DataModel();
        DataModel.Workspace workspace = new DataModel.Workspace(Constants.DEFAULT_WORKSPACE);
        DataModel.Workspace.Node node =  convertNode(rootNode);

        workspace.getNodes().add(node);
        dataModel.getWorkspaces().add(workspace);

        return  dataModel;
    }

    private DataModel.Workspace.Node convertNode( MagnoliaXmlNode magNode) {
        return addData(magNode);
    }

    private DataModel.Workspace.Node addData(MagnoliaXmlNode magNode) {
        Optional<MagnoliaXmlNode.Property> property = magNode.getProperties().stream().filter(MagnoliaXmlNode.Property::isNodeProperty).findFirst();

        if (!property.isPresent()){
            throw new IllegalArgumentException("Can't find property for node");
        }

        MagnoliaXmlNode.Property property1 = property.get();

        DataModel.Workspace.Node node = new DataModel.Workspace.Node(magNode.getName(), null, property1.getValue());

        for(MagnoliaXmlNode.Property p : magNode.getProperties()){
            if(p.isNodeProperty()) {
                continue;
            }
            DataModel.Workspace.Node propNode = new DataModel.Workspace.Node(p.getName(), p.getValue(), p.getType());
            node.getNodes().add(propNode);
        }

        for(MagnoliaXmlNode mgnlNode : magNode.getNodes()) {
            DataModel.Workspace.Node subNode= addData(mgnlNode);
            node.getNodes().add(subNode);
        }

        return node;
    }
}