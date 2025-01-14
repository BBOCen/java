package examenFinalBrandonBatesOrtiz;

public class Persona {
	private final String nombre = "Juan";
	private int cruasanesComidos;
	private int caloriasConsumidas;

	public Persona(int cruasanesComidos, int caloriasConsumidas) {
		this.cruasanesComidos = cruasanesComidos;
		this.caloriasConsumidas = caloriasConsumidas;
	}

	public int getCruasanesComidos() {
		return cruasanesComidos;
	}

	public void setCruasanesComidos(int cruasanesComidos) {
		this.cruasanesComidos = cruasanesComidos;
	}

	public int getCaloriasConsumidas() {
		return caloriasConsumidas;
	}

	public void setCaloriasConsumidas(int caloriasConsumidas) {
		this.caloriasConsumidas = caloriasConsumidas;
	}

	public String getNombre() {
		return nombre;
	}

	public void mostrarDatos() {
		System.out.println(nombre + " se ha comido " + cruasanesComidos + " cruasanes y ha consumido "
				+ caloriasConsumidas + " calorías");
	}

}
