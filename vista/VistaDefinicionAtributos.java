package vista;

import modelo.DefinicionAtributo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Internal frame para definir atributos dinámicos de la Entidad.
 */
public class VistaDefinicionAtributos extends JInternalFrame {

    private JPanel panel;
    private List<JTextField> camposNombre;
    private List<JComboBox<String>> combosTipo;
    private List<JCheckBox> checkUnico;
    private JButton btnGuardar;

    public VistaDefinicionAtributos() {
        super("Definir Atributos", true, true, true, true);
        setSize(400, 300);
        setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(0, 4, 5, 5));
        camposNombre = new ArrayList<>();
        combosTipo   = new ArrayList<>();
        checkUnico   = new ArrayList<>();

        // Inicialmente 3 filas para 3 atributos
        for (int i = 0; i < 3; i++) {
            JTextField txtNombre = new JTextField();
            JComboBox<String> cbTipo = new JComboBox<>(new String[]{"STRING", "INTEGER"});
            JCheckBox chUnico = new JCheckBox();

            camposNombre.add(txtNombre);
            combosTipo.add(cbTipo);
            checkUnico.add(chUnico);

            panel.add(new JLabel("Nombre:"));
            panel.add(txtNombre);
            panel.add(new JLabel("Tipo:"));
            panel.add(cbTipo);
            panel.add(new JLabel("Único:"));
            panel.add(chUnico);
        }

        btnGuardar = new JButton("Guardar Definiciones");

        add(panel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public List<DefinicionAtributo> obtenerDefiniciones() {
        List<DefinicionAtributo> defs = new ArrayList<>();
        for (int i = 0; i < camposNombre.size(); i++) {
            String nombre = camposNombre.get(i).getText().trim();
            if (nombre.isEmpty()) continue;
            String tipoStr = (String) combosTipo.get(i).getSelectedItem();
            boolean unico = checkUnico.get(i).isSelected();
            DefinicionAtributo.Tipo tipo = tipoStr.equals("INTEGER")
                                            ? DefinicionAtributo.Tipo.INTEGER
                                            : DefinicionAtributo.Tipo.STRING;
            defs.add(new DefinicionAtributo(nombre, tipo, unico));
        }
        return defs;
    }

    public void mostrarDefiniciones(List<DefinicionAtributo> defs) {
        for (int i = 0; i < Math.min(defs.size(), camposNombre.size()); i++) {
            DefinicionAtributo d = defs.get(i);
            camposNombre.get(i).setText(d.getNombre());
            combosTipo.get(i).setSelectedItem(d.getTipo().name());
            checkUnico.get(i).setSelected(d.isUnico());
        }
    }

    // <<< Aquí cambian los métodos de diálogo >>>
    public void mostrarMensaje(String msg) {
        JOptionPane.showInternalMessageDialog(
            this,
            msg,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void mostrarError(String err) {
        JOptionPane.showInternalMessageDialog(
            this,
            err,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

