package comando;

import dao.JsonDAO;
import modelo.Entidad;
import modelo.ListaEntidades;

/**
 * Comando para eliminar una entidad.
 */
public class ComandoEliminar implements Comando {
    private Entidad entidad;

    public ComandoEliminar(Entidad entidad) {
        this.entidad = entidad;
    }

    @Override
    public void ejecutar() throws Exception {
        // Elimina de JSON
        JsonDAO.getInstancia().eliminar(entidad);
        // Elimina del modelo observable
        ListaEntidades.getInstancia().eliminarEntidad(entidad);
    }
}
