package examenFinalBrandonBatesOrtiz;

import java.io.Serializable;

public abstract class Pasteles implements Serializable {
	private MarcaEnum marcaEnum;
	private String sabor;
	private int calorias;

	public Pasteles(MarcaEnum marcaEnum, String sabor, int calorias) {
		this.marcaEnum = marcaEnum;
		this.sabor = sabor;
		this.calorias = calorias;
	}

	public MarcaEnum getMarcaEnum() {
		return marcaEnum;
	}

	public void setMarcaEnum(MarcaEnum marcaEnum) {
		this.marcaEnum = marcaEnum;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

}
