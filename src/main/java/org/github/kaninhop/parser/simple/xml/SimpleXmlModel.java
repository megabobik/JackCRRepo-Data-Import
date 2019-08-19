package org.github.kaninhop.parser.simple.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
class SimpleXmlModel {

    @Getter
    @XmlElement(name = "workspace")
    private List<SimpleXmlWorkspaceModel> workspaces = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SimpleXmlWorkspaceModel {

        public SimpleXmlWorkspaceModel(String name) {
            this.workspaceName = name;
        }

        @XmlAttribute(name = "name")
        private String workspaceName;

        @XmlElement(name = "node")
        private List<SimpleXmlNodeModel> nodes = new ArrayList<>();

        @Getter
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class SimpleXmlNodeModel {

            @XmlAttribute(name = "name")
            private String name;

            @XmlAttribute(name = "value")
            private String value;

            @XmlAttribute(name = "type")
            private String type;

            @XmlElement(name = "node")
            private List<SimpleXmlNodeModel> nodes = new ArrayList<>();
        }
    }
}