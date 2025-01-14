package examen;

public class Humano extends Heroes {

	public Humano(String nombre, int vida, int ataque, int defensa) {
		super(nombre, vida, ataque, defensa);
	}

	@Override
	public void comprobar() {
		if (getVida() <= 0) {
			setVida(0);
			System.out.println("El humano " + getNombre() + " ha muerto");
			Menus.salir = true;
		}
	}
}
