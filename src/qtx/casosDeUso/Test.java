package qtx.casosDeUso;

import java.util.ArrayList;
import java.util.List;
// import java.util.Locale;

import qtx.componentes.ComponentePC;
import qtx.componentes.TipoComponente;
import qtx.promociones.Promocion;
import qtx.promociones.PromocionBuilder;
import qtx.servicios.ICotizador;

public class Test{
	
	public static void main(String[] args){
		testCotizacionArticulos();
		testCotizacionArticulosPromo();
		testCotizador3();
		testCotizador3_promo();
	}
	public static void testCotizacionArticulos() {
		System.out.println("\n--- Test Cotizacion Simple ---");
		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
		disco1.setPrecioUnitario(555.0f);
		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
		disco2.setPrecioUnitario(445.0f);
	
		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb");
		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56");
		monitor.setPrecioUnitario(1000.0f);
		
		List<ComponentePC> subcomponentes = new ArrayList<>();
		subcomponentes.add(tarjeta);
		subcomponentes.add(monitor);
		subcomponentes.add(disco1);
		subcomponentes.add(disco2);
		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes);
		miPc.desplegarCaracteristicas();
		tarjeta.setPrecioUnitario(100.0f);
		
		for (int unidades=1;unidades<11;unidades++)
			System.out.println("Cotización de " + unidades
					        +" unidades de " 
							+ tarjeta.getIdentificacion() + " con precio unitario de "
							+ tarjeta.getPrecioUnitario()
							+ " = " 
							+ tarjeta.cotizar(unidades));
		
		System.out.println("Cotización de " + 2 + " unidades de "
				+ miPc.getIdentificacion() + " = " 
				+ miPc.cotizar(2));
		
		System.out.println("Cotización de "+4+" unidades de "+monitor.getIdentificacion()+" = "+
				monitor.cotizar(4));
	}
	public static void testCotizacionArticulosPromo() {
		System.out.println("\n--- Test Cotizacion Simple con Promociones ---");
		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
		disco1.setPrecioUnitario(555.0f);
		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
		disco2.setPrecioUnitario(445.0f);
	
		Promocion promoTarjeta = Promocion.crear(new PromocionBuilder().conNxM(3, 2));
		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb", promoTarjeta);
		
		Promocion promoMonitor = Promocion.crear(new PromocionBuilder().conDesctoXrango(5, 3, 5)
				                                                       .conDesctoXrango(10, 6, 1000));
		
		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56",promoMonitor);
		monitor.setPrecioUnitario(1000.0f);
		
		List<ComponentePC> subcomponentes = new ArrayList<>();
		subcomponentes.add(tarjeta);
		subcomponentes.add(monitor);
		subcomponentes.add(disco1);
		subcomponentes.add(disco2);
		Promocion promoPc = Promocion.crear(new PromocionBuilder().conDesctoPlano(5, "Oferta Verano"));
		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes, promoPc);
		tarjeta.setPrecioUnitario(100.0f);
		
		for (int unidades=1;unidades<11;unidades++)
			System.out.println("Cotización de " + unidades
					        +" unidades de " 
							+ tarjeta.getIdentificacion() + " con precio unitario de "
							+ tarjeta.getPrecioUnitario()
							+ " = " 
							+ tarjeta.cotizar(unidades));
		
		System.out.println("Cotización de " + 2 
				+ " unidades de " + miPc.getIdentificacion() 
				+ " = " + miPc.cotizar(2));
		
		System.out.println("Cotización de " + 4 
				+ " unidades de " + monitor.getIdentificacion() 
				+ " = " + monitor.cotizar(4));
	}
	
//	public static void testCotizador() {
//		System.out.println("\n--- TestCotizador: Prueba de cotizadores (implementados con un ArrayList) ---");
//		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
//		disco1.setPrecioUnitario(555.0f);
//		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
//		disco2.setPrecioUnitario(445.0f);
//	
//		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb");
//		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56");
//		monitor.setPrecioUnitario(1000.0f);
//		
//		List<ComponentePC> subcomponentes = new ArrayList<>();
//		subcomponentes.add(tarjeta);
//		subcomponentes.add(monitor);
//		subcomponentes.add(disco1);
//		subcomponentes.add(disco2);
//		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes);
//		tarjeta.setPrecioUnitario(100.0f);
//		
//		ICotizador cotizador = new CotizadorImpl2ArrayList();
//		cotizador.agregarItem(miPc, 3);
//		cotizador.agregarItem(monitor, 5);
//		cotizador.agregarItem(disco2, 10);
//		cotizador.agregarItem(tarjeta, 7);
//		cotizador.mostrarCotizacion();
//		
//		ICotizador cotizador2 = new CotizadorImpl2ArrayListLocale(Locale.GERMANY);
//		cotizador2.agregarItem(miPc, 3);
//		cotizador2.agregarItem(monitor, 5);
//		cotizador2.agregarItem(disco2, 10);
//		cotizador2.agregarItem(tarjeta, 7);
//		cotizador2.mostrarCotizacion();
//		
//		ICotizador cotizador3 = new CotizadorImpl2ArrayListLocale();
//		cotizador3.agregarItem(miPc, 3);
//		cotizador3.agregarItem(monitor, 5);
//		cotizador3.agregarItem(disco2, 10);
//		cotizador3.agregarItem(tarjeta, 7);
//		cotizador3.mostrarCotizacion();
//		
//	}
//	public static void testCotizador2() {
//		System.out.println("\n--- Test Cotizador2 (Prueba de un cotizador implementado con un Mapa) ---");
//		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
//		disco1.setPrecioUnitario(555.0f);
//		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
//		disco2.setPrecioUnitario(445.0f);
//	
//		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb");
//		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56");
//		monitor.setPrecioUnitario(1000.0f);
//		
//		List<ComponentePC> subcomponentes = new ArrayList<>();
//		subcomponentes.add(tarjeta);
//		subcomponentes.add(monitor);
//		subcomponentes.add(disco1);
//		subcomponentes.add(disco2);
//		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes);
//		tarjeta.setPrecioUnitario(100.0f);
//		
//		ICotizador cotizador = new CotizadorImplMap();
//		cotizador.agregarItem(miPc, 3);
//		cotizador.agregarItem(monitor, 5);
//		cotizador.agregarItem(disco2, 10);
//		cotizador.agregarItem(tarjeta, 7);
//		cotizador.mostrarCotizacion();
//				
//	}
	public static void testCotizador3() {
		System.out.println("\n--- Test Cotizador3 (Prueba de un cotizador definido por configuración) ---");
		
		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
		disco1.setPrecioUnitario(555.0f);
		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
		disco2.setPrecioUnitario(445.0f);
	
		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb");
		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56");
		monitor.setPrecioUnitario(1000.0f);
		
		List<ComponentePC> subcomponentes = new ArrayList<>();
		subcomponentes.add(tarjeta);
		subcomponentes.add(monitor);
		subcomponentes.add(disco1);
		subcomponentes.add(disco2);
		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes);
		tarjeta.setPrecioUnitario(100.0f);
		
		ICotizador cotizador = Configuracion.getCotizador();
		cotizador.agregarItem(miPc, 3);
		cotizador.agregarItem(monitor, 5);
		cotizador.agregarItem(disco2, 10);
		cotizador.agregarItem(tarjeta, 7);
		cotizador.mostrarCotizacion();
				
	}
	public static void testCotizador3_promo() {
		System.out.println("\n--- Test Cotizador3_promo (Prueba de un cotizador definido por configuración) ---");
		
		Promocion promoTarjeta = Promocion.crear(new PromocionBuilder().conNxM(3, 2));
		Promocion promoMonitor = Promocion.crear(new PromocionBuilder().conDesctoXrango(5, 3, 5)
                .conDesctoXrango(10, 6, 1000));
		Promocion promoPc = Promocion.crear(new PromocionBuilder().conDesctoPlano(5, "Oferta Verano"));
		
		ComponentePC disco1 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Seagate","XD34","400Gb");
		disco1.setPrecioUnitario(555.0f);
		ComponentePC disco2 = ComponentePC.crear(TipoComponente.DISCO_DURO, "Dell","Z360","600Gb");
		disco2.setPrecioUnitario(445.0f);
	
		ComponentePC tarjeta = ComponentePC.crear(TipoComponente.TARJETA_VIDEO,"Sony","YOKO34","2Gb",promoTarjeta);
		ComponentePC monitor = ComponentePC.crear(TipoComponente.MONITOR,"Samsung","Flat-56",promoMonitor);
		monitor.setPrecioUnitario(1000.0f);
		
		List<ComponentePC> subcomponentes = new ArrayList<>();
		subcomponentes.add(tarjeta);
		subcomponentes.add(monitor);
		subcomponentes.add(disco1);
		subcomponentes.add(disco2);
		ComponentePC miPc = ComponentePC.crearAgregado(TipoComponente.PC, "Dell","Latitude E456", subcomponentes, promoPc);
		tarjeta.setPrecioUnitario(100.0f);
		
		ICotizador cotizador = Configuracion.getCotizador();
		cotizador.agregarItem(miPc, 3);
		cotizador.agregarItem(monitor, 5);
		cotizador.agregarItem(disco2, 10);
		cotizador.agregarItem(tarjeta, 7);
		cotizador.mostrarCotizacion();
				
	}
	
}
