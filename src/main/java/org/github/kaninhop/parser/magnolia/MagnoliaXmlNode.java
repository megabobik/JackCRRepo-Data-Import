package org.github.kaninhop.parser.magnolia;

import com.google.common.base.Strings;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class MagnoliaXmlNode {

    @Getter
    @XmlAttribute(name = "name")
    private String name;

    @Getter
    @XmlElement(name = "node")
    private List<MagnoliaXmlNode> nodes = new ArrayList<>();

    @Getter
    @XmlElement(name = "property")
    private List<Property> properties = new ArrayList<>();

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Property {

        @Getter
        @XmlAttribute(name = "name")
        private String name;

        @Getter
        @XmlAttribute(name = "type")
        private String type;

        @Getter
        @XmlElement(name = "value")
        private String value;

        public boolean isValueNode() {
            return !Strings.isNullOrEmpty(value);
        }

        public boolean isNodeProperty() {
            return name.equals("jcr:primaryType") && type.equals("Name");
        }
    }
}