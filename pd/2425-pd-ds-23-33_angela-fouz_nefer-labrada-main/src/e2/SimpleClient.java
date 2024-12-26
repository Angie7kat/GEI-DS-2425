package e2;

public class SimpleClient implements Observer {
    private final String name;

    public SimpleClient(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Stock stock) {
            System.out.println(name + " - Actualización para: " + stock.getSymbol() + " con precio de cierre: " + stock.getClosePrice());
        }
    }
}