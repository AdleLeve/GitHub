package controller;

import java.util.ArrayList;

// Import de touts les models car on les manipuleras tous ici 
import model.*;

/**
 * @author Adèle
 * Interface DataList permettant de stocker les listes des differents Objets
 */
public interface DataList {
	public static ArrayList<Annee> anneeListe = new ArrayList<Annee>();
	public static ArrayList<Generation> generationListe = new ArrayList<Generation>();
	public static ArrayList<Type> typeListe = new ArrayList<Type>();
	public static ArrayList<Images> imagesListe = new ArrayList<Images>();
	public static ArrayList<Pokemon> pkmListe = new ArrayList<Pokemon>();
	public static ArrayList<AvoirType> avoirTypeListe = new ArrayList<AvoirType>();
	public static ArrayList<TypeEvolution> typeEvolListe = new ArrayList<TypeEvolution>();
	public static ArrayList<Evolution> evolutionListe = new ArrayList<Evolution>();
}
