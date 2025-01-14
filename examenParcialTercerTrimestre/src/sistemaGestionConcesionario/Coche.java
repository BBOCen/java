package sistemaGestionConcesionario;

import java.util.Objects;

public class Coche implements Comparable<Coche> {
	private int matricula;
	private Marca marca;
	private boolean electrico;
	private double precio;

	public Coche(int matricula, Marca marca, boolean electrico, double precio) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.electrico = electrico;
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return matricula == other.matricula;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public boolean isElectrico() {
		return electrico;
	}

	public void setElectrico(boolean electrico) {
		this.electrico = electrico;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int compareTo(Coche o) {
		return matricula-o.matricula;
	}	
}
