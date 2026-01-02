package bridge.util;

public enum ErrorMessage {

    INPUT_ERROR("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    TYPE_ERROR("타입이 불일치 합니다. 다시 입력해 주세요."),
    NUMBER_FORMAT_ERROR("숫자형식 에러"),
    BRIDGE_LENGTH_ERROR("다리 길이는 3부터 20 사이의 숫자여야 합니다.");

    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
