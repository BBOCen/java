package examen;

public class Villano {
	private String nombre;
	private int vida, ataque, defensa;

	public Villano(String nombre, int vida, int ataque, int defensa) {
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

	// Aquí he puesto Heroes en el argumento del método para que pueda operar con
	// los atributos del héroe
	public void atacar(Heroes h) {
		int danio = ataque - h.getDefensa();
		// Aquí se comprueba el daño del ataque, si el daño es negativo (la defensa es
		// superior al ataque, se iguala esta variable a 0, ya que no tiene sentido
		// operar con un daño negativo)
		if (danio < 0) {
			danio = 0;
		}
		h.setVida(h.getVida() - danio);
		System.out.println("El villano " + nombre + " ha atacado al héroe " + h.getNombre() + " y ha perdido " + danio
				+ " puntos de vida");
	}

	public void mostrarEstado() {
		System.out.println("Estado del villano: \nNombre: " + nombre + "\nVida: " + vida + "\nAtaque: " + ataque
				+ "\nDefensa: " + defensa);
	}

	// Si usado es true, no se ejecuta este método
	public void comprobar() {
		if (getVida() <= 0) {
			setVida(0);
			System.out.println("El villano " + nombre + " ha muerto");
			Menus.salir = true;
		}
	}

}
