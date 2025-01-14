package proyectoFutbolFinTrimestres;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Comparable<Jugador>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int numeroJugador;
	private int numeroGoles;
	private Equipo equipo;
	private Posicion posicion;

	public Jugador(String nombre, int numeroJugador, int numeroGoles, Equipo equipo, Posicion posicion) {
		super();
		this.nombre = nombre;
		this.numeroJugador = numeroJugador;
		this.numeroGoles = numeroGoles;
		this.equipo = equipo;
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroJugador() {
		return numeroJugador;
	}

	public void setNumeroJugador(int numeroJugador) {
		this.numeroJugador = numeroJugador;
	}

	public int getNumeroGoles() {
		return numeroGoles;
	}

	public void setNumeroGoles(int numeroGoles) {
		this.numeroGoles = numeroGoles;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void mostrarDatos() {
		// Como el equipo es un enum, se pondrá como un string que varía según el valor
		// del enum, determinado por la siguiente condicional:
		String equipoString = fijarEquipoString(equipo);
		System.out.println("Nombre: " + nombre);
		System.out.println("Número de jugador: " + numeroJugador);
		System.out.println("Número de goles: " + numeroGoles);
		System.out.println("Equipo: " + equipoString);
		System.out.println("Posicion: " + posicion);
		System.out.println("");
	}

	// Esta función fija el valor del String de equipo en función del valor del enum
	public String fijarEquipoString(Equipo equipo) {
		String equipoString = "";
		if (equipo.equals(Equipo.BARCELONA)) {
			equipoString = "Barcelona";
		} else if (equipo.equals(Equipo.CHELSEA)) {
			equipoString = "Chelsea";
		} else if (equipo.equals(Equipo.MANCHESTER_UNITED)) {
			equipoString = "Machester United";
		} else if (equipo.equals(Equipo.MANCHESTER_CITY)) {
			equipoString = "Machester City";
		}
		return equipoString;
	}

	// Este código hace que dos objetos sean iguales si coinciden su equipo y número
	// de jugador, al almacenar estos objetos de tipo jugador en un TreeSet si un
	// objeto coincide con estos datos, no se añadirá
	@Override
	public int hashCode() {
		return Objects.hash(equipo, numeroJugador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return equipo == other.equipo && numeroJugador == other.numeroJugador;
	}

	// Este código hace que los jugadores se organicen por su número de camiseta
	public int compareTo(Jugador o) {
		return numeroJugador - o.numeroJugador;
	}

}
