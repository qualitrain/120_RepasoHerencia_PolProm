package qtx.servicios.cotizacion.mapa;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import qtx.componentes.ComponentePC;
import qtx.servicios.ICotizador;

public class CotizadorImplMap implements ICotizador{
	private Map<Integer,DetalleCotizacion> cotizacion;

	public CotizadorImplMap() {
		this.cotizacion = new HashMap<Integer,DetalleCotizacion>();
	}

	@Override
	public void agregarItem(ComponentePC componente, int cantidad) {
		DetalleCotizacion detCotizacion = new DetalleCotizacion(cantidad,componente);
		int numRenglon = this.cotizacion.size() + 1;
		this.cotizacion.put(numRenglon, detCotizacion);
	}

	@Override
	public float cotizar() {
		float totalCotizacion=0f;
		for(DetalleCotizacion detCotI:this.cotizacion.values()){
			ComponentePC componenteI = detCotI.getComponente();
			totalCotizacion+=componenteI.cotizar(detCotI.getCantidad());
		}
		return totalCotizacion;
	}

	@Override
	public void mostrarCotizacion() {
		float totalCotizacion=0f;
		Locale localidadGeografica = new Locale("es", "MX");
		System.out.println();
		System.out.println("===================================================================================");
		System.out.println("                                      Cotización  ");
		System.out.println("===================================================================================");
		System.out.print("Cantidad");
		System.out.print(String.format("%20s", "Descripción"));
		System.out.print(String.format("%15s", "Marca"));
		System.out.print(String.format("%15s", "Modelo"));
		System.out.println(String.format("     %15s", "Importe"));
		System.out.println();
		for(int i=0 ; i<this.cotizacion.size();i++){
			DetalleCotizacion detCot = this.cotizacion.get(i+1);
			ComponentePC componenteI = detCot.getComponente();
			int cantidad = detCot.getCantidad();
			float cotizacionItem = componenteI.cotizar(cantidad);
			System.out.print(String.format(" %5d", cantidad));
			System.out.print(String.format("       %-22s",componenteI.getIdentificacion()));
			System.out.print(String.format("   %-10s", componenteI.getMarca()));
			System.out.print(String.format("   %-14s", componenteI.getModelo()));
			System.out.println("       $ "+String.format(localidadGeografica, "%8.2f",cotizacionItem));
			totalCotizacion+=cotizacionItem;
		}
		System.out.println("===================================================================================");
		System.out.println("TOTAL: "
				+String.format("%67s", "$ ") 
				+String.format(localidadGeografica,"%8.2f", totalCotizacion));
		
	}

	@Override
	public void setLocalidad(Locale localidad) {
	}

}
