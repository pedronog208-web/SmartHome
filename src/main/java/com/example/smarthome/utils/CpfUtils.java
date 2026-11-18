package com.example.smarthome.utils; // Ajuste para o seu pacote

public class CpfUtils {

    /**
     * Valida um CPF (com ou sem máscara).
     *
     * @param cpf String contendo o CPF
     * @return true se o CPF for válido, false caso contrário
     */
    public static boolean isValidCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }

        // Remove caracteres não numéricos
        String cleanedCpf = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (cleanedCpf.length() != 11) {
            return false;
        }

        // Rejeita CPFs com todos os dígitos iguais (ex: 000.000.000-00)
        if (cleanedCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Validação do 1º dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cleanedCpf.charAt(i)) * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit > 9) {
                firstDigit = 0;
            }
            if (firstDigit != Character.getNumericValue(cleanedCpf.charAt(9))) {
                return false;
            }

            // Validação do 2º dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cleanedCpf.charAt(i)) * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit > 9) {
                secondDigit = 0;
            }
            if (secondDigit != Character.getNumericValue(cleanedCpf.charAt(10))) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}