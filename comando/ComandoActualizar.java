package comando;

import dao.JsonDAO;
import modelo.Entidad;
import modelo.ListaEntidades;

/**
 * Comando para actualizar una entidad existente.
 */
public class ComandoActualizar implements Comando {
    private int indice;
    private Entidad nuevaEntidad;

    public ComandoActualizar(int indice, Entidad nuevaEntidad) {
        this.indice        = indice;
        this.nuevaEntidad  = nuevaEntidad;
    }

    @Override
    public void ejecutar() throws Exception {
        // Actualiza en JSON
        JsonDAO.getInstancia().actualizar(indice, nuevaEntidad);
        // Actualiza en el modelo observable
        ListaEntidades.getInstancia().actualizarEntidad(indice, nuevaEntidad);
    }
}
