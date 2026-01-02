package bridge.controller;

import bridge.service.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;

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
        // 게임 시작
            // 이동할 칸 입력 받기
            // 매 라운드 결과 출력
            // 실패시 재시도 여부 묻기
        // 최종 게임 결과 출력
        // 게임 성공 여부 출력
        // 총 시도 횟수 출력
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

    private void startGame(List<String> bridge) {

        for (int i = 0; i < bridge.size(); i++) {
            String userMoving = InputView.readMoving();
            String answer = bridge.get(i);
            String result = bridgeService.isMatchAnswer(userMoving, answer);
        }

    }

}
