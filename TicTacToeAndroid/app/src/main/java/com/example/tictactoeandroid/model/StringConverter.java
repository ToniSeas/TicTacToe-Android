package com.example.tictactoeandroid.model;

public class StringConverter {

    public static String convertToSend(String action, String values) {
        return action+"??"+values;
    }

    public static String getAction(String socketMessage) {
        return socketMessage.split("\\?\\?")[0];
    }

    public static String getValues(String socketMessage) {
        if (socketMessage.split("\\?\\?").length == 2) {
            return socketMessage.split("\\?\\?")[1];
        }
        return "";
    }

}
