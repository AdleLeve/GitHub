package model;

/**
 * Classe Pokemon
 */
public class Pokemon {
	// Declaration Attributs
	private String num_pokedex_mondial;
	private String nom_pokemon;
	private String description_pokedex_pokemon;
	private Boolean particularite_legendaire_pkm;
	private Images image;
	private Generation generation;
	
	/**
	 * Constructeur Plein de la classe Pokemon
	 * @param num Chaine
	 * @param nom Chaine
	 * @param desc Entier
	 * @param plpkm Caractere
	 * @param img Images
	 * @param gen Generation
	 */
	public Pokemon(String num, String nom, String desc, 
			Boolean plpkm, Images img, Generation gen) {
		this.num_pokedex_mondial = num;
		this.nom_pokemon = nom;
		this.description_pokedex_pokemon = desc;
		this.particularite_legendaire_pkm = plpkm;
		this.image = img;
		this.generation = gen;
	}

	/**
	 * Constructeur Vide de la classe Pokemon
	 */
	public Pokemon() {
		this.num_pokedex_mondial = null;
		this.nom_pokemon = null;
		this.description_pokedex_pokemon = null;
		this.particularite_legendaire_pkm = null;
		this.image = null;
		this.generation = null;
	}

	/**
	 * Renvoie le numero dans le pokedex mondial de l'objet Pokemon cible
	 * @return Chaine
	 */
	public String getNumPokedexMondialPkm () {
		return this.num_pokedex_mondial;
	}

	/**
	 * Modifie le numero dans le pokedex mondial de l'objet Pokemon cible
	 * @param num Chaine
	 */
	public void setNumPokedexMondialPkm (String num) {
		this.num_pokedex_mondial = num;
	}
	
	/**
	 * Renvoie le nom de l'objet Pokemon cible
	 * @return Chaine
	 */
	public String getNomPokemon () {
		return this.nom_pokemon;
	}
	
	/**
	 * Modifie le nom de l'objet Pokemon cible
	 * @param nom Chaine
	 */
	public void setNomPokemon (String nom) {
		this.nom_pokemon = nom;
	}
	
	/**
	 * Renvoie la Chaine representant la description de l'objet Pokemon cible
	 * @return String
	 */
	public String getDescPokemon () {
		return this.description_pokedex_pokemon;
	}
	
	/**
	 * Modifie la Chaine representant la description de l'objet Pokemon cible
	 * @param desc Chaine
	 */
	public void setDescPokemon (String desc) {
		this.description_pokedex_pokemon = desc;
	}
	
	/**
	 * Renvoie le Boolean representant si l'objet Pokemon cible est vrai => "Legendaire" ou faux => "Normal"
	 * @return boolean
	 */
	public Boolean getParticulariteLegendairePokemon () {
		return this.particularite_legendaire_pkm;
	}
	
	/**
	 * Modifie le Boolean representant si l'objet Pokemon cible est vrai => "Legendaire" ou faux => "Normal"
	 * @param plpkm boolean
	 */
	public void setParticulariteLegendairePokemon (Boolean plpkm) {
		this.particularite_legendaire_pkm = plpkm;
	}
	
	/**
	 * Renvoie l'Objet Images assigne a l'objet Pokemon cible
	 * @return Images
	 */
	public Images getImagesPokemon () {
		return this.image;
	}
	
	/**
	 * Modifie l'Objet Images assigne a l'objet Pokemon cible
	 * @param img Images
	 */
	public void setImagesPokemon (Images img) {
		this.image = img;
	}

	/**
	 * Renvoie l'Objet Generation assigne a l'objet Pokemon cible
	 * @return Generation
	 */
	public Generation getGenerationPkm() {
		return generation;
	}

	/**
	 * Modifie l'Objet Generation assigne a l'objet Pokemon cible
	 * @param generation
	 */
	public void setGenerationPkm(Generation generation) {
		this.generation = generation;
	}
}
