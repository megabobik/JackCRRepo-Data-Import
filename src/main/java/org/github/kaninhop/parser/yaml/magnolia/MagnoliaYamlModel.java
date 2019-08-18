package org.github.kaninhop.parser.yaml.magnolia;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
class MagnoliaYamlModel {

    @Getter
    private Map<String, Object> yamlMap;
}
