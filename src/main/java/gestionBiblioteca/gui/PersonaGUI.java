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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PersonaGUI extends JFrame {
	private List<Persona1> listPersona = new ArrayList<Persona1>();

	private JTextField jtfIdentificacion;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfFechaNacimiento;
	private JComboBox<String> jcbSexo;
	private JComboBox<String> jbcTipoSangre;
	private JTable table;
	private DefaultTableModel model = null;

	public static void main(String[] args) {
		PersonaGUI frame = new PersonaGUI();
		frame.setVisible(true);
	}

	public PersonaGUI() {
		setTitle("Persona");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 306);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 803, 277);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDatosDePersona = new JLabel("DATOS DE PERSONA");
		lblDatosDePersona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatosDePersona.setBounds(75, 12, 150, 28);
		panel.add(lblDatosDePersona);

		JLabel lblListadoDePersonas = new JLabel("LISTADO DE PERSONAS");
		lblListadoDePersonas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListadoDePersonas.setBounds(465, 12, 197, 28);
		panel.add(lblListadoDePersonas);

		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setBounds(10, 51, 107, 14);
		panel.add(lblIdentificacion);

		jtfIdentificacion = new JTextField();
		jtfIdentificacion.setBounds(117, 45, 150, 20);
		panel.add(jtfIdentificacion);
		jtfIdentificacion.setColumns(10);

		jtfNombre = new JTextField();
		jtfNombre.setColumns(10);
		jtfNombre.setBounds(117, 73, 150, 20);
		panel.add(jtfNombre);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 79, 107, 14);
		panel.add(lblNombres);

		jtfApellido = new JTextField();
		jtfApellido.setColumns(10);
		jtfApellido.setBounds(117, 104, 150, 20);
		panel.add(jtfApellido);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 110, 107, 14);
		panel.add(lblApellidos);

		jtfFechaNacimiento = new JTextField();
		jtfFechaNacimiento.setColumns(10);
		jtfFechaNacimiento.setBounds(117, 135, 150, 20);
		panel.add(jtfFechaNacimiento);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(10, 141, 107, 14);
		panel.add(lblFechaNacimiento);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 172, 107, 14);
		panel.add(lblSexo);

		jcbSexo = new JComboBox<String>();
		jcbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Selccione una opcion", "M", "F" }));
		jcbSexo.setBounds(117, 166, 150, 20);
		panel.add(jcbSexo);

		JLabel lblTipoDeSangre = new JLabel("Tipo de Sangre");
		lblTipoDeSangre.setBounds(10, 200, 107, 14);
		panel.add(lblTipoDeSangre);

		jbcTipoSangre = new JComboBox<String>();
		jbcTipoSangre.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selccione una opcion", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+" }));
		jbcTipoSangre.setBounds(117, 194, 150, 20);
		panel.add(jbcTipoSangre);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCancelar();
			}
		});
		btnLimpiar.setBounds(10, 244, 89, 23);
		panel.add(btnLimpiar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCancelar();
			}
		});
		btnCancelar.setBounds(109, 244, 89, 23);
		panel.add(btnCancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(208, 244, 89, 23);
		panel.add(btnGuardar);

		model = new DefaultTableModel(null,
				new String[] { "Identificacion", "Nombre", "Apellido", "Fech. Nac.", "Sexo", "Tipo/Sangre" });
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
		table.setBounds(321, 45, 360, 200);

		JScrollPane jspTabla = new JScrollPane(table);
		jspTabla.setBounds(321, 45, 472, 200);
		panel.add(jspTabla);

	}

	private void limpiarCancelar() {
		jtfIdentificacion.setText("");
		jtfNombre.setText("");
		jtfApellido.setText("");
		jtfFechaNacimiento.setText("");
		jcbSexo.setSelectedIndex(0);
		jbcTipoSangre.setSelectedIndex(0);
	}

	private void guardar() {
		String identificacion = jtfIdentificacion.getText();
		String nombre = jtfNombre.getText();
		String apellido = jtfApellido.getText();
		String fechaNacimiento = jtfFechaNacimiento.getText();
		char sexo = ((String) jcbSexo.getSelectedItem()).charAt(0);
		String tipoSangre = (String) jbcTipoSangre.getSelectedItem();
		if (identificacion == null || identificacion.compareToIgnoreCase("") == 0)
			JOptionPane.showMessageDialog(null, "Ingrese la identificacion", "Mensaje del Sistema",
					JOptionPane.ERROR_MESSAGE);
		else if (nombre == null || nombre.compareToIgnoreCase("") == 0)
			JOptionPane.showMessageDialog(null, "Ingrese el nombre", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		else if (apellido == null || apellido.compareToIgnoreCase("") == 0)
			JOptionPane.showMessageDialog(null, "Ingrese el apellido", "Mensaje del Sistema",
					JOptionPane.ERROR_MESSAGE);
		else if (fechaNacimiento == null || fechaNacimiento.compareToIgnoreCase("") == 0)
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de nacimiento", "Mensaje del Sistema",
					JOptionPane.ERROR_MESSAGE);
		else if (jcbSexo.getSelectedIndex() == 0)
			JOptionPane.showMessageDialog(null, "Ingrese el sexo", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		else if (jbcTipoSangre.getSelectedIndex() == 0)
			JOptionPane.showMessageDialog(null, "Ingrese el tipo de sangre", "Mensaje del Sistema",
					JOptionPane.ERROR_MESSAGE);
		else {
			Persona1 persona = new Persona1(identificacion, nombre, apellido, fechaNacimiento, sexo, tipoSangre);
			listPersona.add(persona);
			añadirPersonaTabla(persona);
			limpiarCancelar();
		}
	}

	private void añadirPersonaTabla(Persona1 persona) {
		Object data[] = new Object[6];
		data[0] = persona.getIdentificacion();
		data[1] = persona.getNombre();
		data[2] = persona.getApellido();
		data[3] = persona.getFechaNacimiento();
		data[4] = persona.getSexo();
		data[5] = persona.getTipoSangre();
		model.addRow(data);
	}

	public void cargarPersona(int fila) {
		Persona1 persona = listPersona.get(fila);
		jtfIdentificacion.setText(persona.getIdentificacion());
		jtfNombre.setText(persona.getNombre());
		jtfApellido.setText(persona.getApellido());
		jtfFechaNacimiento.setText(persona.getFechaNacimiento());
		jcbSexo.setSelectedItem(String.valueOf(persona.getSexo()));
		jbcTipoSangre.setSelectedItem(persona.getTipoSangre());
	}

}

class Persona1 {
	private String identificacion;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private char sexo;
	private String tipoSangre;

	public Persona1() {
	}

	public Persona1(String identificacion, String nombre, String apellido, String fechaNacimiento, char sexo,
			String tipoSangre) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.tipoSangre = tipoSangre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

}
