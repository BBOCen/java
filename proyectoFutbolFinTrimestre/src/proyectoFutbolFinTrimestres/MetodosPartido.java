package proyectoFutbolFinTrimestres;

public interface MetodosPartido {
	void agregarJugador();

	void registrarEvento();

	void mostrarJugadores();

	void mostrarEventos();

	void eliminarJugadores();

	void eliminarEventos();

	void cargarArchivo(String ruta);

	void guardarArchivo(Partido partido, String ruta);
}
