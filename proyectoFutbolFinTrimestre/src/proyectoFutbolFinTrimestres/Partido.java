package proyectoFutbolFinTrimestres;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Partido implements MetodosPartido, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// La colección para jugadores será un TreeSet, porque se pueden ordenar por el
	// número de la camiseta y no aceptan objetos duplicados (se pondrá en la clase
	// Jugador que un jugador es igual a otro si tiene el mismo número y equipo (ya
	// que un jugador puede tener el mismo número de otro, pero, solamente si es de
	// otro
	// equipo) por lo tanto, no se añadirá a la colección)

	private TreeSet<Jugador> jugadores;

	// Como pueden haber eventos duplicados, se utilizará un array list para guardar
	// los eventos, posteriormente, en el método mostrarEventos(), se organizarán
	// los eventos por el minuto en el que ocurrieron

	private ArrayList<Evento> eventos;

	public Partido() {
		jugadores = new TreeSet<Jugador>();
		eventos = new ArrayList<Evento>();
	}

	public TreeSet<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(TreeSet<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	// Método Scanner para los métodos del menú (si se pone como variable causa
	// problemas con la serialización de los objetos)

	// Este es para los Strings

	public String recibirEntradaString(String mensaje) {
		Scanner sc = new Scanner(System.in);
		String entrada = "";
		boolean salir2 = false;

		while (!salir2) {
			try {
				System.out.println(mensaje);
				entrada = sc.nextLine();
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Introduce una cadena válida");
				sc.next();
			}
		}
		salir2 = false;
		return entrada;
	}

	// Este es para los enteros

	public int obtenerEntradaEntero(String mensaje) {
		Scanner sc = new Scanner(System.in);
		int entradaEntero = 0;
		boolean salir2 = false;

		while (!salir2) {
			try {
				System.out.println(mensaje);
				entradaEntero = sc.nextInt();
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Introduce un número válido");
				sc.next();
			}

		}
		salir2 = false;
		return entradaEntero;
	}

	// Métodos del menú

	// Este método agregará los jugadores a la colección

	@Override

	public void agregarJugador() {
		String nombre = recibirEntradaString("Introduce el nombre del jugador: ");
		System.out.println("Ahora hay que introducir el equipo del jugador, tiene que ser uno de la siguiente lista: ");
		for (Equipo equipo : Equipo.values()) {
			System.out.println(equipo.name());
		}
		boolean salir2 = false;
		String equipoString;
		Equipo equipoEnumGuardado = null;
		while (!salir2) {
			try {
				equipoString = recibirEntradaString("Introduce el equipo del jugador");
				equipoString = equipoString.toUpperCase();
				equipoEnumGuardado = Equipo.valueOf(equipoString);
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Has introducido una opción inválida, introduce una opción de la lista anterior");
			}
		}
		salir2 = false;
		int numeroJugador = 0;
		while (!salir2) {
			numeroJugador = obtenerEntradaEntero("Introduce el número de jugador: ");
			boolean repetido = false;
			// En este for se verifica si hay un jugador con el mismo número y equipo que el
			// que se ha introducido, no nos dejará añadirlo si este es el caso
			for (Jugador jugador : jugadores) {
				if (numeroJugador == jugador.getNumeroJugador() && equipoEnumGuardado.equals(jugador.getEquipo())) {
					repetido = true;
					break;
				}
			}
			if (repetido) {
				System.out
						.println("Ese número ya está asignado a otro jugador del mismo equipo. Introduce otro número");
			} else {
				salir2 = true;
			}
			repetido = false;
		}
		salir2 = false;
		System.out
				.println("Ahora hay que introducir la posición del jugador, tiene que ser uno de la siguiente lista: ");
		for (Posicion posicion : Posicion.values()) {
			System.out.println(posicion.name());
		}
		String posicionString;
		Posicion posicionEnumGuardado = null;
		while (!salir2) {
			try {
				posicionString = recibirEntradaString("Introduce la posición del jugador");
				posicionString = posicionString.toUpperCase();
				posicionEnumGuardado = Posicion.valueOf(posicionString);
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Has introducido una opción inválida, introduce una opción de la lista anterior");
			}
		}
		salir2 = false;
		// Cuando se añade un jugador, su número de goles será 0, ya que este atributo
		// se irá actualizando cuando se registre un evento de tipo gol con este jugador
		jugadores.add(new Jugador(nombre, numeroJugador, 0, equipoEnumGuardado, posicionEnumGuardado));
		System.out.println("El jugador se ha añadido correctamente");
	}

	// Este método agregará los eventos a la colección

	@Override
	public void registrarEvento() {
		// En esta parte se introducirán los datos del jugador para poder identificarlo
		// correctamente, se buscará el jugador en la base datos, si no existe, se
		// volverá a pedir al usuario que introduzca al jugador (también hay que
		// introducir el equipo, ya que dos jugadores pueden tener el mismo número si
		// son de distintos equipos)
		if (jugadores.isEmpty()) {
			System.out.println("Para registrar un evento hace falta que hayan jugadores en la base de datos");
		} else {
			String equipoString;
			boolean salir2 = false;
			boolean salir3 = false;
			Jugador jugadorGuardado = null;
			Equipo equipoEnumGuardado = null;
			// Este bucle funciona para introducir los datos del jugador que ha participado
			// en el evento, si no coinciden con los datos de un jugador que ya existe en la
			// base de datos, volverá a salir el mensaje para pedir los datos del jugador
			boolean encontrado = false;
			while (!salir3) {
				int numero = obtenerEntradaEntero("Introduce el número del jugador que ha participado en el evento: ");
				System.out.println(
						"Ahora hay que introducir el equipo del jugador, tiene que ser uno de la siguiente lista: ");
				for (Equipo equipo : Equipo.values()) {
					System.out.println(equipo.name());
				}
				while (!salir2) {
					try {
						equipoString = recibirEntradaString("Introduce el equipo del jugador: ");
						equipoString = equipoString.toUpperCase();
						equipoEnumGuardado = Equipo.valueOf(equipoString);
						salir2 = true;
					} catch (Exception e) {
						System.out.println(
								"Has introducido una opción inválida, introduce una opción de la lista anterior");
					}
				}
				salir2 = false;

				for (Jugador jugador : jugadores) {
					if (numero == jugador.getNumeroJugador() && equipoEnumGuardado == jugador.getEquipo()) {
						jugadorGuardado = jugador;
						encontrado = true;
						salir3 = true;
					}
				}
				if (!encontrado) {
					System.out.println(
							"Ese jugador no existe, introduce los datos de un jugador que existe en la base de datos");
				} else {
					if (encontrado) {
						System.out.println("Se ha encontrado el jugador " + jugadorGuardado.getNombre() + " con número "
								+ jugadorGuardado.getNumeroJugador() + " del equipo "
								+ jugadorGuardado.getEquipo().name());
					} else {
						System.out.println("Ese jugador no está en la base de datos");
					}
				}
			}
			System.out.println("Ahora hay que introducir el tipo de evento a registrar, las opciones son: ");
			for (EventoEnum ee : EventoEnum.values()) {
				System.out.println(ee.name());
			}
			salir2 = false;
			EventoEnum eventoEnumGuardado = null;
			String eventoEnum;
			while (!salir2) {
				try {
					eventoEnum = recibirEntradaString("Introduce el tipo evento que quieres registrar: ");
					eventoEnum = eventoEnum.toUpperCase();
					eventoEnumGuardado = EventoEnum.valueOf(eventoEnum);
					salir2 = true;
				} catch (Exception e) {
					System.out
							.println("Has introducido una opción inválida, introduce una opción de la lista anterior");
				}
			}
			salir2 = false;
			int minuto = obtenerEntradaEntero("Introduce el minuto en el que ocurrió el evento: ");
			// Aquí miramos que no haya ningún error con la creación del objeto jugador, si
			// es null, algo fue mal y no se añade el evento
			if (jugadorGuardado != null) {
				if (eventoEnumGuardado.equals(EventoEnum.GOL)) {
					jugadorGuardado.setNumeroGoles(jugadorGuardado.getNumeroGoles() + 1);
					System.out.println("El evento se ha añadido correctamente");
				}
				eventos.add(new Evento(jugadorGuardado, minuto, eventoEnumGuardado));
			} else {
				System.out.println("No se ha encontrado un jugador con ese número y equipo en la base de datos");
			}

			salir3 = false;
			encontrado = false;

		}

	}

	// Este método mostrará los jugadores que hay en la colección

	@Override
	public void mostrarJugadores() {
		if (jugadores.isEmpty()) {
			System.out.println("La base de datos de jugadores está vacía");
		} else {
			for (Jugador jugador : jugadores) {
				System.out.println("Datos del jugador: ");
				jugador.mostrarDatos();
			}
		}

	}

	// Este método mostrará los eventos que hay en la colección

	@Override
	public void mostrarEventos() {
		if (eventos.isEmpty()) {
			System.out.println("La base de datos de eventos está vacía");
		} else {
			// Aquí organizamos los objetos de tipo evento según su atributo minuto
			Collections.sort(eventos);
			System.out.println("Eventos del partido: ");
			for (Evento evento : eventos) {
				evento.mostrarDatos();
			}
		}
	}

	// Este método eliminará los jugadores que hay en la colección (1 o todos)

	@Override
	public void eliminarJugadores() {
		if (jugadores.isEmpty()) {
			System.out.println("La base de datos de jugadores está vacía");
		} else {
			boolean salir3 = false;
			while (!salir3) {
				int opcion = obtenerEntradaEntero(
						"Introduce una opción: \n1. Eliminar un jugador por número y equipo \n2. Eliminar todos los jugadores \n3. Salir");
				if (opcion == 1) {
					if (jugadores.isEmpty()) {
						System.out.println("La base de datos de jugadores está vacía");
					} else {
						boolean salir2 = false;
						String equipoString;
						Equipo equipoEnumGuardado = null;
						boolean encontrado = false;
						int numero = obtenerEntradaEntero("Introduce el número del jugador que quieres eliminar: ");
						System.out.println(
								"Ahora hay que introducir el equipo del jugador que quieres eliminar, tiene que ser uno de la siguiente lista: ");
						for (Equipo equipo : Equipo.values()) {
							System.out.println(equipo.name());
						}
						while (!salir2) {
							try {
								equipoString = recibirEntradaString("Introduce el equipo del jugador: ");
								equipoString = equipoString.toUpperCase();
								equipoEnumGuardado = Equipo.valueOf(equipoString);
								salir2 = true;
							} catch (Exception e) {
								System.out.println(
										"Has introducido una opción inválida, introduce una opción de la lista anterior");
							}
						}
						salir2 = false;
						Iterator<Jugador> it = jugadores.iterator();
						while (it.hasNext()) {
							Jugador jugador = it.next();
							if (numero == jugador.getNumeroJugador() && equipoEnumGuardado == jugador.getEquipo()) {
								it.remove();
								encontrado = true;
							}
						}
						if (!encontrado) {
							System.out.println(
									"Ese jugador no existe, introduce los datos de un jugador que existe en la base de datos");
						} else {
							if (encontrado) {
								System.out.println("Se ha eliminado el jugador de la base de datos");
							} else {
								System.out.println("Ese jugador no está en la base de datos");
							}
						}
						encontrado = false;
					}

				} else if (opcion == 2) {
					Iterator<Jugador> it = jugadores.iterator();
					while (it.hasNext()) {
						Jugador jugador = it.next();
						it.remove();
					}
					System.out.println("Se han eliminado todos los jugadores de la base de datos");
				} else if (opcion == 3) {
					System.out.println("Has salido del menú");
					salir3 = true;
				} else {
					System.out.println("Tienes que introducir una opción válida (1-3)");
				}
			}
			salir3 = false;
		}

	}

	// Este método eliminará los eventos que hay en la colección (1 o todos)

	@Override
	public void eliminarEventos() {
		if (eventos.isEmpty()) {
			System.out.println("La base de datos de eventos está vacía");
		} else {
			boolean salir3 = false;
			while (!salir3) {
				int opcion = obtenerEntradaEntero(
						"Introduce una opción: \n1. Eliminar un evento introduciendo el número y equipo del jugador, el minuto y el tipo de evento \n2. Eliminar todos los eventos \n3. Salir");
				if (opcion == 1) {
					boolean salir2 = false;
					int numero = obtenerEntradaEntero("Introduce el número del jugador que quieres eliminar: ");
					boolean encontrado = false;
					String equipoString;
					Equipo equipoEnumGuardado = null;
					System.out.println(
							"Ahora hay que introducir el equipo del jugador que quieres eliminar, tiene que ser uno de la siguiente lista: ");
					for (Equipo equipo : Equipo.values()) {
						System.out.println(equipo.name());
					}
					while (!salir2) {
						try {
							equipoString = recibirEntradaString("Introduce el equipo del jugador: ");
							equipoString = equipoString.toUpperCase();
							equipoEnumGuardado = Equipo.valueOf(equipoString);
							salir2 = true;
						} catch (Exception e) {
							System.out.println(
									"Has introducido una opción inválida, introduce una opción de la lista anterior");
						}
					}
					salir2 = false;
					int minuto = obtenerEntradaEntero("Introduce el minuto en el que ocurrió el evento: ");
					EventoEnum eventoEnumGuardado = null;
					String eventoEnum;
					while (!salir2) {
						try {
							eventoEnum = recibirEntradaString("Introduce el tipo evento que quieres registrar: ");
							eventoEnum = eventoEnum.toUpperCase();
							eventoEnumGuardado = EventoEnum.valueOf(eventoEnum);
							salir2 = true;
						} catch (Exception e) {
							System.out.println(
									"Has introducido una opción inválida, introduce una opción de la lista anterior");
						}
					}
					salir2 = false;
					Iterator<Evento> it = eventos.iterator();
					while (it.hasNext()) {
						Evento evento = it.next();
						if (evento.getMinuto() == minuto && evento.getEvento() == eventoEnumGuardado
								&& evento.getJugador().getNumeroJugador() == numero
								&& evento.getJugador().getEquipo() == equipoEnumGuardado) {
							// Este if verifica si el evento eliminado es un gol, si es así, se restará un
							// gol del atributo del jugador que había participado en el evento
							if (evento.getEvento() == EventoEnum.GOL) {
								for (Jugador jugador : jugadores) {
									if (numero == jugador.getNumeroJugador()
											&& equipoEnumGuardado == jugador.getEquipo()) {
										jugador.setNumeroGoles(jugador.getNumeroGoles() - 1);
									}
								}
							}
							it.remove();
							encontrado = true;
						}
					}
					if (encontrado) {
						System.out.println("El evento se eliminó correctamente de la base de datos");
					} else {
						System.out.println("El evento introducido no existe");
					}
					encontrado = false;
				} else if (opcion == 2) {
					Iterator<Evento> it = eventos.iterator();
					while (it.hasNext()) {
						Evento evento = it.next();
						it.remove();
					}
					System.out.println("Se han eliminado todos los eventos de la base de datos");
				} else if (opcion == 3) {
					System.out.println("Has salido del menú");
					salir3 = true;
				} else {
					System.out.println("Tienes que introducir una opción válida (1-3)");
				}
			}
			salir3 = false;
		}
	}

	// Este método cargará los datos guardados en el archivo partido.dat

	@Override
	public void cargarArchivo(String ruta) {
		File f = new File(ruta);
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ruta);
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					Partido partido = (Partido) ois.readObject();
					this.setJugadores(partido.getJugadores());
					this.setEventos(partido.getEventos());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				ois.close();
				fis.close();
				System.out.println("Se ha encontrado un archivo con los datos de un partido");
				System.out.println("");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Este método guardará los datos del partido en el archivo partido.dat

	@Override
	public void guardarArchivo(Partido partido, String ruta) {
		try {
			FileOutputStream fos = new FileOutputStream(ruta);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(partido);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Archivo corrupto");
			e.printStackTrace();
		}
		System.out.println("Se han guardado los datos del partido al archivo " + ruta);
	}

}
