package org.github.kaninhop.namespace;

public class NamespaceFactory {

    public static Namespace createNamespace(String type) {
        Namespace namespace = new Namespace(type);

        if(type.contains(":")) {
            String trimmedType = type.substring(0, type.indexOf(":"));
            namespace = new Namespace(trimmedType);
        }

        return namespace;
    }
}
