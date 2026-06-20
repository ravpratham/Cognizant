package org.designpatterns.factorymethod;

public class PdfDocument implements Document{

    public PdfDocument(){
        System.out.println("PdfDocument: instance created.");
    }

    @Override
    public void open() {
        System.out.println("Opening a PDF document (.pdf)...");
    }

    @Override
    public void save() {
        System.out.println("document is saved as .pdf");
    }

    @Override
    public String getType() {
        return "PDF document";
    }
}
