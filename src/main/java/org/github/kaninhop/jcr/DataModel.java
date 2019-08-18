package org.github.kaninhop.jcr;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Data model that every parser parse to, and used as data holder for importing to Jackrabbit repository
 */
public class DataModel {

    @Getter
    private List<Workspace> workspaces = new ArrayList<>();

    /**
     * Holder for workspace
     */
    @Getter
    @NoArgsConstructor
    public static class Workspace {

        public Workspace(String name) {
            this.workspaceName = name;
        }

        private String workspaceName;

        private List<Node> nodes = new ArrayList<>();

        /**
         * Holder for node, if value not null and not empty, then it is considered that node is property
         */
        @Getter
        @NoArgsConstructor
        public static class Node {

            public Node(String name, String value, String type) {
                this.name = name;
                this.value = value;
                this.type = type;
            }

            private String name;

            private String value;

            private String type;

            private List<Node> nodes = new ArrayList<>();

            /**
             * Check if this node can be considered as property
             * @return true if this is property, false otherwise
             */
            public boolean isProperty() {
                return !Strings.isNullOrEmpty(value);
            }
        }
    }
}