package ejercicioGalleta;

import java.io.Serializable;

public class Galleta implements Serializable {
	String tipo;

	public Galleta(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tipo de galleta: " + tipo;
	}
}
