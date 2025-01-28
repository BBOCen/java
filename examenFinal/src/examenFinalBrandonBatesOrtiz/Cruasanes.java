package examenFinalBrandonBatesOrtiz;

public class Cruasanes extends Pasteles {

	public Cruasanes(MarcaEnum marcaEnum, String sabor, int calorias) {
		super(marcaEnum, sabor, calorias);
	}

	public void mostrarDatos() {
		System.out.println("************Datos del cruasán************");
		System.out.println("Marca: " + getMarcaEnum().name());
		System.out.println("Sabor: " + getSabor());
		System.out.println("Calorias: " + getCalorias());
		System.out.println("");
	}

}
