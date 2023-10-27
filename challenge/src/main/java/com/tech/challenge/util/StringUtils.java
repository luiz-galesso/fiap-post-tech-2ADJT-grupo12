package com.tech.challenge.util;

import jakarta.validation.constraints.NotNull;

public class StringUtils {
    public static String removeMascara(String mascarado) {
        if (mascarado.isEmpty()) {
            return mascarado; //Fazer o campo nao permitir nulo
        }

        return mascarado.replace(".", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "");
    }
}
