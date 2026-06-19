package org.designpatterns.factorymethod;

public interface Document {
    void open();
    void close();
    void save();

    String getType();
}
