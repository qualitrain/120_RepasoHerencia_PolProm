package qtx.promociones;

import java.util.ArrayList;
import java.util.List;

public class PromocionAgregada extends Promocion{
	private Promocion promBase;
	private List<PromocionDscto> promsAnidadas;
	
	public PromocionAgregada(Promocion promBase) {
		super();
		this.promsAnidadas = new ArrayList<PromocionDscto>();
		this.promBase = promBase;
		this.nombre = "Promocion agregada";
	}
	@Override
	public float calcularPrecio(int cantidad, float precio) {
//		System.out.println("PromocionAgregada.calcularPrecio(" + cantidad + ", " + precio + ")");
		if(cantidad == 0)
			return 0;
		float precioAgregado = this.promBase.calcularPrecio(cantidad, precio);
//		System.out.println("precioAgregado = " + precioAgregado);
		for(Promocion promI:this.promsAnidadas) {
			precioAgregado = promI.calcularPrecio(cantidad, precioAgregado / cantidad);
		}
		return precioAgregado;
	}
	public void agregarPromocion(PromocionDscto promExtra) {
		this.promsAnidadas.add(promExtra);
	}
	@Override
	public String getDescripcion() {
		String descAgregada = "Promociones: " + this.promBase.getDescripcion();
		for(PromocionDscto promI : this.promsAnidadas) {
			descAgregada += " + " + promI.getDescripcion();
		}
		return descAgregada;
	}
}
