package proyectoFutbolFinTrimestres;

public class Menu {
	public static void menuPrincipal() {
		boolean salir = false;
		int opcion = 0;
		String ruta = "./partido.dat";
		Partido partido = new Partido();
		partido.cargarArchivo(ruta);
		while (!salir) {
			opcion = partido.obtenerEntradaEntero(
					"Selecciona una opción: \n1. Agregar jugador \n2. Registrar evento \n3. Mostrar jugadores \n4. Mostrar eventos ocurridos en el partido \n5. Eliminar jugadores de la base de datos (uno o todos) \n6. Eliminar eventos de la base de datos (uno o todos) \n7. Salir (para guardar los datos del partido, selecciona esta opción)");
			switch (opcion) {
			case 1:
				partido.agregarJugador();
				break;
			case 2:
				partido.registrarEvento();
				break;
			case 3:
				partido.mostrarJugadores();
				break;
			case 4:
				partido.mostrarEventos();
				break;
			case 5:
				partido.eliminarJugadores();
				break;
			case 6:
				partido.eliminarEventos();
				break;
			case 7:
				System.out.println("Has salido del programa con éxito");
				partido.guardarArchivo(partido, ruta);
				salir = true;
				break;
			default:
				System.out.println("Introduce un número válido (1-7)");
			}
		}
	}
}
