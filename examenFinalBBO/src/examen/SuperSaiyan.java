package examen;

public class SuperSaiyan extends Heroes implements BolasDragon {

	public SuperSaiyan(String nombre, int vida, int ataque, int defensa) {
		super(nombre, vida, ataque, defensa);
	}

	public boolean usado = false;

	// Al igual que el método atacar(), en este método he incluido al objeto Villano
	// por parámetro para poder operar con los atributos del este objeto
	public void kame(Villano v) {
		int danio = 2 * getAtaque() - v.getDefensa();
		if (danio < 0) {
			danio = 0;
		}
		v.setVida(v.getVida() - danio);
		System.out.println(getNombre() + " ha usado Kamehameha contra " + v.getNombre() + " y este ha perdido " + danio
				+ " puntos de vida");
	}

	// Aquí incluyo la variable booleana usado para saber si ya se usado
	// anteriormente el método revivir, si usado es true, se sale del programa y
	// "muere el personaje"
	@Override
	public void comprobar() {
		if (getVida() <= 0 && usado) {
			setVida(0);
			System.out.println("El Super Saiyan " + getNombre() + " ha muerto");
			Menus.salir = true;
		}
	}

	// Si usado es true, no se ejecuta este método
	@Override
	public void revivir() {
		if (!usado && getVida() <= 0) {
			setVida(180);
			System.out.println("El Super Saiyan " + getNombre() + " ha revivido");
			usado = true;
		}
	}
}
