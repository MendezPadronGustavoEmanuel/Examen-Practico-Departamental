package observador;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para modelos que pueden ser observados por vistas u otros observadores.
 */
public class ModeloObservable {
    private List<ObservadorModelo> observadores = new ArrayList<>();

    /**
     * Añade un observador que será notificado de cambios.
     */
    public void agregarObservador(ObservadorModelo observador) {
        observadores.add(observador);
    }

    /**
     * Remueve un observador.
     */
    public void removerObservador(ObservadorModelo observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica a todos los observadores de un cambio en el modelo.
     */
    protected void notificar() {
        for (ObservadorModelo obs : observadores) {
            obs.actualizar();
        }
    }
}
