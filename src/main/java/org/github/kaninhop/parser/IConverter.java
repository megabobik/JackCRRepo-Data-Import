package org.github.kaninhop.parser;

import org.github.kaninhop.jcr.DataModel;

public interface IConverter<T> {

    DataModel getDataModel(T model);
}