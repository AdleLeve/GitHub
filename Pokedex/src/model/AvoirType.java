package model;

/**
 * Classe Avoir_Type
 */
public class AvoirType {
	// Declaration Attributs
	private Pokemon pokemon;
	private Type type;
	
	/**
	 * Constructeur Plein de la classe AvoirType
	 * @param lPkm
	 * @param t
	 */
	public AvoirType(Pokemon pkm, Type t) {
		this.pokemon = pkm;
		this.type = t;
	}
	
	/**
	 * Constructeur Vide de la classe AvoirType
	 */
	public AvoirType() {
		this.pokemon = null;
		this.type = null;
	}

	/**
	 * Renvois l'Objet Pokemon correspondant à la correspondance d'Avoir_Type cible
	 * @return Pokemon
	 */
	public Pokemon getPkmAvoirType() {
		return pokemon;
	}
	
	/**
	 * Insert ou Modifie l'Objet Pokemon correspondant à la correspondance d'Avoir_Type cible
	 * @param pkm Pokemon
	 */
	public void setPkmAvoirType(Pokemon pkm) {
		this.pokemon = pkm;
	}
	
	/**
	 * Renvois l'Objet Type correspondant à la correspondance d'Avoir_Type cible
	 * @return Type
	 */
	public Type getTypeAvoirType() {
		return type;
	}
	
	/**
	 * Insert ou Modifie l'Objet Type correspondant à la correspondance d'Avoir_Type cible
	 * @param t Type
	 */
	public void setTypeAvoirType(Type t) {
		this.type = t;
	}
}
