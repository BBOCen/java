package ejercicioGalleta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Bote bote = new Bote();
		// Si existe este archivo se añadirá al arraylist, sino se mostrará una
		// excepción de que no existe el archivo
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tipos_galletas.txt"))) {
		    bote = (Bote) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("Menú:");
			System.out.println("1. Añadir galleta");
			System.out.println("2. Comer galleta");
			System.out.println("3. Mostrar galletas");
			System.out.println("4. Salir");

			System.out.print("Escoge una opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				System.out.print("Introduce el tipo de galleta (Normales, Fibra, Chocolate): ");
				String tipoGalleta = sc.next();
				if (tipoGalleta.equals("Normales") || tipoGalleta.equals("Fibra") || tipoGalleta.equals("Chocolate")) {
					bote.añadirGalleta(new Galleta(tipoGalleta));
				} else {
					System.out.println("Tipo de galleta no válido.");
				}
				break;
			case 2:
				bote.comerGalleta();
				break;
			case 3:
				bote.mostrarGalletas();
				break;
			case 4:
				break;
			default:
				System.out.println("Opción no válida, usa una opción válida (1-4)");
			}
		} while (opcion != 4);
		// Al finalizar el programa se guarda el archivo en un .txt
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tipos_galletas.txt"))) {
			oos.writeObject(bote);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
