package bridge.service;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeMaker;

import java.util.List;

public class BridgeService {

    private static final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
    private static final BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

    public List<String> makeBridge(int bridgeSize) {
        return bridgeMaker.makeBridge(bridgeSize);
    }
}
