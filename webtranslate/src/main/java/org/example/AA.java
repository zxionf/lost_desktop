package org.example;

import java.util.Locale;
import java.awt.im.InputContext;
public class AA {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        InputContext context = InputContext.getInstance();
        String locale = context.getLocale().toString();
//        if(locale.startsWith("zh")){
            context.selectInputMethod(Locale.ENGLISH);
            System.out.println("Locale: " + locale);
//        }

    }
}