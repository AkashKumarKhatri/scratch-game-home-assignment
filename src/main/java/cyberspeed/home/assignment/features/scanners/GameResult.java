package cyberspeed.home.assignment.features.scanners;

import cyberspeed.home.assignment.config.DesignSet;

import java.math.BigDecimal;

/**
 * @author akash.kumar
 */
public class GameResult {
    private String symbol;
    private String combination;
    private DesignSet group;
    private BigDecimal rewardMultiplier;

    public GameResult() {
    }

    public GameResult(String symbol, String combination, DesignSet group, BigDecimal rewardMultiplier) {
        this.symbol = symbol;
        this.combination = combination;
        this.group = group;
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public DesignSet getGroup() {
        return group;
    }

    public void setGroup(DesignSet group) {
        this.group = group;
    }

    public BigDecimal getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(BigDecimal rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }
}
