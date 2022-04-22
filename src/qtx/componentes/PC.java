package qtx.componentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PC extends ComponentePC{
	private TarjetaVideo tarjetaVideo;
	private Monitor monitor;
	private List<DiscoDuro> discos;	
	
	public PC(String marca, String modelo, TarjetaVideo tarjetaVideo,
			Monitor monitor, DiscoDuro[] discos) {
		this.marca = marca;
		this.modelo = modelo;
		this.tarjetaVideo = tarjetaVideo;
		this.monitor = monitor;
		this.discos = Arrays.asList(discos);
	}
	public PC(String marca, String modelo) {
		this.marca = marca;
		this.modelo = modelo;
		this.discos = new ArrayList<>();
	}
	public String getIdentificacion(){
		return "PC";
	}
	public float getPrecioUnitario(){
		float precioCotizado = this.monitor.getPrecioUnitario() +
							this.tarjetaVideo.getPrecioUnitario();
		for(DiscoDuro discoI:this.discos)
			precioCotizado+=discoI.getPrecioUnitario();
		
		return precioCotizado * 0.80f;
	}


	public void desplegarCaracteristicas(){
		System.out.println("=============================");
		System.out.println("Características de la PC");
		System.out.println("Marca:"+this.marca);
		System.out.println("Modelo:"+this.modelo);
		this.tarjetaVideo.desplegarCaracteristicas();
		this.monitor.desplegarCaracteristicas();
		for(DiscoDuro discoI : this.discos)
		   discoI.desplegarCaracteristicas();
		System.out.println("=============================");
	}
	public TarjetaVideo getTarjetaVideo() {
		return tarjetaVideo;
	}

	public void setTarjetaVideo(TarjetaVideo tarjetaVideo) {
		this.tarjetaVideo = tarjetaVideo;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public DiscoDuro[] getDiscos() {
		return this.discos.toArray(new DiscoDuro[0]);
	}

	public void agregarDisco(DiscoDuro disco) {
		this.discos.add(disco);
	}

}
