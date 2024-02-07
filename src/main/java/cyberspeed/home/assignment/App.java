package cyberspeed.home.assignment;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import cyberspeed.home.assignment.config.Config;
import cyberspeed.home.assignment.features.PuzzlePattern;
import cyberspeed.home.assignment.features.ScratchGame;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author akash.kumar
 */
public class App {
    public static void main(String[] args) throws IOException {
        String configPath = null;
        double betAmount = 0;

        for (int i = 0; i < args.length; i++) {
            if ("--config".equals(args[i]) && i + 1 < args.length) {
                configPath = args[++i];
            } else if ("--betting-amount".equals(args[i]) && i + 1 < args.length) {
                betAmount = Double.parseDouble(args[++i]);
            }
        }

        if (configPath == null || betAmount <= 0) {
            System.out.println(
                    "Usage: java -jar <your-jar-file> --config <path-to-config> --betting-amount <amount>");
            return;
        }

        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();

        Config config = objectMapper.readValue(new File(configPath), Config.class);

        PuzzlePattern output = new ScratchGame(config).scratch(BigDecimal.valueOf(betAmount));

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(System.out, output);
    }
}
