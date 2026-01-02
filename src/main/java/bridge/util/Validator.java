package bridge.util;

import java.util.regex.Pattern;

import static bridge.util.ErrorMessage.*;

public class Validator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
    private static final String UP_INDEX = "U";
    private static final String DOWN_INDEX = "D";
    private static final String RESTART_INDEX = "R";
    private static final String QUIT_INDEX = "Q";

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

    public static void validateMovingInput(String input) {
        if (!input.equals(UP_INDEX) && !input.equals(DOWN_INDEX)) {
            throw new IllegalArgumentException(MOVING_FORMAT_ERROR.getMessage());
        }
    }

    public static void validateIsRestart(String input) {
        if (!input.equals(RESTART_INDEX) && !input.equals(QUIT_INDEX)) {
            throw new IllegalArgumentException(RESTART_FORMAT_ERROR.getMessage());
        }
    }
}

