package cyberspeed.home.assignment.features.scanners;

import cyberspeed.home.assignment.config.WinDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author akash.kumar
 */
public class CombinationScanner {

    private final Map<String, WinDesign> winCombinations;

    protected CombinationScanner() {
        winCombinations = Map.of();
    }

    public CombinationScanner(Map<String, WinDesign> winCombinations) {
        this.winCombinations = winCombinations;
    }

    public List<GameResult> match(String[][] puzzle) {
        List<GameResult> result = new ArrayList<>();

        result.addAll(new SameSymbolScanner(winCombinations).match(puzzle));
        result.addAll(new LinearScanner(winCombinations).match(puzzle));

        return result;
    }

    protected static class PatternProfile {
        private String name;
        private WinDesign winDesign;

        public PatternProfile(String name, WinDesign winDesign) {
            this.name = name;
            this.winDesign = winDesign;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public WinDesign getWinCombination() {
            return winDesign;
        }

        public void setWinCombination(WinDesign winDesign) {
            this.winDesign = winDesign;
        }
    }
}
