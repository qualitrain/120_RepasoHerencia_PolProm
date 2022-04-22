package qtx.promociones;

public class Promocion {
	protected String nombre;
	protected String descripcion;
	
	protected Promocion() {
		this.nombre = "precio plano";
		this.descripcion = "precio sin descuento";
	}

	protected Promocion(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float calcularPrecio(int cantidad, float precio) {
		return cantidad * precio;
	}

	public static Promocion crear(PromocionBuilder pBuilder) {
		PromocionAgregada promAgregada;
		if(pBuilder.esSimple())
			return new Promocion();
		if(pBuilder.tieneNxM())
			promAgregada = new PromocionAgregada(new PromocionNxM(pBuilder.getN(), pBuilder.getM()));
		else
			promAgregada = new PromocionAgregada(new Promocion());
		if(pBuilder.tieneDesctosPorRango()) {
			for(PromocionDsctoVolumen promI:pBuilder.getRangosDscto()) {
				promAgregada.agregarPromocion(promI);
			}
		}
		if(pBuilder.tieneDesctosPlanos()) {
			for(PromocionDscto promI:pBuilder.getDsctosPlanos()) {
				promAgregada.agregarPromocion(promI);
			}			
		}
		return promAgregada;
	}
}
