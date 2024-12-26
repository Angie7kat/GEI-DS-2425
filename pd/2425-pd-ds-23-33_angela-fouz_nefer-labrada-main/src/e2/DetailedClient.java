package e2;

public class DetailedClient implements Observer {
    private final String name;

    public DetailedClient(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Stock stock) {
            System.out.println(name + " - Actualización detallada de " + stock.getSymbol() + ":");
            System.out.println("  Cierre: " + stock.getClosePrice());
            System.out.println("  Valor Alto: " + stock.getHighPrice());
            System.out.println("  Valor Bajo: " + stock.getLowPrice());
            System.out.println("  Volumen: " + stock.getVolume());
        }
    }
}
