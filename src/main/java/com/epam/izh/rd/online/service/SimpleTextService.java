package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    @Override
    public String removeString(String base, String remove) {

        return base.replace(remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {

        return text.endsWith("?");
    }

    @Override
    public String concatenate(String... elements) {

       StringBuilder stringBuilder = new StringBuilder();
        for (int i  = 0; i < elements.length; i++) {

            stringBuilder.append(elements[i]);
        }

        return stringBuilder.toString();
    }

    @Override
    public String toJumpCase(String text) {

        char[] textToChar = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textToChar.length; i++) {
            if (i % 2 == 0) {
                textToChar[i] = Character.toLowerCase(textToChar[i]);
            } else {
                textToChar[i] = Character.toUpperCase(textToChar[i]);
            }
        }
        return stringBuilder.append(textToChar).toString();
    }

    @Override
    public boolean isPalindrome(String string) {

        if (string == null) {
            return false;
        }
        int i = 0;
        int j = string.length() - 1;
        while (i < j) {
            if (string.charAt(i++) != string.charAt(j--)) {
                return false;
            } else {
                return true;
            }
        }
       return false;
    }
}
