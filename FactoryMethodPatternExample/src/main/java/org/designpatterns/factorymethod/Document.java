package org.designpatterns.factorymethod;

public interface Document {
    void open();
    void save();

    String getType();
}
