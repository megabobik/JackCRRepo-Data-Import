package org.github.kaninhop;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataModel {

    @Getter
    @XmlElement(name = "workspace")
    private List<Workspace> workspaces = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Workspace {

        public Workspace(String name) {
            this.workspaceName = name;
        }

        @XmlAttribute(name = "name")
        private String workspaceName;

        @XmlElement(name = "node")
        private List<Node> nodes = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Node {

            public Node(String name, String value, String type) {
                this.name = name;
                this.value = value;
                this.type = type;
            }

            @XmlAttribute(name = "name")
            private String name;

            @XmlAttribute(name = "value")
            private String value;

            @XmlAttribute(name = "type")
            private String type;

            @XmlElement(name = "node")
            private List<Node> nodes = new ArrayList<>();

            public boolean isValueNode() {
                return !Strings.isNullOrEmpty(value);
            }
        }
    }
}