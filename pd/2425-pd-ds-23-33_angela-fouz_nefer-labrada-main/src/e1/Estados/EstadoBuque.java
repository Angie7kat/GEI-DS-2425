package e1.Estados;

import e1.Buque;
import e1.Base;

public interface EstadoBuque {
    void enLaBase(Buque buque, Base base);
    void ejercicioNaval(Buque buque, Base base);
    void solicitarReparacion(Buque buque, Base base);
    void iniciarReparacion(Buque buque, Base base);
    void completarReparacion(Buque buque, Base base);
    void repararExpress(Buque buque, Base base);
    void cancelarReparacion(Buque buque, Base base);
    void desmantelar(Buque buque, Base base);
}
