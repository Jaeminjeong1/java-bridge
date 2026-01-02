package bridge.util;

import java.util.regex.Pattern;

import static bridge.util.ErrorMessage.INPUT_ERROR;
import static bridge.util.ErrorMessage.NUMBER_FORMAT_ERROR;

public class Validator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    private Validator() {
    }

    // 빈값 검증
    public static void validateEmptyInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_ERROR.getMessage());
        }
    }

    // 숫자 형식 검증
    public static void validateNumberFormat(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    // 양수인지 검증
    public static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(INPUT_ERROR.getMessage());
        }
    }

    // 값 범위 검증
    public static void validateRange(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(INPUT_ERROR.getMessage());
        }
    }
}

