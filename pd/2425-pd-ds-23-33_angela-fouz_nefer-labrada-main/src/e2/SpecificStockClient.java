package e2;

public class SpecificStockClient implements Observer {
    private final String name;
    private final String interestedSymbol;

    public SpecificStockClient(String name, String interestedSymbol) {
        this.name = name;
        this.interestedSymbol = interestedSymbol;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Stock stock) {
            if (stock.getSymbol().equals(interestedSymbol)) {
                System.out.println(name + " - Actualización para " + stock.getSymbol() + ": Precio de cierre = " + stock.getClosePrice());
            }
        }
    }
}