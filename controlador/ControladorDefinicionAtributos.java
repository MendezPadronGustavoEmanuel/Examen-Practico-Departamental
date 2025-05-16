package controlador;

import vista.VistaDefinicionAtributos;
import modelo.DefinicionAtributo;
import dao.JsonDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para la definición dinámica de atributos.
 */
public class ControladorDefinicionAtributos implements ActionListener {
    private VistaDefinicionAtributos view;
    private JsonDAO dao;
    private ControladorEntidad ctrlEntidad;

    public ControladorDefinicionAtributos(VistaDefinicionAtributos view,
                                          ControladorEntidad ctrlEntidad) {
        this.view        = view;
        this.ctrlEntidad = ctrlEntidad;
        this.dao         = JsonDAO.getInstancia();

        // Botón guardar
        view.getBtnGuardar().addActionListener(this);

        // Cargo definiciones previas
        List<DefinicionAtributo> defs = dao.listarDefiniciones();
        view.mostrarDefiniciones(defs);
        ctrlEntidad.setDefiniciones(defs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<DefinicionAtributo> defs = view.obtenerDefiniciones();
            dao.reemplazarDefiniciones(defs);
            view.mostrarMensaje("Definiciones guardadas");
            ctrlEntidad.setDefiniciones(defs);
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }
}

