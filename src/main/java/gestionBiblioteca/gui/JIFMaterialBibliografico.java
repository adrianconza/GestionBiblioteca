package gestionBiblioteca.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gestionBiblioteca.entity.Libro;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Revista;
import gestionBiblioteca.entity.Tesis;
import gestionBiblioteca.service.MaterialBibliograficoService;
import gestionBiblioteca.service.MaterialBibliograficoServiceImpl;
import gestionBiblioteca.utils.UtilsDate;

public class JIFMaterialBibliografico extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private MaterialBibliograficoService materialBibliograficoService = new MaterialBibliograficoServiceImpl();

	private JTextField jtfCodigo;
	private JTextField jtfTitulo;
	private JTextField jtfAnioPublicacion;
	private JTextField jtfUnidades;
	private JComboBox<String> jcbTipo;
	private JLabel lblCampo1;
	private JTextField jtfCampo1;
	private JLabel lblCampo2;
	private JTextField jtfCampo2;
	private JLabel lblEdicion;
	private JTextField jtfEdicion;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;

	private JTable table;
	private DefaultTableModel model = null;

	private List<MaterialBibliografico> listMaterialBibliografico = new ArrayList<MaterialBibliografico>();
	private MaterialBibliografico materialBibliografico = new MaterialBibliografico();
	private boolean nuevoModificar;

	public JIFMaterialBibliografico(int posicionX, int posicionY) {
		super("Gestionar Material Bibliografico", true, true, true, true);
		initComponents(posicionX, posicionY);
		limpiar();
		activarDesactivar(false, false);
		listMaterialBibliografico = materialBibliograficoService.obtenerTodos();
		llenarTabla();
	}

	private void initComponents(int posicionX, int posicionY) {
		setBounds(posicionX, posicionY, 837, 476);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 816, 426);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 11, 297, 415);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDatos = new JLabel("DATOS");
		lblDatos.setBounds(120, 0, 56, 28);
		panel_1.add(lblDatos);
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 39, 77, 14);
		panel_1.add(lblCodigo);

		jtfCodigo = new JTextField();
		jtfCodigo.setBounds(97, 34, 190, 25);
		panel_1.add(jtfCodigo);
		jtfCodigo.setColumns(10);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 69, 77, 14);
		panel_1.add(lblTitulo);

		jtfTitulo = new JTextField();
		jtfTitulo.setBounds(97, 64, 190, 25);
		panel_1.add(jtfTitulo);
		jtfTitulo.setColumns(10);

		JLabel lblAnioPublicacion = new JLabel("Año Publicacion");
		lblAnioPublicacion.setBounds(10, 100, 77, 14);
		panel_1.add(lblAnioPublicacion);

		jtfAnioPublicacion = new JTextField();
		jtfAnioPublicacion.setBounds(97, 95, 190, 25);
		panel_1.add(jtfAnioPublicacion);
		jtfAnioPublicacion.setColumns(10);

		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(10, 130, 77, 14);
		panel_1.add(lblUnidades);

		jtfUnidades = new JTextField();
		jtfUnidades.setBounds(97, 125, 190, 25);
		panel_1.add(jtfUnidades);
		jtfUnidades.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				activarDesactivar(false, false);
			}
		});
		btnCancelar.setBounds(167, 381, 89, 23);
		panel_1.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(39, 381, 89, 23);
		panel_1.add(btnGuardar);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 160, 77, 14);
		panel_1.add(lblTipo);

		jcbTipo = new JComboBox<String>();
		jcbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcbTipo.getSelectedIndex() == 0) {
					jtfCampo1.setText("");
					jtfCampo2.setText("");
					jtfEdicion.setText("");
					lblCampo1.setVisible(false);
					jtfCampo1.setVisible(false);
					lblCampo2.setVisible(false);
					jtfCampo2.setVisible(false);
					lblEdicion.setVisible(false);
					jtfEdicion.setVisible(false);
				} else if (jcbTipo.getSelectedIndex() == 1) {
					jtfCampo1.setText("");
					jtfCampo2.setText("");
					jtfEdicion.setText("");
					lblCampo1.setText("Autor");
					lblCampo2.setText("Tutor");
					lblCampo1.setVisible(true);
					jtfCampo1.setVisible(true);
					lblCampo2.setVisible(true);
					jtfCampo2.setVisible(true);
					lblEdicion.setVisible(false);
					jtfEdicion.setVisible(false);
				} else if (jcbTipo.getSelectedIndex() == 2) {
					jtfCampo1.setText("");
					jtfCampo2.setText("");
					jtfEdicion.setText("");
					lblCampo1.setText("Fecha Publicacion");
					lblCampo2.setText("Tipo Revista");
					lblCampo1.setVisible(true);
					jtfCampo1.setVisible(true);
					lblCampo2.setVisible(true);
					jtfCampo2.setVisible(true);
					lblEdicion.setVisible(false);
					jtfEdicion.setVisible(false);
				} else {
					jtfCampo1.setText("");
					jtfCampo2.setText("");
					jtfEdicion.setText("");
					lblCampo1.setText("Editorial");
					lblCampo2.setText("Autor");
					lblCampo1.setVisible(true);
					jtfCampo1.setVisible(true);
					lblCampo2.setVisible(true);
					jtfCampo2.setVisible(true);
					lblEdicion.setVisible(true);
					jtfEdicion.setVisible(true);
				}
			}
		});
		jcbTipo.setModel(
				new DefaultComboBoxModel(new String[] { "Selccione una opcion", "TESIS", "REVISTA", "LIBRO" }));
		jcbTipo.setBounds(97, 155, 190, 25);
		panel_1.add(jcbTipo);

		lblCampo1 = new JLabel("Campo1");
		lblCampo1.setBounds(10, 190, 77, 14);
		panel_1.add(lblCampo1);

		jtfCampo1 = new JTextField();
		jtfCampo1.setColumns(10);
		jtfCampo1.setBounds(97, 185, 190, 25);
		panel_1.add(jtfCampo1);

		lblCampo2 = new JLabel("Campo2");
		lblCampo2.setBounds(10, 221, 77, 14);
		panel_1.add(lblCampo2);

		jtfCampo2 = new JTextField();
		jtfCampo2.setColumns(10);
		jtfCampo2.setBounds(97, 216, 190, 25);
		panel_1.add(jtfCampo2);

		lblEdicion = new JLabel("Edicion");
		lblEdicion.setBounds(10, 252, 46, 14);
		panel_1.add(lblEdicion);

		jtfEdicion = new JTextField();
		jtfEdicion.setColumns(10);
		jtfEdicion.setBounds(97, 247, 190, 25);
		panel_1.add(jtfEdicion);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(307, 11, 509, 415);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblListado = new JLabel("LISTADO");
		lblListado.setBounds(219, 0, 70, 28);
		panel_2.add(lblListado);
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 15));

		model = new DefaultTableModel(null, new String[] { "Codigo", "Titulo", "Unidades", "Tipo" });
		table = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				cargarMaterialBibliografico(fila);
			}
		});
		table.setBounds(0, 33, 409, 332);

		JScrollPane jspTabla = new JScrollPane(table);
		jspTabla.setBounds(0, 33, 509, 332);
		panel_2.add(jspTabla);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				activarDesactivar(true, true);
				nuevoModificar = true;
			}
		});
		btnNuevo.setBounds(60, 381, 89, 23);
		panel_2.add(btnNuevo);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (materialBibliografico == null || materialBibliografico.getCodigo() == null
						|| materialBibliografico.getCodigo().compareToIgnoreCase("") == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(true, false);
					jtfCodigo.setEnabled(false);
					nuevoModificar = false;
				}
			}
		});
		btnModificar.setBounds(209, 381, 89, 23);
		panel_2.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (materialBibliografico == null || materialBibliografico.getCodigo() == null
						|| materialBibliografico.getCodigo().compareToIgnoreCase("") == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(false, false);
					String mensaje = materialBibliograficoService.eliminar(materialBibliografico);
					String control = String.valueOf(mensaje.charAt(0));
					mensaje = mensaje.substring(1);
					if (control.compareToIgnoreCase("F") == 0)
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema",
								JOptionPane.INFORMATION_MESSAGE);
						listMaterialBibliografico.remove(materialBibliografico);
						limpiar();
						llenarTabla();
					}
				}
			}
		});
		btnEliminar.setBounds(358, 381, 89, 23);
		panel_2.add(btnEliminar);

		setVisible(true);
	}

	private void llenarTabla() {
		if (listMaterialBibliografico != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--)
				model.removeRow(i);
			for (MaterialBibliografico materialBibliografico : listMaterialBibliografico)
				model.addRow(new Object[] { materialBibliografico.getCodigo(), materialBibliografico.getTitulo(),
						materialBibliografico.getUnidades(), materialBibliografico.getTipo() == 1 ? "TESIS"
								: materialBibliografico.getTipo() == 2 ? "REVISTA" : "LIBRO" });
		}
	}

	public void cargarMaterialBibliografico(int fila) {
		materialBibliografico = listMaterialBibliografico.get(fila);
		jtfCodigo.setText(materialBibliografico.getCodigo());
		jtfTitulo.setText(materialBibliografico.getTitulo());
		jtfAnioPublicacion.setText(String.valueOf(materialBibliografico.getAnioPublicacion()));
		jtfUnidades.setText(String.valueOf(materialBibliografico.getUnidades()));
		jcbTipo.setSelectedIndex(materialBibliografico.getTipo());

		if (materialBibliografico.getTipo() == 1) {
			Tesis tesis = (Tesis) materialBibliografico;
			jtfCampo1.setText(tesis.getAutor());
			jtfCampo2.setText(tesis.getTutor());
		} else if (materialBibliografico.getTipo() == 2) {
			Revista revista = (Revista) materialBibliografico;
			jtfCampo1.setText(UtilsDate.dateToString(revista.getFechaPublicacion()));
			jtfCampo2.setText(revista.getTipoRevista());
		} else {
			Libro libro = (Libro) materialBibliografico;
			jtfCampo1.setText(libro.getEditorial());
			jtfCampo2.setText(libro.getAutor());
			jtfEdicion.setText(String.valueOf(libro.getEdicion()));
		}
	}

	private void guardar() {
		String codigo = jtfCodigo.getText();
		String titulo = jtfTitulo.getText();
		int anioPublicacion;
		try {
			anioPublicacion = Integer.parseInt(jtfAnioPublicacion.getText());
		} catch (Exception e) {
			anioPublicacion = 0;
		}
		int unidades;
		try {
			unidades = Integer.parseInt(jtfUnidades.getText());
		} catch (Exception e) {
			unidades = 0;
		}
		int tipo = jcbTipo.getSelectedIndex();
		String campo1 = jtfCampo1.getText();

		String campo2 = jtfCampo2.getText();

		int edicion;
		try {
			edicion = Integer.parseInt(jtfEdicion.getText());
		} catch (Exception e) {
			edicion = 0;
		}

		MaterialBibliografico materialBibliografico = null;
		if (tipo == 1)
			materialBibliografico = new Tesis(codigo, titulo, anioPublicacion, unidades, tipo, campo1, campo2);
		else if (tipo == 2)
			materialBibliografico = new Revista(codigo, titulo, anioPublicacion, unidades, tipo,
					UtilsDate.stringToDate(campo1), campo2);
		else
			materialBibliografico = new Libro(codigo, titulo, anioPublicacion, unidades, tipo, campo1, campo2, edicion);

		String mensaje;
		if (nuevoModificar)
			mensaje = materialBibliograficoService.insertar(materialBibliografico);
		else
			mensaje = materialBibliograficoService.modificar(materialBibliografico);
		String control = String.valueOf(mensaje.charAt(0));
		mensaje = mensaje.substring(1);
		if (control.compareToIgnoreCase("F") == 0)
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		else {
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
			if (nuevoModificar)
				listMaterialBibliografico.add(materialBibliografico);
			else
				listMaterialBibliografico.set(listMaterialBibliografico.indexOf(this.materialBibliografico),
						materialBibliografico);
			limpiar();
			activarDesactivar(false, false);
			llenarTabla();
		}
	}

	private void limpiar() {
		jtfCodigo.setText("");
		jtfTitulo.setText("");
		jtfAnioPublicacion.setText("");
		jtfUnidades.setText("");
		jcbTipo.setSelectedIndex(0);
		jtfCampo1.setText("");
		jtfCampo2.setText("");
		jtfEdicion.setText("");

		lblCampo1.setVisible(false);
		jtfCampo1.setVisible(false);
		lblCampo2.setVisible(false);
		jtfCampo2.setVisible(false);
		lblEdicion.setVisible(false);
		jtfEdicion.setVisible(false);

	}

	private void activarDesactivar(boolean activoDesactivo, boolean insertar) {
		jtfCodigo.setEnabled(activoDesactivo);
		jtfTitulo.setEnabled(activoDesactivo);
		jtfAnioPublicacion.setEnabled(activoDesactivo);
		jtfUnidades.setEnabled(activoDesactivo);
		jcbTipo.setEnabled(insertar);
		jtfCampo1.setEnabled(activoDesactivo);
		jtfCampo2.setEnabled(activoDesactivo);
		jtfEdicion.setEnabled(activoDesactivo);

		btnGuardar.setEnabled(activoDesactivo);
		btnCancelar.setEnabled(activoDesactivo);

		btnNuevo.setEnabled(!activoDesactivo);
		btnModificar.setEnabled(!activoDesactivo);
		btnEliminar.setEnabled(!activoDesactivo);
	}
}
