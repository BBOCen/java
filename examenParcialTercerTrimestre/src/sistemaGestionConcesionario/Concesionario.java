package sistemaGestionConcesionario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Concesionario {
	private TreeSet<Coche> coches;
	private HashSet<Trabajador> trabajadores;
	private static double ganancias;

	public Concesionario() {
		coches = new TreeSet<Coche>();
		trabajadores = new HashSet<Trabajador>();
	}

	public TreeSet<Coche> getCoches() {
		return coches;
	}

	public void setCoches(TreeSet<Coche> coches) {
		this.coches = coches;
	}

	public HashSet<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(HashSet<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public static double getGanancias() {
		return ganancias;
	}

	public void setGanancias(double ganancias) {
		Concesionario.ganancias = ganancias;
	}

	// Scanner para los metodos de coche y trabajadores

	Scanner sc = new Scanner(System.in);

	// Este método añade unos objetos ya creados al pulsar la opción correspondiente
	// en el menú

	public void anadirObjetosHechos() {
		Trabajador trabajador1 = new Trabajador("Juan Pérez", 10000, 1);

		Trabajador trabajador2 = new Trabajador("Manolo Sánchez", 20000, 2);

		Coche coche1 = new Coche(1, Marca.HONDA, true, 15000);

		Coche coche2 = new Coche(2, Marca.AUDI, false, 25000);

		trabajadores.add(trabajador1);
		trabajadores.add(trabajador2);

		coches.add(coche1);
		coches.add(coche2);

		System.out.println("Se han añadido a la base de datos los hechos que ya estaban hechos");
	}

	// Métodos de coche
	public void anadirCoche() {
		int matricula = 0;
		String marcaString = null, electricoString;
		Marca marca = null;
		boolean electrico = false;
		double precio = 0;
		// Esta variable controla el bucle con el try catch siguiente, lo usaré carias
		// veces para que el mensaje de que se ha introducido un valor inválida vuelva a
		// aparecer
		boolean salir2 = false;
		while (!salir2) {
			try {
				System.out.println(
						"Introduce la matricula del coche (si es la misma que un vehículo ya existente en la base de datos, no se añadirá): ");
				matricula = sc.nextInt();
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Has introducido un número inválido, vuelve a intentarlo");
				sc.next();
			}
		}
		salir2 = false;
		System.out.println("Las marcas posibles de los coches son: ");
		for (Marca marca2 : Marca.values()) {
			System.out.println(marca2.name());
		}
		while (!salir2) {
			try {
				System.out.println("Introduce la marca del coche: ");
				marcaString = sc.next().toUpperCase();
				marca = Marca.valueOf(marcaString);
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Has introducido una marca inválida, introduce una marca de la lista anterior");
			}
		}
		salir2 = false;
		while (!salir2) {
			System.out.println("¿El coche es eléctrico? Por favor responde solo con si (sin tilde) o no: ");
			electricoString = sc.next().toUpperCase();
			switch (electricoString) {
			case "SI":
				electrico = true;
				salir2 = true;
				break;
			case "NO":
				electrico = false;
				salir2 = true;
			default:
				System.out.println("Introduce solo si (sin tilde) o no");
			}
		}
		salir2 = false;
		while (!salir2) {
			try {
				System.out.println("Introduce el precio del coche: ");
				precio = sc.nextDouble();
				salir2 = true;
			} catch (Exception e) {
				System.out.println("Has introducido un número inválido, vuelve a intentarlo");
				sc.next();
			}
		}

		coches.add(new Coche(matricula, marca, electrico, precio));
	}

	public void mostrarCoches() {
		int contador = 1;
		String esElectrico;
		if (coches.isEmpty()) {
			System.out.println("La base de datos de trabajadores está vacía");
		} else {
			for (Coche coche : coches) {
				System.out.println("Datos del coche " + contador);
				System.out.println("Matrícula: " + coche.getMatricula());
				System.out.println("Marca: " + coche.getMarca());
				if (coche.isElectrico()) {
					esElectrico = "Sí";
				} else {
					esElectrico = "No";
				}
				System.out.println("¿Es eléctrico? " + esElectrico);
				System.out.println("Precio: " + coche.getPrecio());
				System.out.println("");
				contador++;
			}
		}
	}

	public void venderCoche() {
		boolean salir2=false;
		int matricula=0;
		while(!salir2) {
			try {
				System.out.println("Indica la matrícula del coche que quieres vender: ");
				matricula = sc.nextInt();
				salir2=true;
			}
			catch(Exception e) {
				System.out.println("Introduce un número válido");
				sc.next();
			}
		}
		
		double precioCoche = 0;
		boolean encontrado = false;
		Iterator<Coche> it = coches.iterator();
		while (it.hasNext()) {
			Coche coche = it.next();
			if (coche.getMatricula() == matricula) {
				encontrado = true;
				precioCoche = coche.getPrecio();
				it.remove();
			}
		}
		ganancias += (0.5 * precioCoche);
		if (encontrado) {
			System.out.println("El coche con matrícula se ha vendido por " + precioCoche
					+ " las ganancias de esta venta han sido " + 0.5 * precioCoche);
		} else {
			System.out.println("No hay un coche con esa matrícula en el base de datos");
		}
	}

	// Métodos de trabajador
	// Aquí declaro el número de trabajador que empieza por 2, ya que en los 2
	// objetos ya hechos, la ID del último trabajador es 2
	int nTrabajador = 2;

	public void anadirTrabajador() {
		String nombre=null;
		double salario=0;
		boolean salir2=false;
		while(!salir2) {
			try {
				System.out.println("Introduce el nombre del trabajador: ");
				nombre = sc.next();
				salir2=true;
			}
			catch (Exception e) {
				System.out.println("Introduce un nombre válido");
				sc.next();
			}
		}
		salir2=false;
		while(!salir2) {
			try {
				System.out.println("Introduce el salario del trabajador: ");
				salario = sc.nextDouble();
				salir2=true;
			}
			catch (Exception e)  {
				System.out.println("Introduce un número válido");
				sc.next();
			}
		}
		
		nTrabajador++;
		trabajadores.add(new Trabajador(nombre, salario, nTrabajador));
	}

	public void buscarTrabajador() {
		boolean salir2=false;
		while(!salir2) {
			try {
				System.out.println("Introduce el número del trabajador: ");
				nTrabajador = sc.nextInt();
				salir2=true;
			}
			catch(Exception e) {
				System.out.println("Introduce un número válido");
				sc.next();
			}
		}
		String nombre = null;
		boolean encontrado = false;
		double salario = 0;
		
		for (Trabajador trabajador : trabajadores) {
			if (trabajador.getnTrabajador() == nTrabajador) {
				nombre = trabajador.getNombre();
				salario = trabajador.getSalario();
				encontrado = true;
				break;
			}
		}
		if (encontrado) {
			System.out.println("Se ha encontrado un trabajador con ese número de identificación. Sus datos son: ");
			System.out.println("Número de identificación del trabajador: " + nTrabajador);
			System.out.println("Nombre: " + nombre);
			System.out.println("Salario: " + salario);
		}
	}

	public void mostrarTrabajadores() {
		int contador = 1;
		if (trabajadores.isEmpty()) {
			System.out.println("La base de datos de trabajadores está vacía");
		} else {
			for (Trabajador trabajador : trabajadores) {
				System.out.println("Datos del trabajador " + contador);
				System.out.println("Número de identificación del trabajador: " + trabajador.getnTrabajador());
				System.out.println("Nombre: " + trabajador.getNombre());
				System.out.println("Salario: " + trabajador.getSalario());
				System.out.println("");
				contador++;
			}
		}

	}
}
