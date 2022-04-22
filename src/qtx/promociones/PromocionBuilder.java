package qtx.promociones;

import java.util.ArrayList;
import java.util.List;

public class PromocionBuilder {
	private boolean esSimple;
	private boolean tieneNxM;
	private int N;
	private int M;
	private int nRango;
	private List<PromocionDsctoVolumen> rangosDscto;
	private List<PromocionDscto> dsctosPlanos;
	
	public PromocionBuilder() {
		this.esSimple = true;
		this.tieneNxM = false;
		this.nRango = 0;
		this.rangosDscto = new ArrayList<PromocionDsctoVolumen>();
		this.dsctosPlanos = new ArrayList<PromocionDscto>();
	}
	
	public PromocionBuilder conNxM(int N, int M) {
		this.tieneNxM = true;
		this.N = N;
		this.M = M;
		this.esSimple = false;
		return this;
	}
	
	public PromocionBuilder conDesctoXrango(float dscto, int min, int max) {
		this.nRango++;
		this.rangosDscto.add(new PromocionDsctoVolumen(dscto,"rango " + this.nRango, min, max));
		this.esSimple = false;
		return this;
	}
	
	public PromocionBuilder conDesctoPlano(float dscto, String motivo) {
		this.nRango++;
		this.dsctosPlanos.add(new PromocionDscto(dscto, motivo));
		this.esSimple = false;
		return this;
	}

	public boolean esSimple() {
		return esSimple;
	}

	public boolean tieneNxM() {
		return tieneNxM;
	}

	public int getN() {
		return N;
	}

	public int getM() {
		return M;
	}

	public List<PromocionDsctoVolumen> getRangosDscto() {
		return rangosDscto;
	}

	public List<PromocionDscto> getDsctosPlanos() {
		return dsctosPlanos;
	}
	public boolean tieneDesctosPorRango() {
		if(this.rangosDscto.size() > 0)
			return true;
		else
			return false;
	}
	public boolean tieneDesctosPlanos() {
		if(this.dsctosPlanos.size() > 0)
			return true;
		else
			return false;
	}
	
}
