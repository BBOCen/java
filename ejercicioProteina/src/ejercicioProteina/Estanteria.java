package ejercicioProteina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Estanteria implements MetodosEstanteria, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Proteina> proteinas;
	private ArrayList<Creatina> creatinas;

	public Estanteria() {
		proteinas = new ArrayList<Proteina>();
		creatinas = new ArrayList<Creatina>();
	}

	public ArrayList<Proteina> getProteinas() {
		return proteinas;
	}

	public void setProteinas(ArrayList<Proteina> proteinas) {
		this.proteinas = proteinas;
	}

	public ArrayList<Creatina> getCreatinas() {
		return creatinas;
	}

	public void setCreatinas(ArrayList<Creatina> creatinas) {
		this.creatinas = creatinas;
	}
	
	//ArrayList con las marcas
	
	ArrayList<String> marcas=new ArrayList<String>();

	// Scanner para los siguientes métodos

	Scanner sc = new Scanner(System.in);

	// Métodos estantería
	
	//Este método se activa al encender el programa
	
	public void anadirMarcas() {
		String fileName = "Suplementos.txt";
		
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                marcas.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo \"marcas.txt\"");
        }
	}

	@Override
	public void tomarCreatina() {
		System.out.println("Introduce el nombre de la creatina que quieres tomar: ");
		String nombreCreatina = sc.next();
		int numeroCucharadas = 0;
		boolean encontrado=false;
		for (Creatina c : creatinas) {
			if (c.getNombre().equals(nombreCreatina)) {
				c.setCucharadas(c.getCucharadas() - 1);
				numeroCucharadas = c.getCucharadas();
				encontrado=true;
			}
		}
		if (encontrado) {
			System.out.println("Te has tomado una cucharada de la creatina " + nombreCreatina + ", ahora te quedan "
					+ numeroCucharadas + " cucharadas");
		}
		else {
			System.out.println("No se ha encontrado ese nombre");
		}
	}

	@Override
	public void tomarProteina() {
		System.out.println("Introduce el nombre de la proteína que quieres tomar: ");
		String nombreProteina = sc.next();
		int numeroCucharadas = 0;
		boolean encontrado=false;
		for (Proteina p : proteinas) {
			if (p.getNombre().equals(nombreProteina)) {
				p.setCucharadas(p.getCucharadas() - 1);
				numeroCucharadas = p.getCucharadas();
				encontrado=true;
			}
		}
		if (encontrado) {
			System.out.println("Te has tomado una cucharada de la proteína " + nombreProteina + ", ahora te quedan "
					+ numeroCucharadas + " cucharadas");
		}
		else {
			System.out.println("No se ha encontrado ese nombre");
		}
		
	}

	@Override
	public void comprarProteina() {
		System.out.println("Vas a comprar una proteína nueva:");
		System.out.println("Introduce el nombre de la proteína: ");
		String nombre=sc.next();
		System.out.println("Introduce el número de cucharadas de la proteína: ");
		int cucharadas=sc.nextInt();
		System.out.println("Introduce la marca, puede ser: ");
		for (String s: marcas) {
			System.out.println(s);
		}
		String marca=sc.next();
		while(!marcas.contains(marca)) {
			System.out.println("Introduce una marca de lista");
			marca=sc.next();
		}
		System.out.println("Introduce el tipo de proteína, puede ser: ");
		for (TipoProteina tp:TipoProteina.values()) {
			System.out.println(tp);
		}
		String tipoProteinaString=sc.next();
		TipoProteina tp=TipoProteina.valueOf(tipoProteinaString);
		proteinas.add(new Proteina(nombre, cucharadas, marca, tp));
	}

	@Override
	public void comprarCreatina() {
		System.out.println("Vas a comprar una creatina nueva:");
		System.out.println("Introduce el nombre de la creatina: ");
		String nombre=sc.next();
		System.out.println("Introduce el número de cucharadas de la creatina: ");
		int cucharadas=sc.nextInt();
		
		System.out.println("Introduce la marca, puede ser: ");
		for (String s: marcas) {
			System.out.println(s);
		}
		String marca=sc.next();
		while(!marcas.contains(marca)) {
			System.out.println("Introduce una marca de lista");
			marca=sc.next();
		}
		creatinas.add(new Creatina(nombre, cucharadas, marca));
	}

}
