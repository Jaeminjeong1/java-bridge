package bridge.view;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String START_OUTPUT = "다리 건너기 게임을 시작합니다.\n";
    private static final String BRIDGE_START = "[";
    private static final String BRIDGE_END = "]";
    private static final String FINAL_RESULT = "최종 게임 결과";
    private static final String SUCCESS_OUTPUT = "게임 성공 여부: ";
    private static final String ATTEMPT_OUTPUT = "총 시도한 횟수: ";
    private static final String SUCCESS = "성공";
    private static final String FAIL = "실패";


    public static void printStart() {
        System.out.println(START_OUTPUT);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static StringBuilder printMap(List<String> resultBridge, List<String> userMovings) {
        StringBuilder totalSb = buildMap(resultBridge, userMovings);
        System.out.println(totalSb + "\n");
        return totalSb;
    }

    private static StringBuilder buildMap(List<String> resultBridge, List<String> userMovings) {
        StringBuilder sbUp = new StringBuilder();
        StringBuilder sbDown = new StringBuilder();

        sbUp.append(BRIDGE_START);
        sbDown.append(BRIDGE_START);
        for (int i = 0; i < resultBridge.size(); i++) {
            String userMoving = userMovings.get(i);
            decideMoving(userMoving, resultBridge, i, sbUp, sbDown);
        }
        return new StringBuilder().append(sbUp).append("\n").append(sbDown);
    }

    private static void decideMoving(String userMoving, List<String> resultBridge, int i, StringBuilder sbUp, StringBuilder sbDown) {
        String roundResult = resultBridge.get(i);
        boolean isLast = isLastRound(resultBridge, i);

        if (userMoving.equals("U")) {
            handleUp(sbUp, sbDown, roundResult, isLast);
            return;
        }

        handleDown(sbUp, sbDown, roundResult, isLast);
    }

    private static void handleUp(StringBuilder sbUp, StringBuilder sbDown, String roundResult, boolean isLast) {
        if (isLast) {
            appendEnd(sbUp, sbDown, roundResult, true);
            return;
        }
        appendMiddle(sbUp, sbDown, roundResult, true);
    }

    private static void handleDown(StringBuilder sbUp, StringBuilder sbDown, String roundResult, boolean isLast) {
        if (isLast) {
            appendEnd(sbUp, sbDown, roundResult, false);
            return;
        }
        appendMiddle(sbUp, sbDown, roundResult, false);
    }

    private static void appendMiddle(StringBuilder sbUp, StringBuilder sbDown, String result, boolean isUp) {
        if (isUp) {
            sbUp.append(" " + result + " |");
            sbDown.append("   |");
            return;
        }
        sbDown.append(" " + result + " |");
        sbUp.append("   |");
    }

    private static void appendEnd(StringBuilder sbUp, StringBuilder sbDown, String result, boolean isUp) {
        if (isUp) {
            sbUp.append(" " + result + " " + BRIDGE_END);
            sbDown.append("   " + BRIDGE_END);
            return;
        }
        sbDown.append(" " + result + " " + BRIDGE_END);
        sbUp.append("   " + BRIDGE_END);
    }

    private static boolean isLastRound(List<String> bridge, int index) {
        return bridge.size() - 1 == index;
    }


    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(List<String> resultBridge, List<String> userMovings, boolean isSuccess, int attempt) {
        System.out.println(FINAL_RESULT);
        System.out.println(buildMap(resultBridge, userMovings));
        System.out.println();
        System.out.println(SUCCESS_OUTPUT + decideSuccess(isSuccess));
        System.out.println(ATTEMPT_OUTPUT + attempt);
    }

    private static String decideSuccess(boolean isSuccess) {
        if (isSuccess) {
            return SUCCESS;
        }
        return FAIL;
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
