package e3;

public class Ad {
    private final String agencia;
    private final Property propiedad;
    private final AdType tipoAnuncio;
    private double valor;

    public Ad(String agencia, Property propiedad, AdType tipoAnuncio, double valor) {
        this.agencia = agencia;
        this.propiedad = new Property(propiedad);
        this.tipoAnuncio = tipoAnuncio;
        this.valor = valor;
    }

    public Ad(Ad a) {
        this.agencia = a.agencia;
        this.propiedad = new Property(a.propiedad);
        this.tipoAnuncio = a.tipoAnuncio;
        this.valor = a.valor;
    }

    public String getAgencia() { return agencia; }
    public AdType getTipoAnuncio() { return tipoAnuncio; }
    public Property getPropiedad() { return propiedad; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        String agenciaStr = (agencia != null) ? agencia : "null";
        String tipoAnuncioStr = (tipoAnuncio != null) ? String.valueOf(tipoAnuncio) : "null";

        return agenciaStr + "\n" + tipoAnuncioStr + "\n" + propiedad + "\n" + valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ad a = (Ad) obj;
        return propiedad.equals(a.getPropiedad()) && valor == a.getValor();
    }

    @Override
    public int hashCode() { //hash
        return 31 * propiedad.hashCode() + Double.hashCode(valor);
    }

    public boolean isPropertyEqual(Ad a) {
        return a != null && this.propiedad.equals(a.getPropiedad());
    }

    public boolean isPriceNormal() {
        if (tipoAnuncio == AdType.RENTAL) {
            return (valor < 200000 && valor > 10000);
        } else {
            return valor >= 99999 && valor <= 9999999;
        }
    }

    public double priceMetersEuros() {
        int metros = this.propiedad.getDimensiones();
        if (metros <= 0) {
            throw new ArithmeticException("Dimensiones invalidas");
        }
        return valor / metros;
    }

    public void dropPrice(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }
        valor = valor * (1 - porcentaje / 100);
    }

    public double getPriceInEuros() {
        return this.valor;
    }
}