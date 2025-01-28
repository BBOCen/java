package examenFinalBrandonBatesOrtiz;

import java.util.Scanner;

public class Menu {
	public static void menuPrincipal() {
		// Declaramos las variables necesarias
		boolean salir = false;
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Vitrina vitrina = new Vitrina();
		String ruta = "cruasanes.dat";
		Persona persona1 = new Persona(0, 0);
		// Este métodos verifica si existe el archivo "cruasanes.dat", si existe, lo
		// carga
		vitrina.cargarArchivo(ruta);

		while (!salir) {
			opcion = vitrina.scannerEntero(
					"Introduce una opción: \n1. Comprar cruasanes \n2. Comer cruasanes \n3. Mostrar todos los cruasanes \n4. Mostrar los cruasanes filtrados por sabor \n5. Salir");
			switch (opcion) {
			case 1:
				vitrina.comprarCruasan();
				break;
			case 2:
				vitrina.comerCruasan(persona1);
				break;
			case 3:
				vitrina.mostrarCruasanes();
				break;
			case 4:
				vitrina.mostrarCruasanesFiltrados();
				break;
			case 5:
				// Este método guarda el archivo al salir del programa (solo si se pulsa la
				// opción 5)
				vitrina.guardarArchivo(vitrina, ruta);
				System.out.println("Has salido del programa");
				salir = true;
				break;
			default:
				System.out.println("Introduce una opción válida (1-5)");
			}
		}
	}
}
