package comando;

/**
 * Interfaz que define un comando ejecutable.
 */
public interface Comando {
    /**
     * Ejecuta la acción encapsulada por este comando.
     * @throws Exception en caso de fallo en la operación
     */
    void ejecutar() throws Exception;
}
