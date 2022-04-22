package qtx.servicios.cotizacion.v1;

import java.util.Locale;

import qtx.componentes.ComponentePC;

public class CotizadorImpl2ArrayListLocale extends CotizadorImpl2ArrayList{
	private Locale localidadGeografica;
		
	public CotizadorImpl2ArrayListLocale(Locale localidadGeografica) {
		super();
		this.localidadGeografica = localidadGeografica;
	}
	public CotizadorImpl2ArrayListLocale() {
		super();
		this.localidadGeografica = new Locale("es", "MX");
	}

	public void mostrarCotizacion(){
		float totalCotizacion=0f;
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
		for(int i=0 ; i<this.items.size();i++){
			ComponentePC componenteI = this.items.get(i);
			int cantidad = this.cantidades.get(i);
			float cotizacionItem = componenteI.cotizar(cantidad);
			System.out.print(String.format(" %5d", cantidad));
			System.out.print(String.format("       %-22s",componenteI.getIdentificacion()));
			System.out.print(String.format("   %-10s", componenteI.getMarca()));
			System.out.print(String.format("   %-14s", componenteI.getModelo()));
//			System.out.println("    $ "+String.format("   %8.2f",cotizacionItem));
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
		this.localidadGeografica = localidad;
	}

}
