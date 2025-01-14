package examen;

public class Namekiano extends Heroes implements BolasDragon {

	public Namekiano(String nombre, int vida, int ataque, int defensa) {
		super(nombre, vida, ataque, defensa);
	}
	
	public boolean usado=false;
	
	//Aquí incluyo la variable booleana usado para saber si ya se usado anteriormente el método revivir, si usado es true, se sale del programa y "muere el personaje"
	
	@Override
	public void comprobar() {
		if (getVida()<=0 && usado) {
			setVida(0);
			System.out.println("El Super Saiyan "+getNombre()+" ha muerto");
			Menus.salir=true;
		}
	}

	// Si usado es true, no se ejecuta este método
	@Override
	public void revivir() {
		 if (!usado && getVida()<=0) {
			setVida(150);
			System.out.println("El Namekiano "+getNombre()+" ha revivido");
			usado=true;
		}
	}

}
