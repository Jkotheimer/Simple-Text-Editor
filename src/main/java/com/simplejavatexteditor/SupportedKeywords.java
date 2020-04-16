package com.simplejavatexteditor;

import java.util.ArrayList;

/**
 * <h1>A class to store the programming language keywords and
 * provide access to them.</h1>
 *
 * <p>Makes multiple language support possible and makes adding new language
 * support convenient. To add more keywords, add a string array and getters
 * to this class. Then, update the switch statement in UI.java.</p>
 */
public class SupportedKeywords {

    //Array holding Strings of supported languages
    private String[] supportedLanguages = {".cpp",".java"};

    //Array holding Strings of supported keywords for java.
    private String[] java = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "System", "out", "print()", "println()",
            "new", "null", "package", "private", "protected", "public", "interface",
            "long", "native", "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while", "String"};

    //Array holding Strings of supported keywords for C++.
    private String[] cpp = { "auto", "const", "double", "float", "int", "short",
                "struct", "unsigned", "break", "continue", "else", "for", "long", "signed",
                "switch", "void", "case", "default", "enum", "goto", "register", "sizeof",
                "typedef", "volatile", "char", "do", "extern", "if", "return", "static",
                "union", "while", "asm", "dynamic_cast", "namespace", "reinterpret_cast", "try",
                "bool", "explicit", "new", "static_cast", "typeid", "catch", "false", "operator",
                "template", "typename", "class", "friend", "private", "this", "using", "const_cast",
                "inline", "public", "throw", "virtual", "delete", "mutable", "protected", "true", "wchar_t" };

    //Getter method that returns private instance variable
    //supportedLanguages.
    public String[] getSupportedLanguages() {
        return supportedLanguages;
    }

    //More private instance variables
    private String[] brackets = { "{", "(" };
    private String[] bCompletions = { "}", ")" };
    
    //Getter method that returns private instance variable
    //java.
    public String[] getJavaKeywords() {
        return java;
    }

    //Getter method that returns private instance variable
    //cpp.
    public String[] getCppKeywords() {
        return cpp;
    }

    //Getter method that allows for back bracket and back parentheses 
    //completion by adding it to an ArrayList.
    public ArrayList<String> getbracketCompletions() {
        ArrayList<String> al = new ArrayList<>();
        for(String completion : bCompletions) {
            al.add(completion);
        }
        return al;
    }

    //Getter method that allows for bracket and parentheses completion by
    //adding it to an ArrayList.
    public ArrayList<String> getbrackets() {
        ArrayList<String> al = new ArrayList<>();
        for(String completion : brackets) {
            al.add(completion);
        }
        return al;
    }

    //Setter method that allows you to pass in what keywords you want. 
    public ArrayList<String> setKeywords(String[] arr) {
        ArrayList<String> al = new ArrayList<>();
        for(String words : arr) {
            al.add(words);
        }
        return al;
    }

}
