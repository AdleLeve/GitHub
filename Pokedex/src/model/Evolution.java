package model;

/**
 * Classe Evolution
 */
public class Evolution {
	// Declaration Attributs
	private int idEvolution;
	private String libelleEvolution;
	private Pokemon pkmSousEvolution;
	private Pokemon pkmSurEvolution;
	private TypeEvolution typeEvolution;
	
	
	/**
	 * Constructeur Plein de la classe Evolution
	 * @param libelle
	 * @param sousEvolution
	 * @param surEvolution
	 * @param type
	 */
	public Evolution(int id, String libelle, Pokemon sousEvolution,
			Pokemon surEvolution, TypeEvolution type) {
		this.idEvolution = id;
		this.libelleEvolution = libelle;
		this.pkmSousEvolution = sousEvolution;
		this.pkmSurEvolution = surEvolution;
		this.typeEvolution = type;
	}

	/**
	 * Constructeur Vide de la classe Evolution
	 */
	public Evolution() {
		this.libelleEvolution = null;
		this.pkmSousEvolution = null;
		this.pkmSurEvolution = null;
		this.typeEvolution = null;
	}
	
	
	public int getIdEvolution() {
		return idEvolution;
	}
	
	
	public void setIdEvolution(int id) {
		this.idEvolution = id;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getLibelleEvolution() {
		return libelleEvolution;
	}
	
	/**
	 * 
	 * @param lib
	 */
	public void setLibelleEvolution(String lib) {
		this.libelleEvolution = lib;
	}


	public TypeEvolution getTypeEvolution() {
		return typeEvolution;
	}


	public void setTypeEvolution(TypeEvolution type) {
		this.typeEvolution = type;
	}


	public Pokemon getPkmSousEvolution() {
		return pkmSousEvolution;
	}


	public void setPkmSousEvolution(Pokemon sousEvolution) {
		this.pkmSousEvolution = sousEvolution;
	}


	public Pokemon getPkmSurEvolution() {
		return pkmSurEvolution;
	}


	public void setPkmSurEvolution(Pokemon surEvolution) {
		this.pkmSurEvolution = surEvolution;
	}
}
