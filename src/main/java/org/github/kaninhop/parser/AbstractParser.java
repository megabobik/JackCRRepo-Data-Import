package org.github.kaninhop.parser;

import org.github.kaninhop.jcr.DataModel;

public abstract class AbstractParser<T> {

    public DataModel getModel() {
        T data  = parseData();
        IConverter<T> converter = getConverter();
        return converter.getDataModel(data);
    }

    protected abstract T parseData();
    protected abstract IConverter getConverter();
}