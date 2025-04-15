public class Stock {
    String symbol, security;
    double openPrice, closePrice, highPrice, lowPrice;

    public Stock(String symbol, String security, double openPrice, double closePrice,
                 double highPrice, double lowPrice) {
        this.symbol = symbol;
        this.security = security;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
    }

    public double getGainLoss() {
        return closePrice - openPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): Open %.2f, Close %.2f, High %.2f, Low %.2f",
                security, symbol, openPrice, closePrice, highPrice, lowPrice);
    }
}
