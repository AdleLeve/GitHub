package model;

/**
 * Classe Images
 */
public class Images implements ImagesInterface {
	// Declarations Attributs
	private int id;
	private String url;
	private String extension;
	
	/**
	 * Constructeur Complet de la classe Images
	 * @param lId Entier
	 * @param ext Chaine
	 * @param lUrl Chaine
	 */
	public Images(int lId, String lUrl, String ext) {
		this.id = lId;
		this.url = lUrl;
		this.extension = ext;
	}
	
	/**
	 * Constructeur Vide de la classe Images
	 */
	public Images() {
		this.id = 0; // On met 0 car un objet s'il n'est pas complet ne sera pas enregistre en memoire 
		this.url = null;
		this.extension = null;
	}

	/**
	 * Retourne l'id de la classe Images
	 * @return Entier
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifie ou Insert l'Entier id de l'Image 
	 * @param id Entier
	 */
	public void setId(int lId) {
		this.id = lId;
	}

	/**
	 * Retourne l'extension de la classe Images
	 * @return Chaine
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Modifie ou Insert la chaine extension de l'Image
	 * @param ext Chaine
	 */
	public void setExtension(String ext) {
		this.extension = ext;
	}

	/**
	 * Retourne l'Url de la classe Images
	 * @return Chaine
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Modifie ou Insert la chaine url de l'Image
	 * @param lUrl Chaine
	 */
	public void setUrl(String lUrl) {
		this.url = lUrl;
	}
	
	/**
	 * Retourne la chaine representant l'Url Complete selectionnee de la classe Image
	 * @return Chaine
	 */
	public String getUrlComplete () {
		return this.url + this.extension;
	}
}
