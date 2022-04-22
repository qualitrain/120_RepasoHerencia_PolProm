package qtx.servicios;

import java.util.Locale;

import qtx.componentes.ComponentePC;

public interface ICotizador {
	void agregarItem(ComponentePC componente, int cantidad);
	float cotizar();
	void mostrarCotizacion();
	void setLocalidad(Locale localidad);
}
