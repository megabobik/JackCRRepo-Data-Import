package org.github.kaninhop.parser.xml.magnolia;

import org.github.kaninhop.Constants;
import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.IConverter;

import java.util.Optional;

class MagnoliaXmlConverter implements IConverter<MagnoliaXmlModel> {

    private final String ROOT_TYPE = "rep:root";

    @Override
    public DataModel getDataModel(MagnoliaXmlModel model) {
        DataModel dataModel = new DataModel();
        DataModel.Workspace workspace = new DataModel.Workspace(Constants.DEFAULT_WORKSPACE);
        DataModel.Workspace.Node node =  convertNode(model);

        workspace.getNodes().add(node);
        dataModel.getWorkspaces().add(workspace);

        return  dataModel;
    }

    private DataModel.Workspace.Node convertNode(MagnoliaXmlModel xmlModel) {
        Optional<MagnoliaXmlModel.MagnoliaXmlProperty> property = xmlModel.getProperties().stream().filter(x -> ROOT_TYPE.equals(x.getValue())).findFirst();
        if(property.isPresent()){
            MagnoliaXmlModel nodeB = xmlModel.getNodes().get(0);
            xmlModel = new MagnoliaXmlModel(nodeB.getName(), nodeB.getNodes(), nodeB.getProperties());
        }
        return addData(xmlModel);
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