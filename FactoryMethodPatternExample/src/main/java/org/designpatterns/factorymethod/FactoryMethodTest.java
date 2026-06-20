package org.designpatterns.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {

        System.out.println("--- Factory Method Pattern Test ---\n");

        // An array of factories, one per document type.
        // Client code treats them all identically as DocumentFactory.
        DocumentFactory[] factories = {
                new WordDocumentFactory(),
                new PdfDocumentFactory(),
                new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            // The factory method hides which concrete class gets created.
            Document document = factory.createDocument();

            document.open();
            document.save();
            System.out.println("Created document type: " + document.getType());
            System.out.println();
        }

        System.out.println("--- Demonstrating the template method openNewDocument() ---\n");

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document word = wordFactory.openNewDocument(); // calls createDocument() internally, then opens it
        System.out.println("Returned document type: " + word.getType());
    }
}
