package e3;

import java.util.Objects;

public record EuroCoin(int valor, String color, Pais pais, String disenho, int anho)
        implements Comparable<EuroCoin> {

    public EuroCoin {
        Objects.requireNonNull(color, "Color cannot be null");
        Objects.requireNonNull(pais, "Pais cannot be null");
        Objects.requireNonNull(disenho, "Disenho cannot be null");
    }

    public int getValue() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public Pais getCountry() {
        return pais;
    }

    public String getDesign() {
        return disenho;
    }

    public int getYear() {
        return anho;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) obj;
        return valor == euroCoin.valor &&
                pais == euroCoin.pais &&
                disenho.equals(euroCoin.disenho) &&
                color.equals(euroCoin.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, color, pais, disenho);
    }

    @Override
    public String toString() {
        return "EuroCoin{" +
                "valor=" + valor +
                ", color='" + color + '\'' +
                ", pais=" + pais.getName() +
                ", diseño='" + disenho + '\'' +
                ", año=" + anho +
                '}';
    }

    @Override
    public int compareTo(EuroCoin other) {
        return Integer.compare(this.valor, other.valor);
    }
}