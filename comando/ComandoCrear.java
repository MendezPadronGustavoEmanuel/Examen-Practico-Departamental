package comando;

import dao.JsonDAO;
import modelo.Entidad;
import modelo.ListaEntidades;

/**
 * Comando para crear (persistir y añadir a la lista) una nueva entidad.
 */
public class ComandoCrear implements Comando {
    private Entidad entidad;

    public ComandoCrear(Entidad entidad) {
        this.entidad = entidad;
    }

    @Override
    public void ejecutar() throws Exception {
        // Primero persiste en JSON
        JsonDAO.getInstancia().crear(entidad);
        // Luego notifica al modelo observable
        ListaEntidades.getInstancia().agregarEntidad(entidad);
    }
}
