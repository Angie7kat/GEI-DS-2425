package e4;

import java.util.Objects;

public record EuroCoin (int valor, String color, Pais pais, String disenho, int anho) {

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
                ", color='" + (color != null ? color : "null") + '\'' +
                ", pais=" + (pais != null ? pais.getName() : "null") +
                ", diseño='" + (disenho != null ? disenho : "null") + '\'' +
                ", año=" + anho +
                '}';
    }
}
