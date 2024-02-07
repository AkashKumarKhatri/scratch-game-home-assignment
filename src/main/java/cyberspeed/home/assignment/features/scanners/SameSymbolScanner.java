package cyberspeed.home.assignment.features.scanners;

import cyberspeed.home.assignment.config.DesignMatch;
import cyberspeed.home.assignment.config.WinDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author akash.kumar
 */
public class SameSymbolScanner extends CombinationScanner {

    private final Map<Integer, PatternProfile> combinations = new HashMap<>();
    private final Map<String, Integer> symbols = new HashMap<>();

    public SameSymbolScanner(Map<String, WinDesign> winCombinations) {
        winCombinations.entrySet().stream()
                .filter(e -> e.getValue().getWhen() == DesignMatch.SAME_SYMBOLS)
                .forEach(e -> combinations.put(e.getValue().getCount(), new PatternProfile(e.getKey(), e.getValue())));
    }

    public List<GameResult> match(String[][] puzzle) {
        // This code snippet counts the occurrences of each unique symbol in
        // the two-dimensional puzzle array and stores the counts in a Map called symbols.
        for (String[] row : puzzle) {
            for (String symbol : row) {
                symbols.compute(symbol, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        List<GameResult> result = new ArrayList<>();
        for (Map.Entry<String, Integer> e : symbols.entrySet()) {
            PatternProfile descriptor = combinations.get(e.getValue());
            if (descriptor != null) {
                GameResult gameResult = new GameResult(
                        e.getKey(),
                        descriptor.getName(),
                        descriptor.getWinCombination().getGroup(),
                        descriptor.getWinCombination().getRewardMultiplier()
                );
                result.add(gameResult);
            }
        }
        return result;
    }
}
