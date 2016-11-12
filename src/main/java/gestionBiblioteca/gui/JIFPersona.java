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

import gestionBiblioteca.entity.Docente;
import gestionBiblioteca.entity.Estudiante;
import gestionBiblioteca.entity.Persona;
import gestionBiblioteca.service.PersonaService;
import gestionBiblioteca.service.PersonaServiceImpl;

public class JIFPersona extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private PersonaService personaService = new PersonaServiceImpl();

	private JTextField jtfCedula;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JComboBox<String> jcbSexo;
	private JTextField jtfEdad;
	private JTextField jtfCiudad;
	private JTextField jtfTelefono;
	private JComboBox<String> jcbTipo;
	private JLabel lblCarreraMateria;
	private JTextField jtfCarreraMateria;
	private JLabel lblSemestre;
	private JTextField jtfSemestre;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;

	private JTable table;
	private DefaultTableModel model = null;

	private List<Persona> listPersona = new ArrayList<Persona>();
	private Persona persona = new Persona();
	private boolean nuevoModificar;

	public JIFPersona() {
		super("Gestionar Personas", true, true, true, true);
		initComponents();
		limpiar();
		activarDesactivar(false);
		listPersona = personaService.obtenerTodos();
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

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 39, 77, 14);
		panel_1.add(lblCedula);

		jtfCedula = new JTextField();
		jtfCedula.setBounds(97, 34, 190, 25);
		panel_1.add(jtfCedula);
		jtfCedula.setColumns(10);

		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(10, 69, 77, 14);
		panel_1.add(lblNombre);

		jtfNombre = new JTextField();
		jtfNombre.setBounds(97, 64, 190, 25);
		panel_1.add(jtfNombre);
		jtfNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellidos");
		lblApellido.setBounds(10, 100, 77, 14);
		panel_1.add(lblApellido);

		jtfApellido = new JTextField();
		jtfApellido.setBounds(97, 95, 190, 25);
		panel_1.add(jtfApellido);
		jtfApellido.setColumns(10);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 162, 77, 14);
		panel_1.add(lblEdad);

		jtfEdad = new JTextField();
		jtfEdad.setBounds(97, 157, 190, 25);
		panel_1.add(jtfEdad);
		jtfEdad.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 130, 77, 14);
		panel_1.add(lblSexo);

		jcbSexo = new JComboBox<String>();
		jcbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Selccione una opcion", "M", "F" }));
		jcbSexo.setBounds(97, 125, 190, 25);
		panel_1.add(jcbSexo);

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

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(10, 192, 77, 14);
		panel_1.add(lblCiudad);

		jtfCiudad = new JTextField();
		jtfCiudad.setColumns(10);
		jtfCiudad.setBounds(97, 187, 190, 25);
		panel_1.add(jtfCiudad);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 223, 77, 14);
		panel_1.add(lblTelefono);

		jtfTelefono = new JTextField();
		jtfTelefono.setColumns(10);
		jtfTelefono.setBounds(97, 218, 190, 25);
		panel_1.add(jtfTelefono);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 253, 77, 14);
		panel_1.add(lblTipo);

		jcbTipo = new JComboBox<String>();
		jcbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcbTipo.getSelectedIndex() == 0) {
					jtfCarreraMateria.setText("");
					jtfSemestre.setText("");
					lblCarreraMateria.setVisible(false);
					jtfCarreraMateria.setVisible(false);
					lblSemestre.setVisible(false);
					jtfSemestre.setVisible(false);
				} else if (jcbTipo.getSelectedIndex() == 1) {
					jtfCarreraMateria.setText("");
					jtfSemestre.setText("");
					lblCarreraMateria.setText("Carrera");
					lblCarreraMateria.setVisible(true);
					jtfCarreraMateria.setVisible(true);
					lblSemestre.setVisible(true);
					jtfSemestre.setVisible(true);
				} else {
					jtfCarreraMateria.setText("");
					jtfSemestre.setText("");
					lblCarreraMateria.setText("Materia");
					lblCarreraMateria.setVisible(true);
					jtfCarreraMateria.setVisible(true);
					lblSemestre.setVisible(false);
					jtfSemestre.setVisible(false);
				}
			}
		});
		jcbTipo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Selccione una opcion", "ESTUDIANTE", "DOCENTE" }));
		jcbTipo.setBounds(97, 248, 190, 25);
		panel_1.add(jcbTipo);

		lblCarreraMateria = new JLabel("Carrera");
		lblCarreraMateria.setBounds(10, 283, 77, 14);
		panel_1.add(lblCarreraMateria);

		jtfCarreraMateria = new JTextField();
		jtfCarreraMateria.setColumns(10);
		jtfCarreraMateria.setBounds(97, 278, 190, 25);
		panel_1.add(jtfCarreraMateria);

		lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 314, 77, 14);
		panel_1.add(lblSemestre);

		jtfSemestre = new JTextField();
		jtfSemestre.setColumns(10);
		jtfSemestre.setBounds(97, 309, 190, 25);
		panel_1.add(jtfSemestre);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(307, 11, 509, 415);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblListado = new JLabel("LISTADO");
		lblListado.setBounds(219, 0, 70, 28);
		panel_2.add(lblListado);
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 15));

		model = new DefaultTableModel(null, new String[] { "Cedula", "Apellido", "Nombres", "Tipo" });
		table = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				cargarPersona(fila);
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
		btnNuevo.setBounds(60, 381, 89, 23);
		panel_2.add(btnNuevo);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (persona == null || persona.getCedula() == null || persona.getCedula().compareToIgnoreCase("") == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(true);
					jtfCedula.setEnabled(false);
					nuevoModificar = false;
				}
			}
		});
		btnModificar.setBounds(209, 381, 89, 23);
		panel_2.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (persona == null || persona.getCedula() == null || persona.getCedula().compareToIgnoreCase("") == 0)
					JOptionPane.showMessageDialog(null, "Escoja una fila primero", "Mensaje del Sistema",
							JOptionPane.ERROR_MESSAGE);
				else {
					activarDesactivar(false);
					String mensaje = personaService.eliminar(persona);
					String control = String.valueOf(mensaje.charAt(0));
					mensaje = mensaje.substring(1);
					if (control.compareToIgnoreCase("F") == 0)
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema",
								JOptionPane.INFORMATION_MESSAGE);
						listPersona.remove(persona);
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
		if (listPersona != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--)
				model.removeRow(i);
			for (Persona persona : listPersona)
				model.addRow(new Object[] { persona.getCedula(), persona.getApellido(), persona.getNombre(),
						persona.getTipo() == 1 ? "ESTUDIANTE" : "DOCENTE" });
		}
	}

	public void cargarPersona(int fila) {
		persona = listPersona.get(fila);
		jtfCedula.setText(persona.getCedula());
		jtfNombre.setText(persona.getNombre());
		jtfApellido.setText(persona.getApellido());
		jcbSexo.setSelectedItem(String.valueOf(persona.getSexo()));
		jtfEdad.setText(String.valueOf(persona.getEdad()));
		jtfCiudad.setText(persona.getCiudad());
		jtfTelefono.setText(persona.getTelefono());
		jcbTipo.setSelectedIndex(persona.getTipo());

		if (persona.getTipo() == 1) {
			Estudiante estudiante = (Estudiante) persona;
			jtfCarreraMateria.setText(estudiante.getCarrera());
			jtfSemestre.setText(String.valueOf(estudiante.getSemestre()));
		} else {
			Docente docente = (Docente) persona;
			jtfCarreraMateria.setText(docente.getMateria());
		}
	}

	private void guardar() {
		String cedula = jtfCedula.getText();
		String nombre = jtfNombre.getText();
		String apellido = jtfApellido.getText();
		char sexo = ((String) jcbSexo.getSelectedItem()).charAt(0);
		int edad;
		try {
			edad = Integer.parseInt(jtfEdad.getText());
		} catch (Exception e) {
			edad = 0;
		}
		String ciudad = jtfCiudad.getText();
		String telefono = jtfTelefono.getText();
		int tipo = jcbTipo.getSelectedIndex();
		String carreraMateria = jtfCarreraMateria.getText();
		int semestre;
		try {
			semestre = Integer.parseInt(jtfSemestre.getText());
		} catch (Exception e) {
			semestre = 0;
		}

		Persona persona = null;
		if (tipo == 1)
			persona = new Estudiante(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo, carreraMateria,
					semestre);
		else
			persona = new Docente(cedula, nombre, apellido, sexo, edad, ciudad, telefono, tipo, carreraMateria);

		String mensaje;
		if (nuevoModificar)
			mensaje = personaService.insertar(persona);
		else
			mensaje = personaService.modificar(persona);
		String control = String.valueOf(mensaje.charAt(0));
		mensaje = mensaje.substring(1);
		if (control.compareToIgnoreCase("F") == 0)
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		else {
			JOptionPane.showMessageDialog(null, mensaje, "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
			if (nuevoModificar)
				listPersona.add(persona);
			else
				listPersona.set(listPersona.indexOf(this.persona), persona);
			limpiar();
			activarDesactivar(false);
			llenarTabla();
		}
	}

	private void limpiar() {
		jtfCedula.setText("");
		jtfNombre.setText("");
		jtfApellido.setText("");
		jcbSexo.setSelectedIndex(0);
		jtfEdad.setText("");
		jtfCiudad.setText("");
		jtfTelefono.setText("");
		jcbTipo.setSelectedIndex(0);
		jtfCarreraMateria.setText("");
		jtfSemestre.setText("");

		lblCarreraMateria.setVisible(false);
		jtfCarreraMateria.setVisible(false);
		lblSemestre.setVisible(false);
		jtfSemestre.setVisible(false);
	}

	private void activarDesactivar(boolean activoDesactivo) {
		jtfCedula.setEnabled(activoDesactivo);
		jtfNombre.setEnabled(activoDesactivo);
		jtfApellido.setEnabled(activoDesactivo);
		jcbSexo.setEnabled(activoDesactivo);
		jtfEdad.setEnabled(activoDesactivo);
		jtfCiudad.setEnabled(activoDesactivo);
		jtfTelefono.setEnabled(activoDesactivo);
		jcbTipo.setEnabled(activoDesactivo);
		jtfCarreraMateria.setEnabled(activoDesactivo);
		jtfSemestre.setEnabled(activoDesactivo);

		btnGuardar.setEnabled(activoDesactivo);
		btnCancelar.setEnabled(activoDesactivo);

		btnNuevo.setEnabled(!activoDesactivo);
		btnModificar.setEnabled(!activoDesactivo);
		btnEliminar.setEnabled(!activoDesactivo);
	}

}
