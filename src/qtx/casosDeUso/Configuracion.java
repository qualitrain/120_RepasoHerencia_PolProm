package qtx.casosDeUso;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;

import qtx.servicios.ICotizador;

public class Configuracion {
	private static String nombreClaseCotizador = "";
	private static Locale localidad;
	static {
		cargarPropiedades();
	}
	public static ICotizador getCotizador() {
			try {
				Class<?> claseCotizador = Class.forName(nombreClaseCotizador);
				Constructor<?> constCotizador = claseCotizador.getDeclaredConstructor();
				ICotizador cotizador = (ICotizador) constCotizador.newInstance();
				cotizador.setLocalidad(localidad);
				return cotizador;				
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			catch (InstantiationException | InvocationTargetException e) {
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
	}
	private static void cargarPropiedades() {
		try(FileReader reader = new FileReader("config.properties")){
			Properties propiedadesConfig = new Properties();
			propiedadesConfig.load(reader);
			Configuracion.nombreClaseCotizador = propiedadesConfig.getProperty("claseCotizador");
			
			String nomLocalidad = propiedadesConfig.getProperty("localidad");
			switch(nomLocalidad) {
			case "Alemania":
				Configuracion.localidad = Locale.GERMANY;
				break;
			case "Japon":
				Configuracion.localidad = Locale.JAPAN;
				break;
			case "Mexico":
				Configuracion.localidad = new Locale("es","MX");
				break;				
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
