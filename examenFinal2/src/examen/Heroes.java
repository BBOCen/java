package examen;

public abstract class Heroes {
	private String nombre;
	private int vida, ataque, defensa;

	public Heroes(String nombre, int vida, int ataque, int defensa) {
		super();
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	// Aquí he puesto Villano en el argumento del método para que pueda operar con
	// los atributos del villano
	public void atacar(Villano v) {
		int danio = ataque - v.getDefensa();
		// Aquí se comprueba el daño del ataque, si el daño es negativo (la defensa es
		// superior al ataque, se iguala esta variable a 0, ya que no tiene sentido
		// operar con un daño negativo)
		if (danio < 0) {
			danio = 0;
		}
		v.setVida(v.getVida() - danio);
		System.out.println("El héroe " + nombre + " ha atacado al villano " + v.getNombre() + " y ha perdido " + danio
				+ " puntos de vida");
	}

	public void mostrarEstado() {
		System.out.println("Estado del héroe: \nNombre: " + nombre + "\nVida: " + vida + "\nAtaque: " + ataque
				+ "\nDefensa: " + defensa);
	}

	// Aquí he puesto un método abstracto, ya que este posteriormente se
	// implementará en las clases hijas
	public abstract void comprobar();
}
