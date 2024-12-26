package e2;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {
    private final String symbol;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private int volume;
    private final List<Observer> observers = new ArrayList<>();

    public Stock(String symbol) {
        if (symbol == null || symbol.length() > 4) {
            throw new IllegalArgumentException("El símbolo del stock debe ser de 1-4 dígitos");
        }
        this.symbol = symbol;
    }

    public void updateStock(double closePrice, double highPrice, double lowPrice, int volume) {
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }


    public void detachAll() {
        observers.clear();
    }

    public String getFormattedData() {
        return String.format("%s: Cierre=%.1f, Valor Alto=%.1f, Valor Bajo=%.1f, Volumen=%d",
                symbol, closePrice, highPrice, lowPrice, volume);
    }

    // Getters
    public String getSymbol() { return symbol; }
    public double getClosePrice() { return closePrice; }
    public double getHighPrice() { return highPrice; }
    public double getLowPrice() { return lowPrice; }
    public int getVolume() { return volume; }
    public int getObserversCount() { return observers.size(); }
}