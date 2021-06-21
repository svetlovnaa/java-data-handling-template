package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {

        final String regex = "(?<=(?:[^\\d]|^))(\\d{4}[ \\t])(?:\\d{4}[ \\t]){2}(\\d{4})(?=(?:[^\\d]|$))";
        final String subst = "$1**** **** $2";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        StringBuilder stringBuilder = new StringBuilder();
        String result = new String();

        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")){

            int c;
            while ((c = reader.read())!= -1 ) {
                if (c != '\n') {
                    stringBuilder.append((char) c);
                }
            }
            Matcher matcher = pattern.matcher(stringBuilder);
            while (matcher.find()) {
                char[] strCharMass = stringBuilder.substring(matcher.start(), matcher.end()).toCharArray();
                char[] strEncrypted = new char[strCharMass.length];
                for (int i = 0; i < strCharMass.length; i++) {
                    if (i > 4 && i < 14 && strCharMass[i] != ' ') {
                        strEncrypted[i] = (char) 42;
                    } else {
                        strEncrypted[i] = strCharMass[i];
                    }
                }
            }
           result = matcher.replaceAll(subst);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        try (FileReader reader = new FileReader("src/main/resources/sensitive_data.txt")) {

            String regexPayment = "[$]{1}[{]{1}[a-z]{7}[_]{1}[a-z]{6}[}]{1}";
            String regexBalance = "[$]{1}[{]{1}[a-z]{7}[}]{1}";

            StringBuilder stringBuilder = new StringBuilder();
            Pattern pattern = Pattern.compile(regexPayment);
            Pattern pattern1 = Pattern.compile(regexBalance);

            int c;
            while((c = reader.read()) != -1) {
                if (c != '\n') {
                    stringBuilder.append((char) c);
                }
            }
            Matcher matcher = pattern.matcher(stringBuilder);
            String result = matcher.replaceAll(Integer.toString((int) paymentAmount));
            Matcher matcher1 = pattern1.matcher(result);
            String result1 = matcher1.replaceAll(Integer.toString((int) balance));
            return result1;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
