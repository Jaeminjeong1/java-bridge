package bridge.service;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;

import java.util.List;

public class BridgeService {

    public List<String> makeBridge(int bridgeSize) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

        return bridgeMaker.makeBridge(bridgeSize);
    }

    public String isMatchAnswer(String userMoving, String answer) {
        BridgeGame bridgeGame = new BridgeGame();

        return bridgeGame.move(userMoving, answer);
    }
}
