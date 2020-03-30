package model;

/**
 * Classe Generation
 */
public class Generation {
	// Declaration attributs
	private int numero; // on laisse cet id car pas dans l'auto-incrementation de la BDD
	private String libelle; 
	private Annee annee;
	
	/**
	 * Constructeur Plein classe Generation
	 * @param num
	 * @param lib
	 * @param lAnnee
	 */
	public Generation(int num, String lib, Annee lAnnee) {
		this.numero = num;
		this.libelle = lib;
		this.annee = lAnnee;
	}
	
	/**
	 * Constructeur Vide classe Generation
	 */
	public Generation() {
		this.numero = 0; // On met 0 car un objet s'il n'est pas complet ne sera pas enregistre en memoire 
		this.libelle = null;
		this.annee = null;
	}
	
	/**
	 * Renvoie un Entier representant le numero de Generation de l'objet selectionne
	 * @return Entier
	 */
	public int getNumGeneration () {
		return this.numero;
	}
	
	/**
	 * definis ou redefinis le numero de l'objet Generation selectionne
	 * @param num Entier
	 */
	public void set_num_genration (int num) {
		this.numero = num;
	}
	
	/**
	 * Renvoie une Chaine representant le libelle de la Generation de l'objet Generation selectionne
	 * @return Chaine
	 */
	public String get_lib_generation () {
		return this.libelle;
	}
	
	/**
	 * definis ou redefinis le libelle de l'objet Generation selectionne
	 * @param lib Chaine
	 */
	public void set_lib_generation (String lib) {
		this.libelle = lib;
	}
	
	/**
	 * Renvoie un objet Annnee representant l'annee de la Generation de l'objet Generation selectionne
	 * @return Annee
	 */
	public Annee get_annee_generation () {
		return this.annee;
	}
		
	/**
	 * Definit ou redefinit un objet Annnee representant l'annee de la Generation de l'objet Generation selectionne
	 * @param lAnnee Annee
	 */
	public void set_annee_generation (Annee lAnnee) {
		this.annee = lAnnee;
	}
}
