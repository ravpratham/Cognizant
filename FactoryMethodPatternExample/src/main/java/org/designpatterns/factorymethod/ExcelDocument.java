package org.designpatterns.factorymethod;

public class ExcelDocument implements Document{
    public ExcelDocument() {
        System.out.println("ExcelDocument: instance created.");
    }

    @Override
    public void open() {
        System.out.println("Opening an Excel document (.xlsx)...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document as .xlsx");
    }

    @Override
    public String getType() {
        return "Excel Document";
    }
}
