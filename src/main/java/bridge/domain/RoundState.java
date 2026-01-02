package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class RoundState {
    private final List<String> moves = new ArrayList<>();
    private final List<String> results = new ArrayList<>();
    private boolean success;

    public void addRound(String move, String result) {
        moves.add(move);
        results.add(result);
    }

    public void markSuccess() {
        success = true;
    }

    public List<String> getMoves() {
        return List.copyOf(moves);
    }

    public List<String> getResults() {
        return List.copyOf(results);
    }

    public boolean isSuccess() {
        return success;
    }
}
