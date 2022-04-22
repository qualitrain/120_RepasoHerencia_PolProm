package qtx.promociones;

public class PromocionDsctoVolumen extends PromocionDscto{
	private int min;
	private int max;
	
	public PromocionDsctoVolumen(float descto, String motivo, int min, int max) {
		super(descto, motivo + "(" + min + "-" + max + ")");
		this.min = min;
		this.max = max;
	}
	@Override
	public float calcularPrecio(int cantidad, float precio) {
		if(cantidad >= min && cantidad <= max)
			return super.calcularPrecio(cantidad, precio);
		else
			return cantidad * precio;
	}
}
