package modelo;

import observador.ModeloObservable;

import java.util.List;

public class ListaEntidades extends ModeloObservable {
    private static ListaEntidades instancia;
    private ListaGenerica<Entidad> lista;

    private ListaEntidades() {
        this.lista = new ListaGenerica<>();
    }

    public static synchronized ListaEntidades getInstancia() {
        if (instancia == null) {
            instancia = new ListaEntidades();
        }
        return instancia;
    }

    /** Carga inicial de entidades y notifica una sola vez */
    /** Carga inicial de entidades y notifica una sola vez */
    public void cargarInicial(List<Entidad> inicial) {
        // Creamos una lista nueva vacía
        this.lista = new ListaGenerica<>();
        // Y añadimos todos los elementos de la lista pasada
        for (Entidad e : inicial) {
            this.lista.agregar(e);
        }
        notificar();
    }

    public void agregarEntidad(Entidad e) {
        lista.agregar(e);
        notificar();
    }

    public boolean eliminarEntidad(Entidad e) {
        boolean ok = lista.eliminar(e);
        if (ok) notificar();
        return ok;
    }

    public void actualizarEntidad(int index, Entidad e) {
        lista.actualizar(index, e);
        notificar();
    }

    public List<Entidad> listarEntidades() {
        return lista.listar();
    }
}
