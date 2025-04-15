import java.util.*;

public class StockManager {
    static List<Stock> stocks = StockFileHandler.loadStocks("stocks.csv");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Stock Data Analyzer ===");
            System.out.println("1. Search stock by name or symbol");
            System.out.println("2. Show highest/lowest stock price");
            System.out.println("3. Show gain/loss of all stocks");
            System.out.println("4. Show top 5 gainers");
            System.out.println("5. Show top 5 losers");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (ch) {
                case 1 -> searchStock();
                case 2 -> showHighLow();
                case 3 -> showGainLoss();
                case 4 -> topMovers(true);
                case 5 -> topMovers(false);
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void searchStock() {
        System.out.print("Enter stock symbol or name: ");
        String keyword = sc.nextLine().toLowerCase();
        for (Stock s : stocks) {
            if (s.symbol.toLowerCase().contains(keyword) || s.security.toLowerCase().contains(keyword)) {
                System.out.println(s);
            }
        }
    }

    static void showHighLow() {
        Stock high = Collections.max(stocks, Comparator.comparingDouble(s -> s.highPrice));
        Stock low = Collections.min(stocks, Comparator.comparingDouble(s -> s.lowPrice));
        System.out.println("Highest: " + high);
        System.out.println("Lowest : " + low);
    }

    static void showGainLoss() {
        for (Stock s : stocks) {
            System.out.printf("%s: Gain/Loss = %.2f\n", s.symbol, s.getGainLoss());
        }
    }

    static void topMovers(boolean gainers) {
        stocks.sort(Comparator.comparingDouble(Stock::getGainLoss).reversed());
        if (!gainers) Collections.reverse(stocks);
        System.out.println((gainers ? "Top Gainers:" : "Top Losers:"));
        for (int i = 0; i < 5 && i < stocks.size(); i++) {
            System.out.println(stocks.get(i));
        }
    }
}
