package ejercicioGalleta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bote implements Serializable {
	List<Galleta> galletas = new ArrayList<>();

    public void añadirGalleta(Galleta galleta) {
        galletas.add(galleta);
    }

    public void comerGalleta() {
        if (!galletas.isEmpty()) {
            System.out.println("Has comido una galleta de tipo: " + galletas.remove(0).tipo);
        } else {
            System.out.println("No hay más galletas en el bote.");
        }
    }

    public void mostrarGalletas() {
        if (galletas.isEmpty()) {
            System.out.println("El bote está vacío, no hay galletas.");
        } else {
            for (Galleta g : galletas) {
                System.out.println(g);
            }
        }
    }
}
