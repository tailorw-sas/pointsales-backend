package com.kynsof.share.utils;

import java.util.function.Consumer;

public class UpdateIfNotNull {

    /**
     * Valida si T es diferente de null para actualizar el valor de la variable.
     * @param <T>
     * @param setter
     * @param value 
     */
    public static <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public static void updateIfStringNotNull(Consumer<String> setter, String value) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

}
