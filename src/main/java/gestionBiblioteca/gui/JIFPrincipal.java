package gestionBiblioteca.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class JIFPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktop;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		JIFPrincipal window = new JIFPrincipal();
		window.setVisible(true);
	}

	public JIFPrincipal() {
		initialize();
	}

	private void initialize() {
		setTitle("Gestion de Biblioteca");
		setBounds(100, 100, 850, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktop = new JDesktopPane();
		desktop.setLayout(null);
		desktop.setBackground(Color.LIGHT_GRAY);
		setContentPane(desktop);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnBiblioteca = new JMenu("Biblioteca");
		menuBar.add(mnBiblioteca);

		JMenuItem mntmGestionar = new JMenuItem("Gestionar");
		mntmGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFGestionBiblioteca jifGestionBiblioteca = new JIFGestionBiblioteca();
				desktop.add(jifGestionBiblioteca);
			}
		});
		mnBiblioteca.add(mntmGestionar);

		JMenuItem mntmMaterialBibliografico = new JMenuItem("Material Bibliografico");
		mntmMaterialBibliografico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFMaterialBibliografico jifMaterialBibliografico = new JIFMaterialBibliografico();
				desktop.add(jifMaterialBibliografico);
			}
		});
		mnBiblioteca.add(mntmMaterialBibliografico);

		JMenuItem mntmPersona = new JMenuItem("Persona");
		mntmPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFPersona jifPersona = new JIFPersona();
				desktop.add(jifPersona);
			}
		});
		mnBiblioteca.add(mntmPersona);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFAcercaDe jifAcercaDe = new JIFAcercaDe();
				desktop.add(jifAcercaDe);
			}
		});
		mnAyuda.add(mntmAcercaDe);
	}
}
