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

    public static boolean updateIfStringNotNull(Consumer<String> setter, String value) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
            return true;
        }
        return false;
    }

    public static boolean updateIfStringNotNullNotEmptyAndNotEquals(Consumer<String> setter, String newValue, String oldValue) {
        if (newValue != null && !newValue.isEmpty() && !newValue.equals(oldValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }

}
