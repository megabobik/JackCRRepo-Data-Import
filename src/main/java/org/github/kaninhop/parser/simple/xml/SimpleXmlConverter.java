package org.github.kaninhop.parser.simple.xml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.IConverter;

import java.util.List;

class SimpleXmlConverter implements IConverter<SimpleXmlModel> {

    @Override
    public DataModel getDataModel(SimpleXmlModel model) {
        DataModel dataModel = new DataModel();

        model.getWorkspaces().forEach(x -> {
            DataModel.Workspace workspace = new DataModel.Workspace(x.getWorkspaceName());
            addData(workspace, x.getNodes());
            dataModel.getWorkspaces().add(workspace);
        });
        return dataModel;
    }

    private void addData(DataModel.Workspace workspace, List<SimpleXmlModel.SimpleXmlWorkspaceModel.SimpleXmlNodeModel> nodeList) {
        nodeList.forEach(x -> {
            DataModel.Workspace.Node node = new DataModel.Workspace.Node(x.getName(), x.getValue(), x.getType());
            addNodes(node, x.getNodes());
            workspace.getNodes().add(node);
        });
    }

    private void addNodes(DataModel.Workspace.Node node, List<SimpleXmlModel.SimpleXmlWorkspaceModel.SimpleXmlNodeModel> nodeList) {
        if(nodeList == null) {
            return;
        }

        nodeList.forEach(x -> {
            final String name = x.getName();
            final String value = x.getValue();
            final String type = x.getType();

            DataModel.Workspace.Node node1  = new DataModel.Workspace.Node(name, value, type);
            node.getNodes().add(node1);

            addNodes(node1, x.getNodes());
        });
    }
}