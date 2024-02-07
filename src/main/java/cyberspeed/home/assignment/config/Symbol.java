package cyberspeed.home.assignment.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author akash.kumar
 */

public class Symbol {
        @JsonProperty("reward_multiplier")
        private BigDecimal rewardMultiplier;
        private BigDecimal extra;
        private SymbolType type;
        private ImpactType impact;

        public BigDecimal getRewardMultiplier() {
                return rewardMultiplier;
        }

        public void setRewardMultiplier(BigDecimal rewardMultiplier) {
                this.rewardMultiplier = rewardMultiplier;
        }

        public BigDecimal getExtra() {
                return extra;
        }

        public void setExtra(BigDecimal extra) {
                this.extra = extra;
        }

        public SymbolType getType() {
                return type;
        }

        public void setType(SymbolType type) {
                this.type = type;
        }

        public ImpactType getImpact() {
                return impact;
        }

        public void setImpact(ImpactType impact) {
                this.impact = impact;
        }
}

