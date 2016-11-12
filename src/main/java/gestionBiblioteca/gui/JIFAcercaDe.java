package gestionBiblioteca.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gestionBiblioteca.utils.UtilsArchivos;

public class JIFAcercaDe extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public JIFAcercaDe() {
		super("Acerca De", false, true, false, true);
		setBounds(40, 25, 726, 287);
		getContentPane().setLayout(null);

		String font = "SansSerif";

		JLabel lbltitulo = new JLabel("GESTION BIBLIOTECA");
		lbltitulo.setForeground(new Color(51, 102, 255));
		lbltitulo.setFont(new Font(font, Font.BOLD, 20));
		lbltitulo.setBounds(243, 11, 223, 28);
		getContentPane().add(lbltitulo);

		JLabel lblVersion = new JLabel("VERSION 1.0");
		lblVersion.setForeground(new Color(51, 102, 255));
		lblVersion.setFont(new Font(font, Font.PLAIN, 20));
		lblVersion.setBounds(290, 50, 130, 28);
		getContentPane().add(lblVersion);

		JLabel lblPerteneceA = new JLabel("PERTENECE A:");
		lblPerteneceA.setForeground(new Color(51, 102, 255));
		lblPerteneceA.setFont(new Font(font, Font.PLAIN, 18));
		lblPerteneceA.setBounds(10, 97, 208, 28);
		getContentPane().add(lblPerteneceA);

		JLabel lblAdrianConza = new JLabel("ADRIAN CONZA");
		lblAdrianConza.setForeground(new Color(51, 102, 255));
		lblAdrianConza.setFont(new Font(font, Font.PLAIN, 16));
		lblAdrianConza.setBounds(228, 97, 474, 28);
		getContentPane().add(lblAdrianConza);

		JLabel lblCelular = new JLabel("CELULAR:");
		lblCelular.setForeground(new Color(51, 102, 255));
		lblCelular.setFont(new Font(font, Font.PLAIN, 18));
		lblCelular.setBounds(10, 125, 208, 28);
		getContentPane().add(lblCelular);

		JLabel cel = new JLabel("0999308011");
		cel.setForeground(new Color(51, 102, 255));
		cel.setFont(new Font(font, Font.PLAIN, 16));
		cel.setBounds(228, 125, 474, 28);
		getContentPane().add(cel);

		JLabel lblCorreo = new JLabel("CORREO:");
		lblCorreo.setForeground(new Color(51, 102, 255));
		lblCorreo.setFont(new Font(font, Font.PLAIN, 18));
		lblCorreo.setBounds(10, 153, 208, 28);
		getContentPane().add(lblCorreo);

		JLabel lblPacaconzagmailcom = new JLabel("pconza@redepronik.com.ec");
		lblPacaconzagmailcom.setForeground(new Color(51, 102, 255));
		lblPacaconzagmailcom.setFont(new Font(font, Font.PLAIN, 16));
		lblPacaconzagmailcom.setBounds(228, 153, 474, 28);
		getContentPane().add(lblPacaconzagmailcom);

		JLabel lblCodigoFuente = new JLabel("CODIGO FUENTE:");
		lblCodigoFuente.setForeground(new Color(51, 102, 255));
		lblCodigoFuente.setFont(new Font(font, Font.PLAIN, 18));
		lblCodigoFuente.setBounds(10, 211, 208, 28);
		getContentPane().add(lblCodigoFuente);

		JTextField caja1 = new JTextField("https://github.com/pacaconza94/GestionBiblioteca");
		caja1.setEditable(false);
		caja1.setForeground(new Color(51, 102, 255));
		caja1.setFont(new Font(font, Font.PLAIN, 16));
		caja1.setBounds(228, 211, 474, 28);
		getContentPane().add(caja1);

		JLabel lblUbicacionDeLa = new JLabel("UBICACION DE LA BD:");
		lblUbicacionDeLa.setForeground(new Color(51, 102, 255));
		lblUbicacionDeLa.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblUbicacionDeLa.setBounds(10, 182, 208, 28);
		getContentPane().add(lblUbicacionDeLa);

		JLabel lblBD = new JLabel(UtilsArchivos.getBD());
		lblBD.setForeground(new Color(51, 102, 255));
		lblBD.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBD.setBounds(228, 182, 474, 28);
		getContentPane().add(lblBD);

		setVisible(true);
	}

}
