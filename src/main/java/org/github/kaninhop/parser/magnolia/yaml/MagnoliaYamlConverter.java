package org.github.kaninhop.parser.magnolia.yaml;

import org.github.kaninhop.Constants;
import org.github.kaninhop.jcr.DataModel;
import org.github.kaninhop.parser.IConverter;

import java.util.Date;
import java.util.Map;

class MagnoliaYamlConverter implements IConverter<MagnoliaYamlModel> {

    private final String TYPE = "jcr:primaryType";
    private final String CONTENT_NODE = "mgnl:contentNode";

    private final String STRING_TYPE = "String";

    private String workspaceName = Constants.DEFAULT_WORKSPACE;

    public MagnoliaYamlConverter(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    @Override
    public DataModel getDataModel(MagnoliaYamlModel model) {
        DataModel dataModel = new DataModel();

        Map<String, Object> map = model.getYamlMap();

        DataModel.Workspace workspace = new DataModel.Workspace(workspaceName);
        addData(workspace, map);
        dataModel.getWorkspaces().add(workspace);

        return dataModel;
    }

    private void addData(DataModel.Workspace workspace, Map<String, Object> map) {
        map.forEach((key, value) -> {
            DataModel.Workspace.Node node = new DataModel.Workspace.Node(key, getValue(value), getType(value));

            addNodes(node, getMap(value));
            workspace.getNodes().add(node);
        });
    }

    private void addNodes(DataModel.Workspace.Node node, Map<String, Object> map) {
        if(map == null) {
            return;
        }
        map.forEach((key, value) -> {
            DataModel.Workspace.Node nod = new DataModel.Workspace.Node(key, getValue(value), getType(value));
            node.getNodes().add(nod);
            addNodes(nod, getMap(value));
        });
    }

    private String getType(Object object) {
        Map<String, Object> map1 = getMap(object);
        if(map1 == null) {
            return STRING_TYPE;
        } else {
            return getType(map1);
        }
    }

    private String getValue(Object object) {
        Map<String, Object> map1 = getMap(object);
        if(map1 == null) {
            if(object instanceof Boolean) {
                return ((Boolean) object).toString();
            }
            if(object instanceof Date) {
                return ((Date) object).toString();
            }
            return (String) object;
        } else {
            return null;
        }
    }

    private String getType(Map<String, Object> map) {
        if(map != null && map.containsKey(TYPE)) {
            String type = (String) map.get(TYPE);
            map.remove(TYPE);
            return type;
        }
        return CONTENT_NODE;
    }

    private Map<String, Object> getMap(Object value){
        if(value instanceof Map){
            return ((Map<String, Object>) value);
        }
        return null;
    }
}
