package org.github.kaninhop.parser.magnolia.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
class MagnoliaYamlModel {

    @Getter
    private Map<String, Object> yamlMap;
}
