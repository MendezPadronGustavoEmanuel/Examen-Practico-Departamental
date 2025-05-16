// VentanaPrincipal.java
package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JDesktopPane desktopPane;

    public VentanaPrincipal(String titulo) {
        super(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 600);
        setLocationRelativeTo(null);


        // Título grande arriba
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));
        add(lblTitulo, BorderLayout.NORTH);

        // Desktop bajo el título
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);
        
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }
}

