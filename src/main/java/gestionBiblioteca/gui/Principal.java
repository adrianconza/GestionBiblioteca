package gestionBiblioteca.gui;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class Principal {

	private JFrame frmGestionDeBiblioteca;
	JDesktopPane desktop;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Principal window = new Principal();
		window.frmGestionDeBiblioteca.setVisible(true);
	}

	public Principal() {
		initialize();
	}

	private void initialize() {
		frmGestionDeBiblioteca = new JFrame();
		frmGestionDeBiblioteca.setTitle("Gestion de Biblioteca");
		frmGestionDeBiblioteca.setBounds(100, 100, 786, 503);
		frmGestionDeBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmGestionDeBiblioteca.setJMenuBar(menuBar);

		JMenu mnBiblioteca = new JMenu("Biblioteca");
		menuBar.add(mnBiblioteca);

		JMenu mnMaterialBibliografico = new JMenu("Material Bibliografico");
		menuBar.add(mnMaterialBibliografico);

		JMenu mnPersona = new JMenu("Persona");
		menuBar.add(mnPersona);

		desktop = new JDesktopPane();
		desktop.setLayout(null);
		desktop.setBackground(Color.LIGHT_GRAY);
		frmGestionDeBiblioteca.setContentPane(desktop);
	}
}
