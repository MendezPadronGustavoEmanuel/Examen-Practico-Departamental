package observador;

/**
 * Interfaz para clases que desean observar cambios en un modelo.
 */
public interface ObservadorModelo {
    /**
     * Método llamado cuando el modelo notifica un cambio.
     */
    void actualizar();
}
