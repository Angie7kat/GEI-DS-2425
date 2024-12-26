package e3;

public class Property {
    private final String catastro;
    private final PropertyType tipo;
    private final String direccion;
    private final String codigoPostal;
    private final int dimensiones;
    private final int habitaciones;
    private final int banos;

    public Property(PropertyType tipo, String catastro, String direccion,
                    String codigoPostal, int dimensiones, int habitaciones, int banos) {
        this.catastro = catastro;
        this.tipo = tipo;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.dimensiones = dimensiones;
        this.habitaciones = habitaciones;
        this.banos = banos;
    }

    public Property(Property p) {
        this.catastro = p.catastro;
        this.tipo = p.tipo;
        this.direccion = p.direccion;
        this.codigoPostal = p.codigoPostal;
        this.dimensiones = p.dimensiones;
        this.habitaciones = p.habitaciones;
        this.banos = p.banos;
    }

    public String getCatastro() { return catastro; }
    public PropertyType getTipo() { return tipo; }
    public String getDireccion() { return direccion; }
    public String getCodigoPostal() { return codigoPostal; }
    public int getDimensiones() { return dimensiones; }
    public int getHabitaciones() { return habitaciones; }
    public int getBanos() { return banos; }

    @Override
    public String toString() {
        return tipo + "\n" +
                catastro + "\n" +
                direccion + "\n" +
                codigoPostal + "\n" +
                dimensiones + " meters, " +
                habitaciones + " rooms, " +
                banos + " bathrooms" + "\n";}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Property p = (Property) obj;
        return catastro.equals(p.catastro);
    }

    @Override
    public int hashCode() {
        return 31 * catastro.hashCode();
    }
}