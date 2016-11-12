package gestionBiblioteca.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class JIFPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktop;
	private int x = 25;
	private int y = 25;

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
		setBounds(100, 100, 1000, 700);
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
				JIFGestionBiblioteca jifGestionBiblioteca = new JIFGestionBiblioteca(x, y);
				jifGestionBiblioteca.moveToFront();
				desktop.add(jifGestionBiblioteca);
				posicionVentanas();
				jifGestionBiblioteca.moveToFront();
				try {
					jifGestionBiblioteca.setSelected(true);
				} catch (PropertyVetoException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnBiblioteca.add(mntmGestionar);

		JMenuItem mntmMaterialBibliografico = new JMenuItem("Material Bibliografico");
		mntmMaterialBibliografico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFMaterialBibliografico jifMaterialBibliografico = new JIFMaterialBibliografico(x, y);
				jifMaterialBibliografico.moveToFront();
				desktop.add(jifMaterialBibliografico);
				posicionVentanas();
				jifMaterialBibliografico.moveToFront();
				try {
					jifMaterialBibliografico.setSelected(true);
				} catch (PropertyVetoException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnBiblioteca.add(mntmMaterialBibliografico);

		JMenuItem mntmPersona = new JMenuItem("Persona");
		mntmPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFPersona jifPersona = new JIFPersona(x, y);
				jifPersona.moveToFront();
				desktop.add(jifPersona);
				posicionVentanas();
				jifPersona.moveToFront();
				try {
					jifPersona.setSelected(true);
				} catch (PropertyVetoException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnBiblioteca.add(mntmPersona);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFAcercaDe jifAcercaDe = new JIFAcercaDe(x, y);
				desktop.add(jifAcercaDe);
				posicionVentanas();
				jifAcercaDe.moveToFront();
				try {
					jifAcercaDe.setSelected(true);
				} catch (PropertyVetoException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnAyuda.add(mntmAcercaDe);
	}

	public void posicionVentanas() {
		x += 25;
		y += 25;
		if (x == 200)
			x = 25;
		if (y == 200)
			y = 25;
	}
}
