package examen;

import java.util.Scanner;

public class Menus {
	public static boolean salir = false;
	public static Scanner sc = new Scanner(System.in);

	// Aquí es decidido usar un array de tipo heroes para pasar por parámetro los
	// objetos al segundo menú luchar()
	public static void seleccion(Heroes[] heroesArray, Villano v) {
		while (!salir) {
			System.out.println("Selecciona héroe: \n1 Krillin, \n2 Piccolo, \n3 Goku, \n4 Salir.");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				luchar(heroesArray[0], v);
				break;
			case 2:
				luchar(heroesArray[1], v);
				break;
			case 3:
				luchar(heroesArray[2], v);
				break;
			case 4:
				System.out.println("Has salido del juego");
				salir = true;
				break;
			default:
				System.out.println("Opción inválida");
			}
		}
	}

	public static void luchar(Heroes h, Villano v) {
		while (!salir) {
			// En este menú he decidido usar unos ifs para verificar si h es de tipo Super
			// Saiyan o de otro tipo, si es de tipo Super Saiyan, aparece otro menú, el cual
			// incluye la opción 3 (incluye el ataque especial)
			// Si es de tipo Super Saiyan, se castea el objeto de tipo héroe, a tipo Super
			// Saiyan, así se puede usar los métodos de Super Saiyan (revivir() y kame())
			if (h instanceof SuperSaiyan) {
				SuperSaiyan superSaiyan = (SuperSaiyan) h;
				System.out.println("Selecciona una opción: \n1 Luchar \n2 Especial. \n3 Salir.");
				int seleccion = sc.nextInt();
				switch (seleccion) {
				case 1:
					superSaiyan.atacar(v);
					v.atacar(h);
					if (h.getVida() <= 0) {
						superSaiyan.revivir();
					}
					superSaiyan.comprobar();
					v.comprobar();
					superSaiyan.mostrarEstado();
					v.mostrarEstado();
					break;
				case 2:
					superSaiyan.kame(v);
					v.atacar(h);
					if (h.getVida() <= 0) {
						superSaiyan.revivir();
					}
					superSaiyan.comprobar();
					v.comprobar();
					superSaiyan.mostrarEstado();
					v.mostrarEstado();
					break;
				case 3:
					System.out.println("Has salido del juego");
					salir = true;
					break;
				default:
					System.out.println("Opción inválida");
				}
			}
			// Si h no es de tipo Super Saiyan, se mostrará el siguiente menú, el cual no
			// tiene la opción 3 (no incluye el ataque especial)
			else {
				System.out.println("Selecciona una opción: \n1 Luchar \n2 Salir.");
				int seleccion = sc.nextInt();
				switch (seleccion) {
				case 1:
					h.atacar(v);
					v.atacar(h);
					// Aquí se verifica que h es de tipo namekiano y su vida es inferior a 0, si es
					// así, se castea este objeto para usar sus métodos específicos, y se utiliza el
					// método revivir()
					if (h instanceof Namekiano && h.getVida() <= 0) {
						((Namekiano) h).revivir();
					}
					h.comprobar();
					v.comprobar();
					h.mostrarEstado();
					v.mostrarEstado();
					break;
				case 2:
					System.out.println("Has salido del juego");
					salir = true;
					break;
				default:
					System.out.println("Opción inválida");
				}
			}

		}
	}
}
