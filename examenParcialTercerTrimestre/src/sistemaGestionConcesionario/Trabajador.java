package sistemaGestionConcesionario;

public class Trabajador {
	private String nombre;
	private double salario;
	private int nTrabajador;

	public Trabajador(String nombre, double salario, int nTrabajador) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.nTrabajador=nTrabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getnTrabajador() {
		return nTrabajador;
	}

	public void setnTrabajador(int nTrabajador) {
		this.nTrabajador = nTrabajador;
	}

}
