package gestionBiblioteca.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UtilsArchivos {

	private static final Properties p = System.getProperties();
	private static final String sep = p.getProperty("file.separator");
	private static final String bd = (p.getProperty("os.name").compareToIgnoreCase("linux") == 0 ? "/opt"
			: p.getProperty("user.home")) + sep + "bd" + sep;

	public static String getBD() {
		return crearRuta(bd);
	}

	public static String getBDPersona() {
		return getBD() + "BDPersona.txt";
	}

	public static String getBDMaterialBibliografico() {
		return getBD() + "BDMaterialBibliografico.txt";
	}

	public static String getBDGestionBiblioteca() {
		return getBD() + "BDGestionBiblioteca.txt";
	}

	public static String crearRuta(String ruta) {
		File directorio = new File(ruta);
		if (!directorio.exists())
			directorio.mkdirs();
		return ruta;
	}

	public static boolean eliminarArchivo(String archivo) {
		File directorio = new File(archivo);
		return directorio.delete();
	}

	public static boolean moverArchivo(String archivo, String archivoFinal) {
		File a = new File(archivo);
		return a.renameTo(new File(archivoFinal));
	}

	public static List<String> obtenerTodos(String archivo) throws Exception {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = br.readLine()) != null)
				list.add(linea);
		} catch (Exception e) {
			throw new Exception("Error al leer el fichero");
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e2) {
				throw new Exception("Error al cerrar el fichero");
			}
		}
		return list;
	}

	public static void insertar(String archivo, String linea) throws Exception {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(archivo, "rw");
			file.seek(file.length());
			file.writeBytes(linea + "\n");
		} catch (Exception e) {
			throw new Exception("Error al escribir en el fichero");
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (Exception e2) {
				throw new Exception("Error al cerrar el fichero");
			}
		}
	}

	public static void modificarEliminar(String archivo, List<String> list) throws Exception {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(archivo));
			for (String s : list)
				pw.println(s);
		} catch (Exception e) {
			throw new Exception("Error al escribir en el fichero");
		} finally {
			try {
				if (pw != null)
					pw.close();
			} catch (Exception e2) {
				throw new Exception("Error al cerrar el fichero");
			}
		}
	}

	public static <T> List<String> generarListaGuardar(List<T> listaDatos) {
		List<String> listaGuardar = new ArrayList<String>();
		for (T dato : listaDatos)
			listaGuardar.add(dato.toString());
		return listaGuardar;
	}

}
