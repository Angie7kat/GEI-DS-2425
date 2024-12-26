package e2;

import java.util.Objects;

public record EuroCoin(int valor, String color, Pais pais, String disenho, int anho)
        implements Comparable<EuroCoin> {

    public EuroCoin(int valor, String color, Pais pais, String disenho, int anho) {
        this.valor = valor;
        this.color = color;
        this.pais = pais;
        this.disenho = disenho;
        this.anho = anho;
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
                Objects.equals(disenho, euroCoin.disenho) &&
                Objects.equals(color, euroCoin.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(valor, color, pais, disenho);
    }

    @Override
    public String toString() {
        return "EuroCoin{" +
                "valor=" + valor +
                ", color='" + (color != null ? color : "null") + '\'' +
                ", pais=" + (pais != null ? pais.getName() : "null") +
                ", diseño='" + (disenho != null ? disenho : "null") + '\'' +
                ", año=" + anho +
                '}';
    }

    @Override
    public int compareTo(EuroCoin other) {
        // primero valor descendente, luego país alfabético y luego diseño alfabético
        int valueComparison = Integer.compare(other.valor, this.valor);
        if (valueComparison != 0) return valueComparison;
        int countryComparison = this.pais.getName().compareTo(other.pais.getName());
        if (countryComparison != 0) return countryComparison;
        return this.disenho.compareTo(other.disenho);
    }
}