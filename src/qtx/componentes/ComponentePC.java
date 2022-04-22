package qtx.componentes;

import java.util.List;

import qtx.promociones.Promocion;
import qtx.promociones.PromocionBuilder;

public abstract class ComponentePC{
	protected String marca;
	protected String modelo;
	protected float precioUnitario;
	protected Promocion promocion;
	
	public abstract void desplegarCaracteristicas();
	
	public String getIdentificacion(){
		return "Componente genérico";
	}
	final public float cotizar(int cantidad){
		return this.promocion.calcularPrecio(cantidad, this.getPrecioUnitario());
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public TipoComponente getTipo() {
		if(this instanceof DiscoDuro)
			return TipoComponente.DISCO_DURO;
		if(this instanceof TarjetaVideo)
			return TipoComponente.TARJETA_VIDEO;
		if(this instanceof Monitor)
			return TipoComponente.MONITOR;
		if(this instanceof PC)
			return TipoComponente.PC;
		return TipoComponente.INDEFINIDO;
	}
	
	public static ComponentePC crear(TipoComponente tipo, String marca, String modelo) {
		String capacidad = null;
		return crear(tipo, marca, modelo, capacidad);
	}
	public static ComponentePC crear(TipoComponente tipo, String marca, String modelo, Promocion promo) {
		String capacidad = null;
		return crear(tipo, marca, modelo, capacidad, promo);
	}
	public static ComponentePC crearAgregado(TipoComponente tipo, String marca, String modelo, List<ComponentePC> subComponentes) {
		switch(tipo) {
		case PC:
			PC pc = new PC(marca,modelo);
			for(ComponentePC subcomI : subComponentes) {
				if(subcomI.getTipo() == TipoComponente.MONITOR) {
					pc.setMonitor((Monitor) subcomI);
					continue;
				}
				if(subcomI.getTipo() == TipoComponente.DISCO_DURO) {
					pc.agregarDisco((DiscoDuro) subcomI);
					continue;
				}
				if(subcomI.getTipo() == TipoComponente.TARJETA_VIDEO) {
					pc.setTarjetaVideo((TarjetaVideo) subcomI);
				}
			}
			pc.setPromocion(Promocion.crear(new PromocionBuilder()));
			return pc;
		default:
			return null;
		}
	}
	
	public static ComponentePC crearAgregado(TipoComponente tipo, String marca, String modelo, List<ComponentePC> subComponentes, Promocion promo) {
		switch(tipo) {
		case PC:
			PC pc = new PC(marca,modelo);
			for(ComponentePC subcomI : subComponentes) {
				if(subcomI.getTipo() == TipoComponente.MONITOR) {
					pc.setMonitor((Monitor) subcomI);
					continue;
				}
				if(subcomI.getTipo() == TipoComponente.DISCO_DURO) {
					pc.agregarDisco((DiscoDuro) subcomI);
					continue;
				}
				if(subcomI.getTipo() == TipoComponente.TARJETA_VIDEO) {
					pc.setTarjetaVideo((TarjetaVideo) subcomI);
				}
			}
			pc.setPromocion(promo);
			return pc;
		default:
			return null;
		}
	}
	
	public static ComponentePC crear(TipoComponente tipo, String marca, String modelo, String capacidad) {
		ComponentePC componente = null;
		switch (tipo)
		{
		case DISCO_DURO:
			componente = new DiscoDuro(marca, modelo, capacidad);
			componente.setPromocion(Promocion.crear(new PromocionBuilder()));
			return componente;
		case TARJETA_VIDEO:
			componente = new TarjetaVideo(marca,modelo,capacidad);
			componente.setPromocion(Promocion.crear(new PromocionBuilder()));
			return componente;
		case MONITOR:
			componente = new Monitor(marca,modelo);
			componente.setPromocion(Promocion.crear(new PromocionBuilder()));
			return componente;
		default:
			
			break;
		}
		return null;
	}
	public static ComponentePC crear(TipoComponente tipo, String marca, String modelo, String capacidad, Promocion promo) {
		ComponentePC componente = null;
		switch (tipo)
		{
		case DISCO_DURO:
			componente = new DiscoDuro(marca, modelo, capacidad);
			componente.setPromocion(promo);
			return componente;
		case TARJETA_VIDEO:
			componente = new TarjetaVideo(marca,modelo,capacidad);
			componente.setPromocion(promo);
			return componente;
		case MONITOR:
			componente = new Monitor(marca,modelo);
			componente.setPromocion(promo);
			return componente;
		default:			
			break;
		}
		return null;
	}
	
}