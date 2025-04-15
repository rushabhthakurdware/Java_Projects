import java.io.*;
import java.util.*;

public class StockFileHandler {
    public static List<Stock> loadStocks(String fileName) {
        List<Stock> stocks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                if (line.trim().isEmpty()) continue;

                // Split by tab instead of comma
                String[] parts = line.split("\t");

                if (parts.length < 9) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    String symbol = parts[2].trim();       // SYMBOL
                    String security = parts[3].trim();     // SECURITY
                    double open = Double.parseDouble(parts[5]);
                    double high = Double.parseDouble(parts[6]);
                    double low = Double.parseDouble(parts[7]);
                    double close = Double.parseDouble(parts[8]);

                    stocks.add(new Stock(symbol, security, open, close, high, low));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line due to number format issue: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading stock file: " + e.getMessage());
        }

        return stocks;
    }
}
