package qtx.casosDeUso;

import qtx.promociones.Promocion;
import qtx.promociones.PromocionAgregada;
import qtx.promociones.PromocionBuilder;
import qtx.promociones.PromocionDscto;
import qtx.promociones.PromocionDsctoVolumen;
import qtx.promociones.PromocionNxM;

public class TestPromo {

	public static void main(String[] args) {
		probarPromNxM(4,3);
		probarPromDscto(10);
		probarPromAgregada(3, 2, 10);
		probarPromAgregada(20f, 10f, 5f);
		probarPromPorRangos();
		
		probarPromNxM_Builder(4,3);
		probarPromDscto_Builder(10);
		probarPromAgregada_Builder(3, 2, 10);
		probarPromAgregada_Builder(20f, 10f, 5f);
		probarPromPorRangos_Builder();
	}
	private static void probarPromNxM(int N, int M) {
		System.out.println("\n-------- Probar Promocion "
				+ N
				+ " x "
				+ M
				+ " --------\n");
		Promocion pp = new PromocionNxM(N,M);
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromDscto(float dscto) {
		System.out.println("\n-------- Probar Promocion Dscto " + dscto
				+ "% --------\n");
		Promocion pp = new PromocionDscto(dscto,"Oferta de temporada");
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromAgregada(int N, int M, float dscto) {
		System.out.println("\n-------- Probar Promocion Agregada "
				+ N + " x " + M + " + " + dscto + "% de dscto"
				+ " --------\n");
		PromocionAgregada pp = new PromocionAgregada(new PromocionNxM(N,M));
		pp.agregarPromocion(new PromocionDscto(dscto, "Fin de temporada"));
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromAgregada(float dscto, float dscto2, float dscto3) {
		System.out.println("\n-------- Probar Promocion Agregada "
				+ dscto + "% dscto + " + dscto2 + "% de dscto"
				+ " --------\n");
		
		PromocionAgregada pp = new PromocionAgregada(new PromocionDscto(dscto,"aniversario"));
		pp.agregarPromocion(new PromocionDscto(dscto2, "Fin de temporada"));
		pp.agregarPromocion(new PromocionDscto(dscto3, "Liquidación"));
		
		for(int uni=1; uni<5; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromPorRangos() {
		System.out.println("\n-------- Probar Promocion Agregada formando rangos"
				+ " --------\n");
		
		PromocionAgregada pp = new PromocionAgregada(new PromocionDsctoVolumen(5,"rango 1", 2, 10));
		pp.agregarPromocion(new PromocionDsctoVolumen(10, "rango 2", 11, 20));
		pp.agregarPromocion(new PromocionDsctoVolumen(15, "rango 3", 21, 1000));
		pp.agregarPromocion(new PromocionDscto(5, "Liquidación"));
		
		for(int uni=1; uni<50; uni+=8) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromNxM_Builder(int N, int M) {
		System.out.println("\n-------- Probar Promocion "
				+ N
				+ " x "
				+ M
				+ " con Builder --------\n");
		Promocion pp = Promocion.crear(new PromocionBuilder().conNxM(N, M));
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromDscto_Builder(float dscto) {
		System.out.println("\n-------- Probar Promocion Dscto " + dscto
				+ "% con Builder --------\n");
		Promocion pp = Promocion.crear(new PromocionBuilder().conDesctoPlano(dscto, "Oferta de temporada"));
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromAgregada_Builder(int N, int M, float dscto) {
		System.out.println("\n-------- Probar Promocion Agregada "
				+ N + " x " + M + " + " + dscto + "% de dscto"
				+ " con un Builder --------\n");
		Promocion pp = Promocion.crear(
				new PromocionBuilder().conNxM(N, M)
				                      .conDesctoPlano(dscto, "Fin de temporada")
				);
		
		for(int uni=0; uni<13; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromAgregada_Builder(float dscto, float dscto2, float dscto3) {
		System.out.println("\n-------- Probar Promocion Agregada "
				+ dscto + "% dscto + " + dscto2 + "% de dscto + " + dscto3 + "% de dscto"
				+ " con Builder --------\n");
		
		Promocion pp = Promocion.crear(
				new PromocionBuilder().conDesctoPlano(dscto, "aniversario")
                                      .conDesctoPlano(dscto2, "Fin de temporada")
                                      .conDesctoPlano(dscto3, "Liquidación")
				);
		
		for(int uni=1; uni<5; uni++) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}
	private static void probarPromPorRangos_Builder() {
		System.out.println("\n-------- Probar Promocion Agregada formando rangos"
				+ " usando un Builder --------\n");
		
		Promocion pp = Promocion.crear(
				new PromocionBuilder().conDesctoXrango(5, 2, 10)
				                      .conDesctoXrango(10, 11, 20)
				                      .conDesctoXrango(15, 21, 1000)
                                      .conDesctoPlano(5, "Liquidación")
				);
		
		for(int uni=1; uni<50; uni+=8) {
			float precio = pp.calcularPrecio(uni, 1000);
			System.out.println(pp.getDescripcion() + ": " + uni  +  " unidades: " + precio);
		}		
	}

}
