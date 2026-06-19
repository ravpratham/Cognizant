package org.designpatterns.singletonpattern;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        if (logger1 == logger2) {
            System.out.println("Pass: logger1 and logger 2 are same instances");
        } else {
            System.out.println("Fail: logger1 and logger 2 are different instances");
        }
        System.out.println("logger1 memory reference: " + logger1.hashCode());
        System.out.println("logger2 memory reference: " + logger2.hashCode());
    }
}
