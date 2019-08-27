package org.github.kaninhop.parser.magnolia.xml;

import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.IConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MagnoliaXmlConverter implements IConverter<MagnoliaXmlModel> {

    private final String ROOT_TYPE = "rep:root";

    private String workspaceName;

    public MagnoliaXmlConverter(String workspaceName){
        this.workspaceName = workspaceName;
    }

    @Override
    public DataModel getDataModel(MagnoliaXmlModel model) {
        DataModel dataModel = new DataModel();
        DataModel.Workspace workspace = new DataModel.Workspace(workspaceName);

        convertNode(workspace, model);
        dataModel.getWorkspaces().add(workspace);

        return  dataModel;
    }

    private void convertNode(DataModel.Workspace workspace, MagnoliaXmlModel xmlModel) {
        Optional<MagnoliaXmlModel.MagnoliaXmlProperty> property = xmlModel.getProperties().stream().filter(x -> ROOT_TYPE.equals(x.getValue())).findFirst();
        List<MagnoliaXmlModel> modelList = new ArrayList<>();
        modelList.add(xmlModel);
        if(property.isPresent()){
            modelList.clear();
            modelList.addAll(xmlModel.getNodes());
        }

        modelList.forEach(nodeB -> {
            MagnoliaXmlModel magModel = new MagnoliaXmlModel(nodeB.getName(), nodeB.getNodes(), nodeB.getProperties());
            DataModel.Workspace.Node newNode = addData(magModel);
            workspace.getNodes().add(newNode);
        });
    }

    private DataModel.Workspace.Node addData(MagnoliaXmlModel magNode) {
        Optional<MagnoliaXmlModel.MagnoliaXmlProperty> property = magNode.getProperties().stream().filter(MagnoliaXmlModel.MagnoliaXmlProperty::isNodeProperty).findFirst();

        if (!property.isPresent()){
            throw new IllegalArgumentException("Can't find property for node");
        }

        MagnoliaXmlModel.MagnoliaXmlProperty property1 = property.get();

        DataModel.Workspace.Node node = new DataModel.Workspace.Node(magNode.getName(), null, property1.getValue());

        magNode.getProperties().forEach(p -> {
            if(p.isNodeProperty()) {
                return;
            }
            DataModel.Workspace.Node propNode = new DataModel.Workspace.Node(p.getName(), p.getValue(), p.getType());
            node.getNodes().add(propNode);
        });

        magNode.getNodes().forEach(x -> {
            DataModel.Workspace.Node subNode= addData(x);
            node.getNodes().add(subNode);
        });

        return node;
    }
}