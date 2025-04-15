package org.example.model.annotation;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class Validator {

    public void validate(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(CheckValue.class)) {
                CheckValue annotation = field.getAnnotation(CheckValue.class);
                Object value = field.get(obj);

                // 非必要欄位但為 null
                if (!annotation.require()) {
                    if (value == null) {
                        if ("String".equals(annotation.type())) {
                            field.set(obj, "");
                        }
                        continue;
                    }
                }

                // 必要欄位為 null，拋例外
                if (annotation.require() && value == null) {
                    throw new RuntimeException(obj.getClass() + "-" + field.getName() + ": value is required");
                }

                // 額外驗證格式或型別
                switch (annotation.type()) {
                    case "String":
                        if (value instanceof String str && str.trim().isEmpty()) {
                            throw new RuntimeException(obj.getClass() + "-" + field.getName() + ": string is empty");
                        }
                        break;

                    case "ip":
                        if (!(value instanceof String) || !isValidIp((String) value)) {
                            throw new RuntimeException(obj.getClass() + "-" + field.getName() + ": invalid IP format");
                        }
                        break;

                    case "email":
                        if (!(value instanceof String) || !isValidEmail((String) value)) {
                            throw new RuntimeException(obj.getClass() + "-" + field.getName() + ": invalid Email format");
                        }
                        break;

                }
            }
        }
    }

    private boolean isValidIp(String ip) {
        String regex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        return ip.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        return Pattern.matches(regex, email);
    }

    private boolean isValidEnumValue(Class<? extends Enum<?>> enumClass, Object value) {
        if (!(value instanceof String)) return false;
        String val = (String) value;
        for (Enum<?> constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(val)) return true;
        }
        return false;
    }
}
