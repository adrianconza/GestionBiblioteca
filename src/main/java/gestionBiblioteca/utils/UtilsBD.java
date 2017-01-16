package gestionBiblioteca.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import org.postgresql.util.PSQLException;

public class UtilsBD {

	private static UtilsBD utilsBD;

	private String driver = "org.postgresql.Driver";
	private Properties propBaseDatos;
	private Connection con;
	private PreparedStatement pst;

	private UtilsBD() {
		try {
			leerPropBaseDatos();
			Class.forName(driver).newInstance();
			String urlBD = "jdbc:postgresql://" + propBaseDatos.getProperty("jdbc.server") + ":"
					+ propBaseDatos.getProperty("jdbc.port") + "/" + propBaseDatos.getProperty("jdbc.database");
			System.out.println("ORM - URL BD: " + urlBD);
			System.out.println("ORM - User BD: " + propBaseDatos.getProperty("jdbc.user"));
			System.out.println("ORM - Password BD: " + propBaseDatos.getProperty("jdbc.password"));
			con = DriverManager.getConnection(urlBD, propBaseDatos.getProperty("jdbc.user"),
					propBaseDatos.getProperty("jdbc.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UtilsBD getInstance() {
		if (utilsBD == null)
			utilsBD = new UtilsBD();
		return utilsBD;
	}

	private void leerPropBaseDatos() throws IOException, Exception {
		if (propBaseDatos == null) {
			propBaseDatos = new Properties();
			InputStream is = null;
			try {
				is = new FileInputStream(this.getClass().getResource("/database.properties").getPath());
			} catch (Exception e) {
				is = new FileInputStream("database.properties");
			}
			propBaseDatos.load(is);
		}
	}

	public ResultSet consulta(String sql) throws Exception {
		System.out.println("ORM: " + sql);
		pst = con.prepareStatement(sql);
		return pst.executeQuery();
	}

	public boolean sentencia(String sql, Object[] params) throws Exception {
		try {
			String stringParams = "";
			pst = con.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					if (param == null) {
						pst.setNull(i + 1, java.sql.Types.NULL);
						stringParams += param + ", ";
					} else if (param instanceof String) {
						pst.setString(i + 1, (String) param);
						stringParams += "'" + param + "', ";
					} else if (param instanceof Character) {
						pst.setString(i + 1, String.valueOf(param));
						stringParams += "'" + String.valueOf(param) + "', ";
					} else if (param instanceof Integer) {
						pst.setInt(i + 1, (Integer) param);
						stringParams += param + ", ";
					} else if (param instanceof Date) {
						pst.setDate(i + 1, new java.sql.Date(((Date) param).getTime()));
						stringParams += "'" + UtilsDate.dateToString((Date) param) + "', ";
					}
				}
			if (sql.contains("INSERT"))
				System.out.println("ORM: " + sql.substring(0, sql.indexOf("VALUES (")) + "("
						+ stringParams.substring(0, stringParams.length() - 2) + ");");
			else if (sql.contains("UPDATE") || sql.contains("DELETE")) {
				sql = sql.replace("?", "¬");
				String[] sqlAux = sql.split("¬");
				sql = "";
				for (int i = 0; i < sqlAux.length - 1; i++) {
					sql += sqlAux[i];
					if (params[i] instanceof String) {
						sql += "'" + params[i] + "'";
					} else if (params[i] instanceof Character) {
						sql += "'" + String.valueOf(params[i]) + "'";
					} else if (params[i] instanceof Integer) {
						sql += params[i];
					} else if (params[i] instanceof Date) {
						sql += "'" + UtilsDate.dateToString((Date) params[i]) + "'";
					}
				}
				System.out.println("ORM: " + sql + ";");
			} else
				System.out.println("ORM: " + sql);
			pst.executeUpdate();
			pst.close();
			return true;
		} catch (PSQLException e) {
			if (!e.getMessage().contains("«=»"))
				throw e;
		}
		return false;
	}

	public void desconectar() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
