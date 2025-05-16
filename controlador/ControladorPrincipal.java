package controlador;

import vista.VentanaPrincipal;
import vista.VistaDefinicionAtributos;
import vista.VistaGestionEntidades;

import javax.swing.*;
import java.awt.*;

public class ControladorPrincipal {
    public static void main(String[] args) {
        // Cargar fuente Poppins
        try {
            Font poppins = Font.createFont(Font.TRUETYPE_FONT,
                    ControladorPrincipal.class.getResourceAsStream("/fonts/Poppins-Regular.ttf"))
                    .deriveFont(14f);

            UIManager.put("Label.font", poppins);
            UIManager.put("Button.font", poppins.deriveFont(13f));
            UIManager.put("Table.font", poppins);
            UIManager.put("TableHeader.font", poppins.deriveFont(Font.BOLD, 14f));
            UIManager.put("InternalFrame.titleFont", poppins.deriveFont(Font.BOLD, 16f));
        } catch (Exception e) {
            System.err.println("No se pudo cargar la fuente Poppins, se usará la predeterminada.");
            e.printStackTrace();
        }

        // Lanzar ventana en el hilo de UI
        EventQueue.invokeLater(() -> new ControladorPrincipal());
    }

    private ControladorPrincipal() {
        // Crear ventana principal con título
        VentanaPrincipal ventana = new VentanaPrincipal("Sistema de Gestión Genérico");
        JDesktopPane desktop = ventana.getDesktopPane();
        ventana.setVisible(true);

        // Vista y controlador de Gestión de Entidades
        VistaGestionEntidades viewEnt = new VistaGestionEntidades();
        ControladorEntidad ctrlEnt = new ControladorEntidad(viewEnt);
        viewEnt.setBounds(420, 10, 900, 450); // A la derecha
        desktop.add(viewEnt);
        viewEnt.setVisible(true);

        // Vista y controlador de Definición de Atributos
        VistaDefinicionAtributos viewDef = new VistaDefinicionAtributos();
        new ControladorDefinicionAtributos(viewDef, ctrlEnt);
        viewDef.setBounds(10, 10, 400, 300); // A la izquierda
        desktop.add(viewDef);
        viewDef.setVisible(true);
    }
}
