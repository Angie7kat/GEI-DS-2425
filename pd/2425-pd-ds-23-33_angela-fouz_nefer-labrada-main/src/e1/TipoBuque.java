package e1;

public enum TipoBuque {
    DE("Destructor de Escolta", "Ultraligero"),
    DD("Destructor", "Ultraligero"),

    CL("Crucero Ligero", "Ligero"),
    AV("Portahidros", "Ligero"),

    CA("Crucero Pesado", "Pesado"),
    CV("Portaaviones", "Pesado"),

    BB("Acorazado", "Ultrapesado");

    private final String nombre;
    private final String categoria;

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    TipoBuque(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

}