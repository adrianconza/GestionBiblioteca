package gestionBiblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsDate {

	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public static Date stringToDate(String fecha) {
		try {
			return formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(Date fecha) {
		try {
			return formatoFecha.format(fecha);
		} catch (Exception e) {
			return "";
		}
	}

}