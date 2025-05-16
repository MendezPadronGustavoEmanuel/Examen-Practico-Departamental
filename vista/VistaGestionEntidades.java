package vista;

import modelo.Entidad;
import modelo.DefinicionAtributo;
import modelo.ListaEntidades;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Internal frame para la gestión (CRUD) de entidades.
 */
public class VistaGestionEntidades extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private JTable tabla;
    private DefaultTableModel tablaModel;
    private JPanel panelFormulario;
    private JTextField[] campos;
    private JButton btnCrear, btnActualizar, btnEliminar;

    private List<DefinicionAtributo> defs;

    public VistaGestionEntidades() {
        super("📁 Gestión de Entidades", true, true, true, true);
        setSize(950, 700);
        setLayout(new BorderLayout());

        setFontRecursivo(this, new Font("Poppins", Font.PLAIN, 12));

        // Tabla
        tablaModel = new DefaultTableModel();
        tabla = new JTable(tablaModel);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        // Panel de formulario (campos + botones)
        panelFormulario = new JPanel(new GridBagLayout());
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(panelFormulario, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        // Split pane para mostrar tabla y formulario horizontalmente
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollTabla, panelDerecho);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(500);
        add(splitPane, BorderLayout.CENTER);

        // Listener de selección
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int idx = getIndiceSeleccionado();
                    rellenarFormulario(idx);
                    btnActualizar.setEnabled(idx >= 0);
                    btnEliminar.setEnabled(idx >= 0);
                }
            }
        });

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public JButton getBtnCrear()      { return btnCrear; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar()   { return btnEliminar; }

    public void actualizarTabla(List<Entidad> lista, List<DefinicionAtributo> defs) {
        this.defs = defs;

        // Columnas
        String[] cols = defs.stream()
                .map(DefinicionAtributo::getNombre)
                .toArray(String[]::new);
        tablaModel.setColumnIdentifiers(cols);

        // Filas
        tablaModel.setRowCount(0);
        for (Entidad e : lista) {
            Object[] row = defs.stream()
                    .map(d -> e.getAtributo(d.getNombre()))
                    .toArray();
            tablaModel.addRow(row);
        }

        // Formulario dinámico
        panelFormulario.removeAll();
        campos = new JTextField[defs.size()];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < defs.size(); i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panelFormulario.add(new JLabel(defs.get(i).getNombre() + ":"), gbc);

            gbc.gridx = 1;
            campos[i] = new JTextField(15);
            panelFormulario.add(campos[i], gbc);
        }

        panelFormulario.revalidate();
        panelFormulario.repaint();
    }

    private void rellenarFormulario(int idx) {
        for (int i = 0; i < campos.length; i++) {
            if (idx >= 0) {
                Entidad e = ListaEntidades.getInstancia().listarEntidades().get(idx);
                Object val = e.getAtributo(defs.get(i).getNombre());
                campos[i].setText(val != null ? val.toString() : "");
            } else {
                campos[i].setText("");
            }
        }
    }

    public Entidad leerEntidad(List<DefinicionAtributo> defs) {
        Entidad e = new Entidad();
        for (int i = 0; i < defs.size(); i++) {
            String text = campos[i].getText().trim();
            Object val = (defs.get(i).getTipo() == DefinicionAtributo.Tipo.INTEGER)
                         ? Integer.parseInt(text)
                         : text;
            e.setAtributo(defs.get(i).getNombre(), val);
        }
        return e;
    }

    public int getIndiceSeleccionado() {
        return tabla.getSelectedRow();
    }

    public void clearFormulario() {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public void clearSelection() {
        tabla.clearSelection();
    }

    public void mostrarError(String err) {
        JOptionPane.showInternalMessageDialog(this, err, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /** Aplica fuente recursivamente */
    private void setFontRecursivo(Component comp, Font font) {
        comp.setFont(font);
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                setFontRecursivo(child, font);
            }
        }
    }
}

