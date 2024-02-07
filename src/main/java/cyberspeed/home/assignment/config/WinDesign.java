package cyberspeed.home.assignment.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author akash.kumar
 */
public class WinDesign {

    @JsonProperty("reward_multiplier")
    private BigDecimal rewardMultiplier;
    private DesignMatch when;
    private DesignSet group;
    private int count;
    @JsonProperty("covered_areas")
    private List<List<Coordinate>> coveredAreas;

    public BigDecimal getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(BigDecimal rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public DesignMatch getWhen() {
        return when;
    }

    public void setWhen(DesignMatch when) {
        this.when = when;
    }

    public DesignSet getGroup() {
        return group;
    }

    public void setGroup(DesignSet group) {
        this.group = group;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<List<Coordinate>> getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(List<List<Coordinate>> coveredAreas) {
        this.coveredAreas = coveredAreas;
    }

    public static class Coordinate {
        private int row;
        private int column;

        public Coordinate() {
        }

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        @JsonCreator
        public static Coordinate from(String s) {
            String[] split = s.split(":");
            return new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
    }
}
