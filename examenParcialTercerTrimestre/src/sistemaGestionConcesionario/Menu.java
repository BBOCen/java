package sistemaGestionConcesionario;

import java.util.Scanner;

public class Menu {
	public static Scanner sc=new Scanner(System.in);
	static Concesionario concesionario=new Concesionario();
	
	public static void menuPrincipal() {
		boolean salir=false;
		int opcion = 0;
		while(!salir) {
			try {
				System.out.println("Selecciona una opción con la que quieres trabajar: \n1. Coches \n2. Trabajadores \n3. Mostrar ganancias \n4. Añadir trabajadores y coches ya hechos a la colección \n5. Salir");
				opcion=sc.nextInt();
			}
			catch (Exception e) {
				System.out.println("Intoduce un número válido");
				sc.next();
			}
			switch(opcion) {
			case 1:
				Menu.menuCoches();
				break;
			case 2:
				Menu.menuTrabajadores();
				break;
			case 3:
				System.out.println("Las ganancias del concesionario son: " + Concesionario.getGanancias());
				break;
			case 4:
				concesionario.anadirObjetosHechos();
				break;
			case 5:
				System.out.println("Has salido del programa con éxito");
				salir=true;
				break;
			default:
				System.out.println("Introduce un número válido (1-5)");
			}
		}
	}
	public static void menuTrabajadores() {
		boolean salir=false;
		int opcion=0;
		while(!salir) {
			try {
				System.out.println("Menú trabajadores \nSelecciona una opción: \n1. Añadir un trabajador \n2. Buscar un trabajador (por número de trabajador) \n3. Mostrar trabajadores \n4. Salir");
				opcion=sc.nextInt();
			}
			catch (Exception e) {
				System.out.println("Intoduce un número válido");
				sc.next();
			}
			
			switch(opcion) {
			case 1:
				concesionario.anadirTrabajador();
				break;
			case 2:
				concesionario.buscarTrabajador();
				break;
			case 3:
				concesionario.mostrarTrabajadores();
				break;
			case 4:
				System.out.println("Has salido del menú de trabajadores con éxito");
				salir=true;
				break;
			default:
				System.out.println("Introduce un número válido (1-4)");
			}
		}
		
	}
	public static void menuCoches() {
		boolean salir=false;
		int opcion=0;
		while(!salir) {
			try {
				System.out.println("Menú coches \nSelecciona una opción: \n1. Añadir un coche \n2. Vender un coche (por matrícula) \n3. Mostrar coches \n4. Salir");
				opcion=sc.nextInt();
			}
			catch (Exception e) {
				System.out.println("Intoduce un número válido");
				sc.next();
			}
			
			switch(opcion) {
			case 1:
				concesionario.anadirCoche();
				break;
			case 2:
				concesionario.venderCoche();
				break;
			case 3:
				concesionario.mostrarCoches();
				break;
			case 4:
				System.out.println("Has salido del menú de trabajadores con éxito");
				salir=true;
				break;
			default:
				System.out.println("Introduce un número válido (1-4)");
			}
		}
		
	}
}
