package bridge.controller;

import bridge.service.BridgeService;

public class BridgeController {

    private final BridgeService bridgeService;

    public BridgeController(BridgeService bridgeService) {
        this.bridgeService = bridgeService;
    }

    public void start() {

    }

}
