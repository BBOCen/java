package ejercicioProteina;

public class Proteina extends Suplemento {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoProteina tipoProteina;

	public Proteina(String nombre, int cucharadas, String marca, TipoProteina tipoProteina) {
		super(nombre, cucharadas, marca);
		this.tipoProteina = tipoProteina;
	}

	public TipoProteina getTipoProteina() {
		return tipoProteina;
	}

	public void setTipoProteina(TipoProteina tipoProteina) {
		this.tipoProteina = tipoProteina;
	}

}
