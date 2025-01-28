package examen;

public class Main {

	public static void main(String[] args) {
		// Aquí instanciamos las clases de tipo Heroes, para así poder guardarlos en un
		// array tipo Heroes
		Heroes humano1 = new Humano("Krillin", 130, 100, 100);
		Heroes namekiano1 = new Namekiano("Piccolo", 150, 130, 80);
		Heroes supersaiyan1 = new SuperSaiyan("Goku", 180, 180, 130);
		Heroes[] heroesArray = { humano1, namekiano1, supersaiyan1 };
		Villano villano1 = new Villano("Boo", 300, 160, 160);
		Menus.seleccion(heroesArray, villano1);
	}

}
