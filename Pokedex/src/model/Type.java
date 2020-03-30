package model;

/**
 * Classe Type
 */
public class Type {
	// Declaration atributs
	private int idType;
	private String libelle;
	private Generation generation;
	
	/**
	 * Constructeur Plein de la classe Type
	 * @param id 
	 * @param lib
	 * @param lGen
	 */
	public Type(int id, String lib, Generation lGen) {
		this.idType = id;
		this.libelle = lib;
		this.generation = lGen;
	}
	
	/**
	 * Constructeur Vide de la classe Type
	 */
	public Type() {
		this.idType = 0;
		this.libelle = null;
		this.generation = null;
	}
	
	public int getIdType() {
		return idType;
	}

	public void setIdType(int id) {
		this.idType = id;
	}

	/**
	 * Permet de rentrer et ou modifier une chaine contenant le libelle du Type selectionne
	 * @param lib
	 */
	public void setLibType (String lib) {
		this.libelle = lib;
	}
	
	/**
	 * Renvoie une chaine contenant le libelle du Type selectionne
	 * @param lib
	 */
	public String getLibType () {
		return this.libelle;
	}
	
	/**
	 * Permet de rentrer et ou modifier l'objet Generation representant la Generation d'apparition du Type selectionne
	 * @param lib
	 */
	public void setGenType (Generation gen) {
		this.generation = gen;
	}
	
	/**
	 * Renvoie l'objet Generation representant la Generation d'apparition du Type selectionne
	 * @param lib
	 */
	public Generation getGenType () {
		return this.generation; 
	}

}
