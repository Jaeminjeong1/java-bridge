package bridge.controller;

import bridge.service.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.domain.RoundResult;

import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    private final BridgeService bridgeService;

    public BridgeController(BridgeService bridgeService) {
        this.bridgeService = bridgeService;
    }

    public void start() {
        // 프로그램 시작 멘트
        OutputView.printStart();
        // 다리 길이 입력 받기
        List<String> bridge = inputBridgeSize();
        List<String> resultBridge = new ArrayList<>();
        List<String> userMovings = new ArrayList<>();
        int attempt = 1;
        RoundResult finalResult = RoundResult.FAIL;

        while (true) {
            RoundResult result = startGame(bridge, resultBridge, userMovings);
            finalResult = result;
            if (result == RoundResult.CLEAR) {
                break;
            }

            String input = InputView.readGameCommand();
            if (bridgeService.isRetry(input)) {
                attempt++;
                resultBridge.clear();
                userMovings.clear();
                continue;
            }
            break;
        }

        // 최종 게임 결과 출력
        OutputView.printResult(resultBridge, userMovings, finalResult == RoundResult.CLEAR, attempt);
    }

    private List<String> inputBridgeSize() {
        while (true) {
            try {
                int bridgeSize = InputView.readBridgeSize();

                return bridgeService.makeBridge(bridgeSize);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private RoundResult startGame(List<String> bridge, List<String> resultBridge, List<String> userMovings) {
        while (true) {
            try {
                for (int i = 0; i < bridge.size(); i++) {
                    String userMoving = InputView.readMoving();
                    String answer = bridge.get(i);
                    String result = bridgeService.isMatchAnswer(userMoving, answer);
                    resultBridge.add(result);
                    userMovings.add(userMoving);
                    OutputView.printMap(resultBridge, userMovings);
                    if (result.equals("X")) {
                        return RoundResult.FAIL;
                    }
                    if (i == bridge.size() - 1) {
                        return RoundResult.CLEAR;
                    }
                }
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }


    }

}
