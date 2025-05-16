package controlador;

import vista.VistaGestionEntidades;
import modelo.Entidad;
import modelo.ListaEntidades;
import modelo.DefinicionAtributo;
import comando.ComandoCrear;
import comando.ComandoActualizar;
import comando.ComandoEliminar;
import observador.ObservadorModelo;
import dao.JsonDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para operaciones CRUD de Entidad y Observador del modelo.
 */
public class ControladorEntidad implements ActionListener, ObservadorModelo {
    private VistaGestionEntidades view;
    private List<DefinicionAtributo> defs;

    public ControladorEntidad(VistaGestionEntidades view) {
        this.view = view;
        this.defs = List.of();

        // 1) Cargo entidades ya existentes de JSON
        try {
            List<Entidad> inicial = JsonDAO.getInstancia().listar();
            ListaEntidades.getInstancia().cargarInicial(inicial);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 2) Registro observador y refresco inicial
        ListaEntidades.getInstancia().agregarObservador(this);
        actualizar();

        // 3) Registro de botones
        view.getBtnCrear().addActionListener(this);
        view.getBtnActualizar().addActionListener(this);
        view.getBtnEliminar().addActionListener(this);
    }


    /** Inyecta o actualiza las definiciones y refresca UI */
    public void setDefiniciones(List<DefinicionAtributo> defs) {
        this.defs = defs;
        actualizar();
    }

    /** Se llama tras carga inicial o cada cambio en ListaEntidades */
    @Override
    public void actualizar() {
        view.actualizarTabla(
            ListaEntidades.getInstancia().listarEntidades(),
            defs
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == view.getBtnCrear()) {
                Entidad entidad = view.leerEntidad(defs);
                new ComandoCrear(entidad).ejecutar();
                view.clearFormulario();
                view.clearSelection();

            } else if (e.getSource() == view.getBtnActualizar()) {
                int idx = view.getIndiceSeleccionado();
                Entidad entidad = view.leerEntidad(defs);
                new ComandoActualizar(idx, entidad).ejecutar();
                view.clearSelection();

            } else if (e.getSource() == view.getBtnEliminar()) {
                int idx = view.getIndiceSeleccionado();
                Entidad entidad = ListaEntidades
                                    .getInstancia()
                                    .listarEntidades()
                                    .get(idx);
                new ComandoEliminar(entidad).ejecutar();
                view.clearFormulario();
                view.clearSelection();
            }
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }
}
