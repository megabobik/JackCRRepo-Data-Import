package org.github.kaninhop.parser.yaml.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
class SimpleYamlModel {

    @Getter
    private Map<String, Object> yamlMap;
}