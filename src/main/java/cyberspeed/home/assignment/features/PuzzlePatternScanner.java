package cyberspeed.home.assignment.features;

import cyberspeed.home.assignment.features.scanners.CombinationScanner;
import cyberspeed.home.assignment.features.scanners.GameResult;
import cyberspeed.home.assignment.config.Config;
import cyberspeed.home.assignment.config.Symbol;

import java.math.BigDecimal;
import java.util.*;

/**
 * A class responsible for scanning and analyzing puzzle patterns.
 * It analyzes the puzzle pattern to determine the reward based on matching results and applied symbols.
 */
public class PuzzlePatternScanner {
    private final Config config;

    Map<String, List<String>> winningCombinations;
    Map<String, BigDecimal> rewardMultipliers;

    /**
     * Constructs a PuzzlePatternScanner object with the specified configuration.
     * @param config The configuration used for scanning and analyzing puzzle patterns.
     */
    public PuzzlePatternScanner(Config config) {
        this.config = config;
    }

    /**
     * Analyzes the given puzzle pattern and determines the reward based on matching results and applied symbols.
     *
     * @param puzzlePattern The puzzle pattern to analyze.
     * @param bet           The bet amount used for calculating the reward.
     * @return A PuzzlePattern object representing the analyzed puzzle pattern with the determined reward.
     */
    public PuzzlePattern analyze(PuzzlePattern puzzlePattern, BigDecimal bet) {
        List<GameResult> matched = new CombinationScanner(config.getWinCombinations()).match(puzzlePattern.getMatrix());
        BigDecimal reward = BigDecimal.ZERO;

        if (!matched.isEmpty()) {
            filterMultipliers(matched);
            reward = applyBasicSymbols(rewardMultipliers, bet);
            reward = applyBonusSymbol(puzzlePattern.getAppliedBonusSymbol(), reward);
        }

        return new PuzzlePattern(
                puzzlePattern.getMatrix(),
                reward,
                winningCombinations,
                reward.equals(BigDecimal.ZERO) ? null : puzzlePattern.getAppliedBonusSymbol()
        );
    }

    /**
     * Filters the multipliers and determines the winning combinations based on the matching results.
     * @param matched The list of matching results to filter multipliers and determine winning combinations.
     */
    private void filterMultipliers(List<GameResult> matched) {
        winningCombinations = new HashMap<>();
        rewardMultipliers = new HashMap<>();
        Set<String> covered = new HashSet<>();
        for (GameResult gameResult : matched) {
            //only one combination per group per symbol
            if (covered.add(gameResult.getSymbol() + ":" + gameResult.getGroup())) {
                winningCombinations.computeIfAbsent(gameResult.getSymbol(), k -> new ArrayList<>())
                        .add(gameResult.getCombination());

                rewardMultipliers.compute(gameResult.getSymbol(),
                        (k, v) -> v == null ? gameResult.getRewardMultiplier() : v.multiply(gameResult.getRewardMultiplier()));
            }
        }
    }

    /**
     * Applies basic symbols reward based on the reward multipliers and bet amount.
     *
     * @param rewardMultipliers The reward multipliers for each symbol.
     * @param reward            The current reward amount to be updated.
     * @return The updated reward amount after applying basic symbols reward.
     */
    private BigDecimal applyBasicSymbols(Map<String, BigDecimal> rewardMultipliers, BigDecimal reward) {
        BigDecimal rewardMultiplier = rewardMultipliers.entrySet().stream()
                .map(e -> config.getSymbols().get(e.getKey()).getRewardMultiplier().multiply(e.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);  //should never happen

        assert rewardMultiplier.signum() > 0;

        return reward.multiply(rewardMultiplier);
    }

    /**
     * Applies bonus symbol reward based on the applied bonus symbol and current reward amount.
     *
     * @param bonusSymbol The applied bonus symbol, if any.
     * @param reward      The current reward amount to be updated.
     * @return The updated reward amount after applying bonus symbol reward.
     */
    private BigDecimal applyBonusSymbol(String bonusSymbol, BigDecimal reward) {
        if (bonusSymbol != null) {
            Symbol bonus = config.getSymbols().get(bonusSymbol);
            switch (bonus.getImpact()) {
                case MULTIPLY_REWARD:
                    return reward.multiply(bonus.getRewardMultiplier());
                case EXTRA_BONUS:
                    return reward.add(bonus.getExtra());
                case MISS: // do nothing in case MISS
            }
        }
        return reward;
    }

}
