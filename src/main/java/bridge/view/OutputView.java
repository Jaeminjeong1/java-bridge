package bridge.view;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String START_OUTPUT = "다리 건너기 게임을 시작합니다.\n";
    private static final String BRIDGE_START = "[";
    private static final String BRIDGE_END = "]";
    private static final String EMPTY_SLOT = "   ";
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
    public static StringBuilder printMap(List<String> moves, List<String> results) {
        StringBuilder totalSb = buildMap(moves, results);
        System.out.println(totalSb + "\n");
        return totalSb;
    }

    private static StringBuilder buildMap(List<String> moves, List<String> results) {
        List<String> upSlots = new ArrayList<>();
        List<String> downSlots = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            boolean isUp = moves.get(i).equals("U");
            if (isUp) {
                upSlots.add(formatSlot(results.get(i)));
                downSlots.add(EMPTY_SLOT);
                continue;
            }
            downSlots.add(formatSlot(results.get(i)));
            upSlots.add(EMPTY_SLOT);
        }
        String up = BRIDGE_START + String.join("|", upSlots) + BRIDGE_END;
        String down = BRIDGE_START + String.join("|", downSlots) + BRIDGE_END;
        return new StringBuilder().append(up).append("\n").append(down);
    }

    private static String formatSlot(String result) {
        return " " + result + " ";
    }


    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(List<String> moves, List<String> results, boolean isSuccess, int attempt) {
        System.out.println(FINAL_RESULT);
        System.out.println(buildMap(moves, results));
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
