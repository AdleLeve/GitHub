package model;

/**
 * Classe TypeEvolution
 */
public class TypeEvolution {
	// Declaration Attributs
	private Integer idTypeEvol;
	private String libelleTypeEvol;
	
	/**
	 * Constructeur Plein de la classe TypeEvolution
	 * @param id Integer
	 * @param lib String
	 */
	public TypeEvolution (Integer id, String lib) {
		this.idTypeEvol = id;
		this.libelleTypeEvol = lib;
	}
	
	/**
	 * Constructeur Vide de la classe TypeEvolution
	 */
	public TypeEvolution () {
		this.idTypeEvol = 0;
		this.libelleTypeEvol = null;
	}

	/**
	 * Renvois un Entier representant l'id du Type d'Evolution selectionne
	 * @return Integer
	 */
	public Integer getIdTypeEvol() {
		return idTypeEvol;
	}

	/**
	 * Modifie l'Entier idTypeEvol representant l'id du Type d'Evolution selectionne
	 * @param idTypeEvol
	 */
	public void setIdTypeEvol(Integer idTypeEvol) {
		this.idTypeEvol = idTypeEvol;
	}

	/**
	 * Renvois la Chaine du libelle du Type d'Evolution selectionnee
	 * @return Chaine
	 */
	public String getLibelleTypeEvol() {
		return libelleTypeEvol;
	}

	/**
	 * Modifie ou Insert la Chaine du libelle du Type d'Evolution selectionnee
	 * @param libelleTypeEvol Entier
	 */
	public void setLibelleTypeEvol(String libelleTypeEvol) {
		this.libelleTypeEvol = libelleTypeEvol;
	}
}
