package qtx.promociones;

public class PromocionDscto extends Promocion{
	private float descto;
	private String motivo;

	public PromocionDscto(float descto, String motivo) {
		super();
		this.motivo = motivo;
		this.nombre = "Descuento " + String.format("%4.2f", descto) + "%";
		this.descripcion = "Descuento plano del " + String.format("%4.2f", descto) 
		                   + "% por " + this.motivo;
		this.descto = descto;
	}
	@Override
	public float calcularPrecio(int cantidad, float precio) {
//		System.out.println("PromocionDscto.calcularPrecio(" + cantidad + ", " + precio + ")");
		float factorDscto = 1 - (this.descto / 100f);
//		System.out.println("factorDscto=" + factorDscto);
		return super.calcularPrecio(cantidad, precio) * factorDscto;
	}
	
}
