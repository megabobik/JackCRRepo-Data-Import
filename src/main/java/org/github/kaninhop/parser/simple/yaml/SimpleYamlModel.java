package org.github.kaninhop.parser.simple.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
class SimpleYamlModel {

    @Getter
    private Map<String, Object> yamlMap;
}