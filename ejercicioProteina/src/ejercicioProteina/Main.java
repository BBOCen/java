package ejercicioProteina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Estanteria estanteria = new Estanteria();
		boolean salir = false;
		boolean anadirMarcas=false;
		Scanner sc = new Scanner(System.in);
		// Leer el archivo suplementos.dat si existe:
		String ruta = "./suplementos.dat";
		File f = new File(ruta);
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ruta);
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					estanteria = (Estanteria) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ois.close();
				fis.close();
				System.out.println("Se ha encontrado una estantería con suplementos");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		while (!salir)

		{
			if (!anadirMarcas) {
				estanteria.anadirMarcas();
				anadirMarcas=true;
			}
			
			System.out.println(
					"Introduce una opción: \n1. Tomar proteína \n2. Tomar creatina \n3. Comprar proteína \n4. Comprar creatina \n5. Salir");
			String opcion = sc.next();
			switch (opcion) {
			case "1":
				estanteria.tomarProteina();
				break;
			case "2":
				estanteria.tomarCreatina();
				break;
			case "3":
				estanteria.comprarProteina();
				break;
			case "4":
				estanteria.comprarCreatina();
				break;
			case "5":
				System.out.println("Has salido del programa");
				salir = true;
			default:
				System.out.println("Introduce una opción válida");
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(ruta);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(estanteria);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("corrupto");
			e.printStackTrace();
		}
		System.out.println("Has guardado los suplementos en el armario");
	}

}
