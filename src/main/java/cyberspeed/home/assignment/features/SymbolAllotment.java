package cyberspeed.home.assignment.features;

import cyberspeed.home.assignment.config.BlockArrangement;

import java.util.*;

/**
 * @author akash.kumar
 */
/**
 * A class for allotting symbols based on their weights.
 */
public class SymbolAllotment {
    private final List<String> symbols = new ArrayList<>();
    private final List<Double> weights = new ArrayList<>();
    private final Random random = new Random();
    private double totalWeight = 0;

    /**
     * Constructs a SymbolAllotment object with symbols and their weights from a BlockArrangement.
     *
     * @param blockArrangement The BlockArrangement containing symbols and their weights.
     */
    public SymbolAllotment(BlockArrangement blockArrangement) {
        blockArrangement.getSymbols().forEach(this::addSymbolWithWeight);
    }

    /**
     * Adds a symbol with its corresponding weight.
     *
     * @param symbol The symbol to add.
     * @param weight The weight of the symbol.
     */
    private void addSymbolWithWeight(String symbol, double weight) {
        if (weight > 0) {
            symbols.add(symbol);
            weights.add(weight);
            totalWeight += weight;
        }
    }

    /**
     * Returns the next symbol based on their weights.
     *
     * @return The next symbol.
     */
    public String next() {
        double value = random.nextDouble() * totalWeight;
        int index = findSymbolIndex(value);
        return symbols.get(index);
    }

    /**
     * Finds the index of the symbol corresponding to the given value.
     *
     * @param value The value to search for.
     * @return The index of the symbol.
     */
    private int findSymbolIndex(double value) {
        double sum = 0;
        for (int i = 0; i < weights.size(); i++) {
            sum += weights.get(i);
            if (value < sum) {
                return i;
            }
        }
        return weights.size() - 1;
    }
}
