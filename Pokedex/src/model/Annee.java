package model;

/**
 * Classe Annee
 */
public class Annee {
	// Declaration attributs
	private String numero_annee;
	
	/**
	 * Constructeur de la classe Annee vide
	 * @param num Chaine
	 */
	public Annee() {
		this.numero_annee = null;
	}
	
	/**
	 * Constructeur de la classe Annee
	 * @param num Chaine
	 */
	public Annee(String num) {
		this.numero_annee = num;
	}
	
	/**
	 * Permet de retourner la chaine representant l'année selectionnee
	 * @return Chaine
	 */
	public String getNumAnnee () {
		return this.numero_annee;
	}

	/**
	 * Permet de rentrer et ou modifier le numero de l'annee selectionnee
	 * @param num
	 */
	public void setNumAnnee (String num) {
		this.numero_annee = num;
	}
}
