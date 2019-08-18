package org.github.kaninhop.parser.xml.magnolia;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
class MagnoliaXmlModel {

    public MagnoliaXmlModel() {

    }

    MagnoliaXmlModel(String name, List<MagnoliaXmlModel> nodes, List<MagnoliaXmlProperty> properties) {
        this.name = name;
        this.nodes.clear();
        this.nodes.addAll(nodes);
        this.properties.clear();
        this.properties.addAll(properties);
    }

    @Getter
    @XmlAttribute(name = "name")
    private String name;

    @Getter
    @XmlElement(name = "node")
    private List<MagnoliaXmlModel> nodes = new ArrayList<>();

    @Getter
    @XmlElement(name = "property")
    private List<MagnoliaXmlProperty> properties = new ArrayList<>();



    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MagnoliaXmlProperty {

        @Getter
        @XmlAttribute(name = "name")
        private String name;

        @Getter
        @XmlAttribute(name = "type")
        private String type;

        @Getter
        @XmlElement(name = "value")
        private String value;

        public boolean isNodeProperty() {
            return name.equals("jcr:primaryType") && type.equals("Name");
        }
    }
}