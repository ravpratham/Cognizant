package org.designpatterns.factorymethod;

public class WordDocument implements Document{
    public WordDocument() {
        System.out.println("WordDocument: instance created.");
    }

    @Override
    public void open() {
        System.out.println("Opening a Word document (.docx)...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document as .docx");
    }

    @Override
    public String getType() {
        return "Word Document";
    }
}
