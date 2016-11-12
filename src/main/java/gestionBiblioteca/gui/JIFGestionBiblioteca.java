package gestionBiblioteca.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import gestionBiblioteca.entity.GestionBiblioteca;
import gestionBiblioteca.entity.MaterialBibliografico;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.service.GestionBibliotecaService;
import gestionBiblioteca.service.GestionBibliotecaServiceImpl;
import gestionBiblioteca.service.MaterialBibliograficoService;
import gestionBiblioteca.service.MaterialBibliograficoServiceImpl;
import gestionBiblioteca.utils.UtilsDate;

public class JIFGestionBiblioteca extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private GestionBibliotecaService gestionBibliotecaService = new GestionBibliotecaServiceImpl();
	private MaterialBibliograficoService materialBibliograficoService = new MaterialBibliograficoServiceImpl();

	private JTextField jtfFechaPrestamo;
	private JTextField jtfFechaDevolucion;
	private JTextField jtfPersona;
	private JLabel lblCodigo;
	private JTextField jtfCodigo;
	private JButton btnAnadir;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnDevolucion;
	private JButton btnEliminar;
	private JTable tableMaterialBibliografico;
	private DefaultTableModel modelMaterialBibliografico = null;
	private List<MaterialBibliografico> listaMaterialBibliografico;

	private JTable table;
	private DefaultTableModel model = null;

	private List<GestionBiblioteca> listGestionBiblioteca = new ArrayList<GestionBiblioteca>();
	private GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();
	private boolean nuevoModificar;

	public JIFGestionBiblioteca() {
		super("Gestionar Biblioteca", true, true, true, true);
		initComponents();
		limpiar();
		activarDesactivar(false);
		listGestionBiblioteca = gestionBibliotecaService.obtenerTodos();
		llenarTabla();
	}

	private void initComponents() {
		setBounds(40, 25, 837, 476);
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

		JLabel lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setBounds(10, 39, 100, 14);
		panel_1.add(lblFechaPrestamo);

		jtfFechaPrestamo = new JTextField();
		jtfFechaPrestamo.setBounds(120, 34, 167, 25);
		panel_1.add(jtfFechaPrestamo);
		jtfFechaPrestamo.setColumns(10);

		JLabel lblFechaDevolucion = new JLabel("Fecha Devolucion");
		lblFechaDevolucion.setBounds(10, 69, 100, 14);
		panel_1.add(lblFechaDevolucion);

		jtfFechaDevolucion = new JTextField();
		jtfFechaDevolucion.setBounds(120, 64, 167, 25);
		panel_1.add(jtfFechaDevolucion);
		jtfFechaDevolucion.setColumns(10);

		JLabel lblPersona = new JLabel("Persona");
		lblPersona.setBounds(10, 100, 100, 14);
		panel_1.add(lblPersona);

		jtfPersona = new JTextField();
		jtfPersona.setBounds(120, 95, 167, 25);
		panel_1.add(jtfPersona);
		jtfPersona.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				activarDesactivar(false);
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

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Material Bibliografico",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(4, 124, 289, 253);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 31, 33, 14);
		panel_4.add(lblCodigo);

		jtfCodigo = new JTextField();
		jtfCodigo.setBounds(66, 26, 135, 25);
		panel_4.add(jtfCodigo);
		jtfCodigo.setColumns(10);

		btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = jtfCodigo.getText();
				jtfCodigo.setText("");
				for (int i = 0; i < listaMaterialBibliografico.size(); i++)
					if (listaMaterialBibliografico.get(i).getCodigo().compareToIgnoreCase(codigo) == 0) {
						listaMaterialBibliografico.remove(i);
						llenarTablaMaterialBibliografico();
						return;
					}
				MaterialBibliografico mb = materialBibliograficoService.obtenerPorCodigo(codigo);
				if (mb == null)
					JOptionPane.showMessageDialog(null, "El material bibliografico no existe", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					listaMaterialBibliografico.add(mb);
					llenarTablaMaterialBibliografico();
				}
			}
		});
		btnAnadir.setToolTipText("Si añade dos veces el mismo codigo se procedera a eliminar ambos");
		btnAnadir.setBounds(211, 27, 68, 23);
		panel_4.add(btnAnadir);

		modelMaterialBibliografico = new DefaultTableModel(null, new String[] { "Codigo", "Titulo" });
		tableMaterialBibliografico = new JTable(modelMaterialBibliografico) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		tableMaterialBibliografico.setBounds(10, 67, 169, 175);

		JScrollPane jspMaterialBibliografico = new JScrollPane(tableMaterialBibliografico);
		jspMaterialBibliografico.setBounds(10, 67, 269, 175);
		panel_4.add(jspMaterialBibliografico);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(307, 11, 509, 415);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblListado = new JLabel("LISTADO");
		lblListado.setBounds(219, 0, 70, 28);
		panel_2.add(lblListado);
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 15));

		model = new DefaultTableModel(null, new String[] { "Id", "Fecha Prestamo", "Fecha Devolucion", "Persona" });
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
				activarDesactivar(true);
				nuevoModificar = true;
			}
		});
		btnNuevo.setBounds(30, 381, 89, 23);
		panel_2.add(btnNuevo);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gestionBiblioteca == null || gestionBiblioteca.getId() == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(true);
					nuevoModificar = false;
				}
			}
		});
		btnModificar.setEnabled(true);
		btnModificar.setBounds(149, 381, 89, 23);
		panel_2.add(btnModificar);

		btnDevolucion = new JButton("Devolucion");
		btnDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gestionBiblioteca == null || gestionBiblioteca.getId() == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(false);

					jtfFechaDevolucion.setEnabled(true);
					btnGuardar.setEnabled(true);
					btnCancelar.setEnabled(true);

					btnNuevo.setEnabled(false);
					btnModificar.setEnabled(false);
					btnDevolucion.setEnabled(false);
					btnEliminar.setEnabled(false);

					nuevoModificar = false;
				}
			}
		});
		btnDevolucion.setBounds(268, 381, 89, 23);
		panel_2.add(btnDevolucion);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gestionBiblioteca == null || gestionBiblioteca.getId() == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(false);
					String mensaje = gestionBibliotecaService.eliminar(gestionBiblioteca);
					String control = String.valueOf(mensaje.charAt(0));
					mensaje = mensaje.substring(1);
					if (control.compareToIgnoreCase("F") == 0)
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema",
								JOptionPane.INFORMATION_MESSAGE);
						listGestionBiblioteca.remove(gestionBiblioteca);
						limpiar();
						llenarTabla();
					}
				}
			}
		});
		btnEliminar.setBounds(387, 381, 89, 23);
		panel_2.add(btnEliminar);

		setVisible(true);
	}

	private void llenarTabla() {
		if (listGestionBiblioteca != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--)
				model.removeRow(i);
			for (GestionBiblioteca gestionBiblioteca : listGestionBiblioteca)
				model.addRow(new Object[] { gestionBiblioteca.getId(),
						UtilsDate.dateToString(gestionBiblioteca.getFechaPrestamo()),
						UtilsDate.dateToString(gestionBiblioteca.getFechaDevolucion()),
						gestionBiblioteca.getPersona().getApellido() + " "
								+ gestionBiblioteca.getPersona().getNombre() });
		}
	}

	private void llenarTablaMaterialBibliografico() {
		if (listaMaterialBibliografico != null) {
			for (int i = modelMaterialBibliografico.getRowCount() - 1; i >= 0; i--)
				modelMaterialBibliografico.removeRow(i);
			for (MaterialBibliografico mb : listaMaterialBibliografico)
				modelMaterialBibliografico.addRow(new Object[] { mb.getCodigo(), mb.getTitulo() });
		}
	}

	public void cargarMaterialBibliografico(int fila) {
		gestionBiblioteca = listGestionBiblioteca.get(fila);
		jtfFechaPrestamo.setText(UtilsDate.dateToString(gestionBiblioteca.getFechaPrestamo()));
		jtfFechaDevolucion.setText(UtilsDate.dateToString(gestionBiblioteca.getFechaDevolucion()));
		jtfPersona.setText(gestionBiblioteca.getPersona().getCedula());
		listaMaterialBibliografico = gestionBiblioteca.getListaMaterialBibliografico();
		llenarTablaMaterialBibliografico();
	}

	private void guardar() {
		String fechaPrestamo = jtfFechaPrestamo.getText();
		String fechaDevolucion = jtfFechaDevolucion.getText();
		String persona = jtfPersona.getText();

		int id = nuevoModificar ? listGestionBiblioteca.size() + 1 : gestionBiblioteca.getId();

		GestionBiblioteca gestionBiblioteca = new GestionBiblioteca(id, UtilsDate.stringToDate(fechaPrestamo),
				UtilsDate.stringToDate(fechaDevolucion), new Persona(persona), listaMaterialBibliografico);

		String mensaje;
		if (nuevoModificar)
			mensaje = gestionBibliotecaService.insertar(gestionBiblioteca);
		else
			mensaje = gestionBibliotecaService.modificar(gestionBiblioteca);
		String control = String.valueOf(mensaje.charAt(0));
		mensaje = mensaje.substring(1);
		if (control.compareToIgnoreCase("F") == 0)
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		else {
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
			if (nuevoModificar)
				listGestionBiblioteca.add(gestionBiblioteca);
			else
				listGestionBiblioteca.set(listGestionBiblioteca.indexOf(this.gestionBiblioteca), gestionBiblioteca);
			limpiar();
			activarDesactivar(false);
			llenarTabla();
		}
	}

	private void limpiar() {
		jtfFechaPrestamo.setText("");
		jtfFechaDevolucion.setText("");
		jtfPersona.setText("");
		jtfCodigo.setText("");
		listaMaterialBibliografico = new ArrayList<MaterialBibliografico>();
		llenarTablaMaterialBibliografico();
	}

	private void activarDesactivar(boolean activoDesactivo) {
		jtfFechaPrestamo.setEnabled(activoDesactivo);
		jtfFechaDevolucion.setEnabled(false);
		jtfPersona.setEnabled(activoDesactivo);
		jtfCodigo.setEnabled(activoDesactivo);

		btnAnadir.setEnabled(activoDesactivo);

		btnGuardar.setEnabled(activoDesactivo);
		btnCancelar.setEnabled(activoDesactivo);

		btnNuevo.setEnabled(!activoDesactivo);
		btnModificar.setEnabled(!activoDesactivo);
		btnDevolucion.setEnabled(!activoDesactivo);
		btnEliminar.setEnabled(!activoDesactivo);
	}
}