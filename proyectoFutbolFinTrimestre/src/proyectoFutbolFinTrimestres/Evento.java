package proyectoFutbolFinTrimestres;

import java.io.Serializable;

public class Evento implements Serializable, Comparable<Evento> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jugador jugador;
	private int minuto;
	private EventoEnum evento;

	public Evento(Jugador jugador, int minuto, EventoEnum evento) {
		super();
		this.jugador = jugador;
		this.minuto = minuto;
		this.evento = evento;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public EventoEnum getEvento() {
		return evento;
	}

	public void setEvento(EventoEnum evento) {
		this.evento = evento;
	}

	public void mostrarDatos() {
		// Al igual que con jugador, se utiliza una función que cambiará el valor del
		// String del enum de evento, dependiendo del valor del enum
		String eventoString = fijarEventoString(evento);
		System.out.println("Minuto: " + minuto);
		System.out.println("Nombre del jugador que participó en el evento: " + jugador.getNombre());
		System.out.println("Evento: " + eventoString);
		System.out.println("");
	}

	// Esta función fija el valor del String de evento en función del valor del enum
	public String fijarEventoString(EventoEnum evento) {
		String eventoString = "";
		if (evento.equals(EventoEnum.GOL)) {
			eventoString = "Gol";
		} else if (evento.equals(EventoEnum.TARJETA_AMARILLA)) {
			eventoString = "Tarjeta Amarilla";
		} else if (evento.equals(EventoEnum.TARJETA_ROJA)) {
			eventoString = "Tarjeta Roja";
		}
		return eventoString;
	}

	// Este código hace que un objeto se compare mediante el minuto en el que
	// ocurrió, así se mostrará por orden cronológico cuando se muestren los eventos
	public int compareTo(Evento o) {
		return minuto - o.minuto;
	}
}
