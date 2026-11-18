package com.example.smarthome.utils;

public class OnlyNumbers {
    /**
     * Remove tudo o que não for número de um texto,
     * mantendo apenas os dígitos de 0 a 9.
     *
     * @param text Texto original (ex: "123.456.789-00")
     * @return Texto apenas com os números (ex: "12345678900")
     */
    public static String apply(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        // O comando que limpa e deixa só os números
        return text.replaceAll("\\D", "");
    }
}
