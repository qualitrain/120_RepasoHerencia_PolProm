package qtx.servicios.cotizacion.mapa;

import qtx.componentes.ComponentePC;

public class DetalleCotizacion {
	private int cantidad;
	private ComponentePC componente;
	
	public DetalleCotizacion(int cantidad, ComponentePC componente) {
		super();
		this.cantidad = cantidad;
		this.componente = componente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ComponentePC getComponente() {
		return componente;
	}

	public void setComponente(ComponentePC componente) {
		this.componente = componente;
	}

	@Override
	public String toString() {
		return "DetalleCotizacion [cantidad=" + cantidad + ", componente="
				+ componente + "]";
	}


}
