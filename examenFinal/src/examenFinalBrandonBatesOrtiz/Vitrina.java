package examenFinalBrandonBatesOrtiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Vitrina implements Serializable {
	private ArrayList<Cruasanes> cruasanes;

	// En el constructor he añadido unas líneas de código que verifican si existe el
	// archivo "cruasanes.dat", si no existe, añade el cruasán que viene por
	// defecto, si no, no lo añade

	public Vitrina() {
		cruasanes = new ArrayList<Cruasanes>();
		File f = new File("cruasanes.dat");
		if (!f.exists()) {
			cruasanes.add(new Cruasanes(MarcaEnum.BIMBO, "CHOCOLATE", 300));
		}
	}

	public ArrayList<Cruasanes> getCruasanes() {
		return cruasanes;
	}

	public void setCruasanes(ArrayList<Cruasanes> cruasanes) {
		this.cruasanes = cruasanes;
	}

	// Estos métodos sirven para recibir la entrada del scanner, además, al estar
	// como métodos, no hay que volver a escribir los try catch varias veces

	public int scannerEntero(String mensaje) {
		Scanner sc = new Scanner(System.in);
		int entrada = 0;
		boolean salir = false;
		while (!salir) {
			try {
				System.out.println(mensaje);
				entrada = sc.nextInt();
				salir = true;
			} catch (Exception e) {
				System.out.println("No has introducido un número entero válido");
				sc.next();
			}
		}
		salir = false;
		return entrada;
	}

	public String scannerCadena(String mensaje) {
		Scanner sc = new Scanner(System.in);
		String entrada = "";
		boolean salir = false;
		while (!salir) {
			try {
				System.out.println(mensaje);
				entrada = sc.nextLine();
				salir = true;
			} catch (Exception e) {
				System.out.println("No has introducido un número entero válido");
				sc.next();
			}
		}
		salir = false;
		return entrada;
	}

	// Estos métodos guardan y cargan los archivos

	public void cargarArchivo(String ruta) {
		File f = new File(ruta);
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ruta);
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					Vitrina vitrina = (Vitrina) ois.readObject();
					this.setCruasanes(vitrina.getCruasanes());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				ois.close();
				fis.close();
				System.out.println("Se ha encontrado un archivo con los datos de una vitrina");
				System.out.println("");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void guardarArchivo(Vitrina vitrina, String ruta) {
		try {
			FileOutputStream fos = new FileOutputStream(ruta);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(vitrina);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("El archivo corrupto");
			e.printStackTrace();
		}
		System.out.println("Se han guardado los datos del programa en el siguiente archivo: " + ruta);
	}

	// Este método lee los sabores del archivo "sabor.txt" y los añade a un array
	// list

	public ArrayList<String> leerSabores() {
		String ruta = "sabor.txt";
		ArrayList<String> sabores = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			while (linea != null) {
				linea = br.readLine();
				sabores.add(linea);
			}
			// Si el array list tiene algún elemento que sea null, se eliminará del mismo
			// Para eliminar ese elemento, utilizo un iterator, si utlizara un for, me daría
			// error, ya que no se pueden eliminar elementos de una lista con un for
			Iterator<String> it = sabores.iterator();
			while (it.hasNext()) {
				String sabor = it.next();
				if (!(sabor == null)) {

				} else {
					it.remove();
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
		return sabores;
	}

	// Estos métodos son los relacionados con la funcionalidad del programa

	// Este método sirve para comprar el cruasan (añade un cruasan a la lista)

	public void comprarCruasan() {
		int calorias = 0;
		while (calorias <= 0) {
			calorias = scannerEntero(
					"Introduce las calorías del cruasán (tiene que ser un número entero mayor que 0): ");
			if (calorias <= 0) {
				System.out.println(
						"Has introducido una cantidad inválida de calorías, introduce un número entero positivo distinto a 0");
			}
		}

		MarcaEnum marca = null;
		String marcaString;
		boolean salir = false;

		while (!salir) {
			try {
				for (MarcaEnum marcas : MarcaEnum.values()) {
					System.out.println(marcas.name());
				}
				marcaString = scannerCadena("Introduce una marca de la lista anterior: ").toUpperCase();
				marca = MarcaEnum.valueOf(marcaString);
				salir = true;
			} catch (Exception e) {
				System.out.println("Introduce una marca válida de la lista anterior");
			}
		}
		String sabor = "";
		salir = false;
		ArrayList<String> sabores = leerSabores();
		while (!salir) {
			System.out.println("Ahora hay que introducir un sabor de la siguiente lista: ");
			for (String saborString : sabores) {
				System.out.println(saborString);
			}
			sabor = scannerCadena(
					"Introduce el sabor deseado (si pones uno que no sale en la lista, el programa volverá a pedirte que introduzcas el sabor): ")
					.toUpperCase();
			if (sabores.contains(sabor)) {
				salir = true;
			}
		}
		salir = false;
		System.out.println("Has comprado el cruasán con éxito");
		cruasanes.add(new Cruasanes(marca, sabor, calorias));
	}

	// Este método sirve para comer un cruasán (elimina un cruasán de la lista)

	public void comerCruasan(Persona persona1) {
		if (cruasanes.isEmpty()) {
			System.out.println("No hay cruasanes en la vitrina");
		} else {
			System.out.println("Te puedes comer un cruasán de la siguiente lista: ");
			for (Cruasanes cruasan : cruasanes) {
				cruasan.mostrarDatos();
			}
			// He decidido que el cruasán se comerá en función del sabor, calorías y marca
			// (si coinciden todos los datos, se eliminará el cruasán de la lista)
			int calorias = 0;
			while (calorias <= 0) {
				calorias = scannerEntero(
						"Introduce las calorías del cruasán (tiene que ser un número entero mayor que 0): ");
			}

			MarcaEnum marca = null;
			String marcaString;
			boolean salir = false;

			while (!salir) {
				try {
					for (MarcaEnum marcas : MarcaEnum.values()) {
						System.out.println(marcas.name());
					}
					marcaString = scannerCadena("Introduce una marca de la lista anterior: ").toUpperCase();
					marca = MarcaEnum.valueOf(marcaString);
					salir = true;
				} catch (Exception e) {
					System.out.println("Introduce una marca válida de la lista anterior");
				}
			}
			String sabor = "";
			salir = false;
			ArrayList<String> sabores = leerSabores();
			while (!salir) {
				System.out.println("Ahora hay que introducir un sabor de la siguiente lista: ");
				for (String saborString : sabores) {
					System.out.println(saborString);
				}
				sabor = scannerCadena(
						"Introduce el sabor deseado (si pones uno que no sale en la lista, el programa volverá a pedirte que introduzcas el sabor): ")
						.toUpperCase();
				if (sabores.contains(sabor)) {
					salir = true;
				}
			}
			salir = false;
			boolean encontrado = false;
			// Para comernos el cruasan utilizo un iterator, si utlizara un for me daría
			// error, ya que no se pueden eliminar elementos de una lista con un for
			// Por otro lado, si hay dos cruasanes iguales, se elimina el primero (solo se
			// consume 1)
			Iterator<Cruasanes> it = cruasanes.iterator();
			while (it.hasNext()) {
				Cruasanes cruasan = it.next();
				if (cruasan.getCalorias() == calorias && cruasan.getMarcaEnum().equals(marca)
						&& cruasan.getSabor().equals(sabor)) {
					encontrado = true;
					it.remove();
					break;
				}
			}
			if (encontrado) {
				cruasanes.remove(new Cruasanes(marca, sabor, calorias));
				System.out.println("Te has comido el cruasán");
				persona1.setCaloriasConsumidas(persona1.getCaloriasConsumidas() + calorias);
				persona1.setCruasanesComidos(persona1.getCruasanesComidos() + 1);
				persona1.mostrarDatos();
			} else {
				System.out.println("No se ha encontrado un cruasán con los atributos que has introducido");
			}
			encontrado = false;
		}
	}

	// Este método sirve para mostrar todos los cruasanes de la vitrina

	public void mostrarCruasanes() {
		if (cruasanes.isEmpty()) {
			System.out.println("No hay cruasanes en la vitrina");
		} else {
			System.out.println("Los cruasanes que hay en la vitrina son los siguientes: ");
			for (Cruasanes cruasan : cruasanes) {
				cruasan.mostrarDatos();
			}
		}
	}

	// Este método mostrará los cruasanes filtrados por el sabor

	public void mostrarCruasanesFiltrados() {
		if (cruasanes.isEmpty()) {
			System.out.println("No hay cruasanes en la vitrina");
		} else {
			String sabor = "";
			boolean salir = false;
			ArrayList<String> sabores = leerSabores();
			while (!salir) {
				System.out.println("Vas a buscar los cruasanes según su sabor, esta es la lista de sabores: ");
				for (String saborString : sabores) {
					System.out.println(saborString);
				}
				sabor = scannerCadena(
						"Introduce el sabor deseado (si pones uno que no sale en la lista, el programa volverá a pedirte que introduzcas el sabor): ")
						.toUpperCase();
				if (sabores.contains(sabor)) {
					salir = true;
				}
			}
			boolean encontrado = false;
			for (Cruasanes cruasan : cruasanes) {
				if (cruasan.getSabor().equals(sabor)) {
					cruasan.mostrarDatos();
					encontrado = true;
				}
			}
			if (!encontrado) {
				System.out.println("No quedan cruasanes del sabor " + sabor);
			}
		}
	}
}
