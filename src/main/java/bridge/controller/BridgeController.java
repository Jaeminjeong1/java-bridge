package bridge.controller;

import bridge.domain.RoundState;
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
        OutputView.printStart();
        List<String> bridge = inputBridgeSize();
        playUntilEnd(bridge);
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

    private void playUntilEnd(List<String> bridge) {
        int attempt = 1;
        while (true) {
            RoundState state = playOnce(bridge);
            OutputView.printResult(state.getMoves(), state.getResults(), state.isSuccess(), attempt);
            if (state.isSuccess()) {
                return;
            }
            if (isRetry()) {
                attempt++;
                continue;
            }
            return;
        }
    }

    private RoundState playOnce(List<String> bridge) {
        RoundState state = new RoundState();
        for (String answer : bridge) {
            if (isFailRound(answer, state)) {
                return state;
            }
        }
        state.markSuccess();
        return state;
    }

    private boolean isFailRound(String answer, RoundState state) {
        String userMoving = inputMoving();
        String result = bridgeService.isMatchAnswer(userMoving, answer); // O or X
        state.addRound(userMoving, result);
        OutputView.printMap(state.getMoves(), state.getResults());
        return result.equals("X");
    }

    private String inputMoving() {
        while (true) {
            try {
                return InputView.readMoving();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private boolean isRetry() {
        while (true) {
            try {
                return bridgeService.isRetry(InputView.readGameCommand());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

}
