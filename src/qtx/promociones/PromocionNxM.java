package qtx.promociones;

public class PromocionNxM extends Promocion{
	private int n;
	private int m;
	
	public PromocionNxM(int n, int m) {
		super();
		this.nombre = n + " X " + m;
		this.descripcion = "Pague " + m + " y llévese " + n;
		this.n = n;
		this.m = m;
	}
	
	@Override
	public float calcularPrecio(int cantidad, float precio) {
		int uniPrecioNormal = cantidad % n;
		int uniConPromocion = cantidad - uniPrecioNormal;
		int uniAcobrar = uniConPromocion / n * m + uniPrecioNormal;
		return uniAcobrar * precio;
	}
}
