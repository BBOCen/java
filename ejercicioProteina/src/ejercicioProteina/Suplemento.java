package ejercicioProteina;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Suplemento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int cucharadas;
	private String marca;

	// Read types from txt (could have an arraylist with brands from txt, when
	// constructor is created call method for reading file and add to araylist?)

	public Suplemento(String nombre, int cucharadas, String marca) {
		super();
		this.nombre = nombre;
		this.cucharadas = cucharadas;
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCucharadas() {
		return cucharadas;
	}

	public void setCucharadas(int cucharadas) {
		this.cucharadas = cucharadas;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
