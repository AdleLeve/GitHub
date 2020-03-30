package controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import model.*;

/**
 * @author Adèle
 * Classe Outils recueillant les differentes methodes et procedures reutilisables 
 * necessaire au bon fonctionnement du programme
 */
public class Outils {
	
	/* Outils crees pour le bon fonctionnement du reste */
	/**
	 * Methode testant si une Chaine passee en parametre est un Entier renvoi 
	 * un Booleen notifiant si oui ou non la Chaine est un Entier
	 * @param machaine Chaine
	 * @return Booleen
	 */
	public static Boolean isInteger (String machaine) {
		Boolean isInteger;
		
		try {
			int i = Integer.parseInt(machaine);
			System.out.println("La Chaine : " + i + " est un entier");
			isInteger = true;
		}
		catch (Exception e) {
			isInteger = false;
			System.out.println("La Chaine : " + machaine + " n'est pas un entier");
		} 
		
		return isInteger;
	}
	
	/**
	 * Methode permettant de renvoyer la valeur Booleenne representant l'Entier (Bit) passer en parametre
	 * @param bit Entier
	 * @return Booleen
	 */
	public static Boolean passBitABool (Integer bit) {
		if (bit == 1)  {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Methode permettant de renvoyer la valeur Entiere (Bit) representant le Booleen passer en parametre
	 * @param bool Booleen
	 * @return Entier
	 */
	public static Integer passBoolABit (Boolean bool) {
		if (bool == true)  {
			return 1;
		} else {
			return 0;
		}
	}
	
	/* Methodes renvoyants a la classe Annee */
	/**
	 * Methode pemettant de creer et inserer un Objet Annee a la liste DataList.anneeListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param numAnnee
	 * @return
	 */
	public static Annee ajoutAnneeListe (String numAnnee) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet Annee
		Annee ann = new Annee();
		
		// Test de la longueur de la chaine et de la possibilite de la transformer en Entier
		if(numAnnee.length() == 4 && isInteger(numAnnee) == true) {
			estConforme = true;
			System.out.println("Le format de l'annee " + numAnnee + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si l'annee est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Annee a: DataList.anneeListe) { 
				System.out.println("a.getNumAnnee() = " + a.getNumAnnee() + " / numAnnee = " + numAnnee);
				if (Integer.parseInt(a.getNumAnnee()) ==  Integer.parseInt(numAnnee)) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja une Annee " + numAnnee);
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format de l'annee " + numAnnee + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de l'annee
		if (estConforme == true) {
			// Instanciation de la variable annee
			ann = new Annee(numAnnee);
			
			if (estDouble == false) {
				// ajout de cette variable a la liste anneeListe de l'interface DataList s'il n'est pas deja present dans la liste
				DataList.anneeListe.add(ann);
				
				// insertion dans la base de donnees
				insertionDtbAnnee(ann);
			}
			
		}

		System.out.println("**** affichage de la valeur d'annee : " + (DataList.anneeListe.size()) + " ****");
				
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.anneeListe.size()) + " ****");
		
		System.out.println("\n\n");
		return ann;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Annee passe en parametre et de l'inserer dans la base de donnees
	 * @param annee
	 */
	private static void insertionDtbAnnee (Annee annee) {
		System.out.println("**** affichage du num de l'annee : " + annee.getNumAnnee() + " ****");
		
		if (annee.getNumAnnee() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				// execution de la procedure stockee
//				statement.executeUpdate("CALL PROC_Insert_annee ('" + annee.getNumAnnee() + "');");
				String requete = "INSERT INTO Annee (numero_annee) VALUES ('" + annee.getNumAnnee() + "');";
				
//				statement.executeUpdate("CALL PROC_Insert_annee ('" + annee.getNumAnnee() + "');");
				statement.executeUpdate(requete);
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertAnnee ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur numero_annee de l'objet annee est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Annee de la BDD dans la liste de type Annee passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbAnnee (ArrayList<Annee> listeAnnee) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeAnnee
		listeAnnee.removeAll(listeAnnee);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
//			resultat = statement.executeQuery( "CALL PROC_Select_Annee_ordreA ();" );
			String requete = "Select numero_annee as num From Annee Order By num;";
			resultat = statement.executeQuery( requete );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				String numAnnee = resultat.getString("num");
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet Annee contenant les valeurs recuperees 
				Annee annee = new Annee(numAnnee);
				System.out.println("Numero annee recupDtbAnnee : " + annee.getNumAnnee());
				
				// Ajout de l'annee a la liste listeAnnee
				listeAnnee.add(annee);
			}
			System.out.println("Nb Annee dans listeAnnee : " + listeAnnee.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectAnnee ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}
	
	/* Methodes renvoyants a la classe Generation */
	/**
	 * Methode pemettant de creer et inserer un Objet Annee a la liste DataList.anneeListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param numAnnee
	 * @return
	 */
	public static Generation ajoutGenerationListe (int numGen, Annee anneeGen) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet Generation
		Generation gen = new Generation();
		
		// Test pour voir si numGen est compris entre 1 et 99
		if(numGen > 0 && numGen < 100) {
			estConforme = true;
			System.out.println("Le format de la generation " + numGen + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si la generation est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Generation g: DataList.generationListe) { 
				System.out.println("g.get_num_generation() = " + g.getNumGeneration());
				if (g.getNumGeneration() ==  numGen) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja une Generation " + numGen);
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format de la Generation " + numGen + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de l'annee
		if (estConforme == true) {
			String libGen = numGen + "G";
			System.out.println("le libelle de Generation est : " + libGen);
			
			// Instanciation de la variable Generation
			gen = new Generation(numGen, libGen, anneeGen);
			
			if (estDouble == false) {
				// ajout de cette variable a la liste generationListe de l'interface DataList s'il n'est pas deja present dans la liste
				DataList.generationListe.add(gen);
				
				// insertion de la Genertation non double dans la base de donnees
				insertionDtbGeneration(gen);
			}
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.generationListe.size()) + " ****");
		
		System.out.println("\n\n");
		return gen;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Generation passe en parametre et de l'inserer dans la base de donnees
	 * @param annee
	 */
	private static void insertionDtbGeneration (Generation gen) {
		System.out.println("**** affichage du lib de la Generation : " + gen.get_lib_generation() + " ****");
		
		if (gen.get_lib_generation() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				
				// execution de la procedure stockee
				statement.executeUpdate("CALL PROC_Insert_Generation (" + gen.getNumGeneration() + ", '" + 
				gen.get_lib_generation() + "', '" + gen.get_annee_generation().getNumAnnee() + "')");
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertGeneration ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur libelle de l'objet Generation est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Generation de la BDD dans la liste de type Generation passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbGeneration (ArrayList<Generation> listeGeneration) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeGeneration
		listeGeneration.removeAll(listeGeneration);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Generation_ordreA ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				int numGen = resultat.getInt("num");
				String libGen = resultat.getString("lib");
				int numAnnee = resultat.getInt("annee");
				Annee annee = new Annee();
				
				for (Annee a: DataList.anneeListe) {
					if (Integer.parseInt(a.getNumAnnee()) == numAnnee) {
						annee = a;
						break;
					}
				}
				
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet Annee contenant les valeurs recuperees 
				Generation gen = new Generation(numGen, libGen, annee);
				System.out.println("Numero Generation recupDtbGeneration : " + gen.getNumGeneration());
				
				// Ajout de l'annee a la liste listeAnnee
				listeGeneration.add(gen);
			}
			System.out.println("Nb Generation dans listeGeneration : " + listeGeneration.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectGeneration ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}

	/* Methodes renvoyants a la classe Type */
	/**
	 * Methode pemettant de creer et inserer un Objet type a la liste DataList.typeListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param numAnnee
	 * @return
	 */
	public static Type ajoutTypeListe (String libType, Generation genType) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet Type
		Type type = new Type();
		
		// Test de longueur de libType
		if(libType.length() <= 15) {
			estConforme = true;
			System.out.println("Le format de la longueur " + libType + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si la generation est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Type t: DataList.typeListe) {
				System.out.println("t.getIdType() = " + t.getIdType());
				if (t.getLibType().toUpperCase().equals(libType.toUpperCase())) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja un Type " + libType.toUpperCase());
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format du Type " + libType + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de l'annee
		if (estConforme == true) {
			ArrayList<Integer> lstId = new ArrayList<Integer>();
			
			// On ajoute touts les id de la liste typeListe a lstId
			for (Type tp: DataList.typeListe) {
				lstId.add(tp.getIdType());
			}
			
			int idType;
			
			// On selectionne l'id max de la liste, on y ajoute 1 et on l'insere dans idType
			try {
				idType = Collections.max(lstId) + 1;
			} catch(Exception e) {
				idType = 1;
			}
			
			// Si les tests sont conforme on fait l'ajout du Type
			if (estConforme == true) {
				// Instanciation de la variable type
				type = new Type(idType, libType.toUpperCase(), genType);
				
				if (estDouble == false) {
					// ajout de cette variable a la liste typeListe de l'interface DataList s'il n'est pas présent dans la liste
					DataList.typeListe.add(type);
					
					// insertion du Type dans la base de donnees
					insertionDtbType(type);
				}	
			}
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.typeListe.size()) + " ****");
		
		System.out.println("\n\n");
		return type;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Type passe en parametre et de l'inserer dans la base de donnees
	 * @param annee
	 */
	private static void insertionDtbType (Type type) {
		System.out.println("**** affichage du lib du Type : " + type.getLibType() + " ****");
		
		if (type.getLibType() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				// execution de la procedure stockee
				statement.executeUpdate("CALL PROC_Insert_Type (" + type.getIdType() + ", '" + 
				type.getLibType() + "', " + type.getGenType().getNumGeneration() + ")");
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertType ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur libelle de l'objet Type est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Generation de la BDD dans la liste de type Generation passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbType (ArrayList<Type> listeType) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeGeneration
		listeType.removeAll(listeType);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Type_ordreACode ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				int codeType = resultat.getInt("code");
				String libType = resultat.getString("lib");
				int gen = resultat.getInt("gen");
				Generation genType = new Generation();
				
				for (Generation g: DataList.generationListe) {
					if (g.getNumGeneration() == gen) {
						genType = g;
						break;
					}
				}
				
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet Annee contenant les valeurs recuperees 
				Type type = new Type(codeType, libType, genType);
				System.out.println("Numero Type recupDtbType : " + type.getIdType());
				
				// Ajout du Type a la liste listeType
				listeType.add(type);
			}
			System.out.println("Nb Type dans listeType : " + listeType.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectGeneration ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}

	/* Methodes renvoyants a la classe Images */
	/**
	 * Methode pemettant de creer et inserer un Objet Images a la liste DataList.imagesListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param extImg String
	 * @param urlImg String
	 * @return Type
	 */
	public static Images ajoutImageListe (String extImg, String urlImg) {
		Boolean estConforme;
		Boolean estDouble = false;
		
		// Declaration de l'objet Images
		Images image = new Images();
		
		// Test de longueur de extImg et urlImg
		if(extImg.length() <= 5 && urlImg.length() <= 50) {
			estConforme = true;
			
			System.out.println("Le format de la longueur " + extImg + " & " + urlImg + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si l'Image est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Images i: DataList.imagesListe) {
				System.out.println("i.getUrl() = " + i.getUrl());
				if ((i.getUrl() + i.getExtension()).equals(urlImg + extImg)) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja une Image " + urlImg + extImg);
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format de l'Image " + urlImg + extImg + " est invalide " + estConforme.toString());
		}
		// declaration d'un Entier idImg
		int idImg;
		
		// Si les tests sont conforme on fait l'ajout de l'annee
		if (estConforme == true) {
		
			if (DataList.imagesListe.size() > 0) { // Si la taille de DataList.imageListe est supperieur a 0
				// Declaration et instanciation d'une ArrayList clesId de type Entier 
				ArrayList<Integer> clesId = new ArrayList<>();
				
				// Pour chaque Images de imagesListe
				for (Images i: DataList.imagesListe) {
					clesId.add(i.getId());
				}
				// recuperation de l'id le plus eleve auxquel on ajoute 1 pour creer un nouveau id
				idImg = Collections.max(clesId) + 1;
			}
			else {
				// Sinon on assigne 1 a idImg
				idImg = 1;
			}
			
			// Si les tests sont conforme on fait l'ajout de l'Image
			if (estConforme == true) {
				// Instanciation de la variable type
				image = new Images(idImg, urlImg, extImg);
				
				if (estDouble == false) {
					// ajout de cette variable a la liste imagesListe de l'interface DataList
					DataList.imagesListe.add(image);
					
					// Insertion de l'image dans la base de donnees 
					insertionDtbImage(image);
				}	
			}
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.typeListe.size()) + " ****");
		
		System.out.println("\n\n");
		return image;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Images passe en parametre et de l'inserer dans la base de donnees
	 * @param annee
	 */
	private static void insertionDtbImage (Images image) {
		System.out.println("**** affichage de l'Url de l'image : " + image.getUrl() + image.getExtension() + " ****");
		
		if (image.getUrl() != null && image.getExtension() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				// execution de la procedure stockee
				statement.executeUpdate("CALL PROC_Insert_Image (" + image.getId() + ", '" + image.getUrl() + "', '" + image.getExtension() + "')");
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertType ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur libelle de l'objet Type est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Images de la BDD dans la liste de type Images passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbImages (ArrayList<Images> listeImages) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeImages
		listeImages.removeAll(listeImages);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Image_ordreAUrl ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				int idImg = resultat.getInt("id");
				String urlImg = resultat.getString("url");
				String extImg = resultat.getString("ext");
								
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet Annee contenant les valeurs recuperees 
				Images image = new Images(idImg, urlImg, extImg);
				System.out.println("Id Images recupDtbImage : " + image.getId());
				
				// Ajout du Type a la liste listeType
				listeImages.add(image);
			}
			System.out.println("Nb Images dans listeImages : " + listeImages.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectImages ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}

	/* Methodes renvoyants a la classe Pokemon */
	/**
	 * Methode pemettant de creer et inserer un Objet Pokemon a la liste DataList.pkmListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param numPkdx String
	 * @param nomPkm String
	 * @param descPkm String
	 * @param imagePkm Images
	 * @param legendairePkm Boolean
	 * @param genAppar Generation
	 * @return Pokemon
	 */
	public static Pokemon ajoutPkmListe (String numPkdx, String nomPkm, String descPkm,
			Images imagePkm, Boolean legendairePkm, Generation genAppar) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet Pokemon
		Pokemon pkm = new Pokemon();
		
		// Test des longueur et des non nullite des parametres
		if(numPkdx.length() == 3 && nomPkm.length() <= 20 && descPkm.length() <= 400 &&
				numPkdx != null && nomPkm != null && descPkm != null && imagePkm != null
				&& legendairePkm != null && genAppar != null) {
			estConforme = true;
			
			System.out.println("Le format des parametres du Pokemon " + numPkdx + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si le Pokemon est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Pokemon p: DataList.pkmListe) {
				System.out.println("p.get_num_pokedex_mondial_pkm() = " + p.getNumPokedexMondialPkm());
				if (p.getNumPokedexMondialPkm().equals(numPkdx) || p.getNomPokemon().equals(nomPkm)) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja un pokemon " + numPkdx + " Ou " + nomPkm);
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format du Pokemon " + numPkdx + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout du Pokemon
		if (estConforme == true) {
			// Instanciation de la variable Pokemon
			pkm = new Pokemon(numPkdx, nomPkm, descPkm, legendairePkm, imagePkm, genAppar);
			
			if (estDouble == false) {
				// ajout de cette variable a la liste pkmListe de l'interface DataList
				DataList.pkmListe.add(pkm);
				
				// Insertion du Pokemon dans la base de donnees
				insertionDtbPokemon(pkm);
			}	
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.pkmListe.size()) + " ****");
		
		System.out.println("\n\n");
		return pkm;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Pokemon passe en parametre et de l'inserer dans la base de donnees
	 * @param pkm
	 */
	private static void insertionDtbPokemon (Pokemon pkm) {
		System.out.println("**** affichage du numero du Pokemon selectionne : " + pkm.getNumPokedexMondialPkm() + " ****");
		
		if (pkm.getNumPokedexMondialPkm() != null && pkm.getNomPokemon() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				
				// creation et instanciation de la chaine de la requete
				String requete = "CALL PROC_Insert_Pkm ('" + pkm.getNumPokedexMondialPkm() + "', '" + pkm.getNomPokemon() + "', '" + pkm.getDescPokemon() 
						+ "', " + pkm.getImagesPokemon().getId() + ", " + passBoolABit(pkm.getParticulariteLegendairePokemon()) + ", " 
						+ pkm.getGenerationPkm().getNumGeneration() + ")";
				
				System.out.println("Insertion reussie de : " + pkm.getNumPokedexMondialPkm() + ", " + pkm.getNomPokemon() + ", " + pkm.getDescPokemon() 
				+ ", " + pkm.getImagesPokemon().getId() + ", " + pkm.getParticulariteLegendairePokemon().toString() + ", " + pkm.getGenerationPkm().getNumGeneration());
				
				// execution de la procedure stockee
				statement.executeUpdate(requete);
				
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertType ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur libelle de l'objet Pokemon est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Images de la BDD dans la liste de type Images passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbPokemon (ArrayList<Pokemon> listePokemon) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeImages
		listePokemon.removeAll(listePokemon);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Pkm_OrdrePkdx ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				String numPkm = resultat.getString("num");
				String nomPkm = resultat.getString("nom");
				String descPkm = resultat.getString("descr");
				Integer intImagePkm = resultat.getInt("url");
				Integer bitLegendPkm = resultat.getInt("legendaire");
				Integer intGenPkm = resultat.getInt("gen");
				
				Boolean legendPkm = passBitABool(bitLegendPkm);
				
				// Declaration Objets pour la recuperations des Objets via les id 
				Images imgPkm = null;
				Generation genPkm = null;
				
				// Recuperation de l'Objet Images affillie au Pokemon via l'id
				for (Images i: DataList.imagesListe) {
					if (i.getId() == intImagePkm) {
						imgPkm = i;
						break;
					}
				}

				// Recuperation de l'Objet Generation affillie au Pokemon via l'id
				for (Generation g: DataList.generationListe) {
					if (g.getNumGeneration() == intGenPkm) {
						genPkm = g;
						break;
					}
				}
				
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet Pokemon contenant les valeurs recuperees 
				Pokemon pkm = new Pokemon(numPkm, nomPkm, descPkm, legendPkm, imgPkm, genPkm);
				System.out.println("Id Pokemon recupDtbPkm : " + pkm.getNumPokedexMondialPkm());
				
				// Ajout du Pokemon a la liste listeType
				listePokemon.add(pkm);
			}
			System.out.println("Nb pokemon dans listePokemon : " + listePokemon.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectImages ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}

	/* Methodes renvoyants a la classe AvoirType */
	/**
	 * Methode pemettant de creer et inserer un Objet AvoirType a la liste DataList.avoirTypeListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param numPkdx String
	 * @param nomPkm String
	 * @param descPkm String
	 * @param imagePkm Images
	 * @param legendairePkm Boolean
	 * @param genAppar Generation
	 * @return Pokemon
	 */
	public static AvoirType ajoutPkmAvoirType (Pokemon pkm, Type type) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet AvoirType vide
		AvoirType avTp = new AvoirType();
		
		// Test de non nullite des parametres
		if(pkm.getNomPokemon() != null && type.getLibType() != null) {
			estConforme = true;
			
			System.out.println("Le format des parametres de la relation AvoirType " + pkm.getNumPokedexMondialPkm() + " / "+ pkm.getNomPokemon() 
			+ " & " + type.getIdType() + " / " + type.getLibType() + " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si l'Image est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (AvoirType aT : DataList.avoirTypeListe) {
				System.out.println("NumPkdx / NomPkm == " +
					aT.getPkmAvoirType().getNumPokedexMondialPkm() + " / " + aT.getPkmAvoirType().getNomPokemon() +
					" & CodeType / LibType == " + 
					aT.getTypeAvoirType().getIdType() + " / " + aT.getTypeAvoirType().getLibType());
				if (aT.getPkmAvoirType().getNumPokedexMondialPkm().equals(pkm.getNumPokedexMondialPkm()) 
						&& aT.getTypeAvoirType().getLibType().equals(type.getLibType())) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja une relation entre " + aT.getPkmAvoirType().getNumPokedexMondialPkm() + " ET " + aT.getTypeAvoirType().getLibType());
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format des parametres de la relation AvoirType " + pkm.getNumPokedexMondialPkm() + " / "+ 
			pkm.getNomPokemon() + " & " + type.getIdType() + " / " + type.getLibType() + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de la relation AvoirType
		if (estConforme == true) {
			// Instanciation de la variable AvoirType
			avTp = new AvoirType(pkm, type);
			
			if (estDouble == false) {
				// ajout de cette variable a la liste avoirTypeListe de l'interface DataList
				DataList.avoirTypeListe.add(avTp);
				
				// Insertion de la relation AvoirType a la Base de Donnees
				insertionDtbAvoirType(avTp);
			}	
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (DataList.avoirTypeListe.size()) + " ****");
		
		System.out.println("\n\n");
		return avTp;
	}
	
	/**
	 * Methode permettant d'inserer un Objet AvoirType passe en parametre et de l'inserer dans la base de donnees
	 * @param at AvoirType
	 */
	private static void insertionDtbAvoirType (AvoirType at) {
		System.out.println("**** affichage du numPkm et du libType de la relation : " + at.getPkmAvoirType().getNumPokedexMondialPkm() + " / " 
				+ at.getTypeAvoirType().getLibType() + " ****");
		
		if (at.getPkmAvoirType().getNumPokedexMondialPkm() != null && at.getTypeAvoirType().getLibType() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				
				// creation et instanciation de la chaine de la requete
				String requete = "CALL PROC_Insert_Avoir_Type ('" + at.getPkmAvoirType().getNumPokedexMondialPkm() + "', " 
						+ at.getTypeAvoirType().getIdType() + ")";
				
				// execution de la procedure stockee
				statement.executeUpdate(requete);
				
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertAvoirType ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur l'objet AvoirType est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table AvoirType de la BDD dans la liste de type Images passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbAvoirType (ArrayList<AvoirType> listeAT) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de la liste
		listeAT.removeAll(listeAT);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Avoir_Type_ordAPkm ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				String numPkm = resultat.getString("num");
				Integer codeType = resultat.getInt("code");
				
				// Declaration Objets pour la recuperations des Objets via les id 
				Pokemon pkm = null;
				Type tp = null;
				
				// Recuperation de l'Objet Pokemon affillie au AvoirType via l'id
				for (Pokemon p: DataList.pkmListe) {
					if (p.getNumPokedexMondialPkm().equals(numPkm)) {
						pkm = p;
						break;
					}
				}

				// Recuperation de l'Objet Type affillie AvoirType via l'id
				for (Type t: DataList.typeListe) {
					if (t.getIdType() == codeType) {
						tp = t;
						break;
					}
				}
				
				/*---** Traiter ici les valeurs recuperees **--*/
				// Instanciation d'un objet AvoirType contenant les valeurs recuperees 
				AvoirType at = new AvoirType(pkm, tp);
				
				// Ajout de la relation AvoirType a la liste listeAT
				listeAT.add(at);
			}
			System.out.println("Nb relation AvoirType dans listeAT : " + listeAT.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectImages ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}

	/* Methodes renvoyants a la classe TypeEvolution */
	/**
	 * Methode pemettant de creer et inserer un Objet TypeEvolution a la liste DataList.typeEvolListe 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param libelleTypeEvol String
	 * @return TypeEvolution
	 */
	public static TypeEvolution ajoutPkmTypeEvolution (String libelleTypeEvol, ArrayList<TypeEvolution> lstCible) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet TypeEvolution vide
		TypeEvolution te = new TypeEvolution();
		
		// Test de non nullite du parametre et qu'il ne soit pas convertible en Entier 
		if(libelleTypeEvol.length() <= 30 && isInteger(libelleTypeEvol) == false) {
			estConforme = true;
			
			System.out.println("Le format du parametre TypeEvolution " + libelleTypeEvol.toString() 
			+ " est valide " + estConforme.toString());
			
			// Boucle test afin de savoir si l'Image est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (TypeEvolution eT : lstCible) {
				System.out.println("eT.getLibelleTypeEvol() == " + eT.getLibelleTypeEvol().toString());
				if (eT.getLibelleTypeEvol().toLowerCase().equals(libelleTypeEvol.toLowerCase())) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja un Objet TypeEvolution : " + eT.getLibelleTypeEvol());
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format du parametre TypeEvolution " + libelleTypeEvol
					+ " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de la relation TypeEvolution
		if (estConforme == true) {
			ArrayList<Integer> lstId = new ArrayList<Integer>();
			
			// Declaration et instanciation a 1 d'un entier id (instancier a 1 car valeur par defaut s'il n'y a pas d'Objet dans la liste cible)
			int id = 1;
			
			if (lstCible.size() != 0) {
				// remplissage d'une liste d'Entiers avec touts les Id de la liste typeEvolListe
				for (TypeEvolution tpE: lstCible) {
					lstId.add(tpE.getIdTypeEvol());
				}
				id = Collections.max(lstId) + 1;
			}
			
			// Instanciation de la variable AvoirType
			te = new TypeEvolution(id, libelleTypeEvol);
			if (estDouble == false) {
				// ajout de cette variable a la liste TypeEvolution de l'interface DataList
				lstCible.add(te);
				
				// Insertion du Type Evolution dans la Base de Donnees
				insertionDtbTypeEvolution(te);
			}
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (lstCible.size()) + " ****");
		
		System.out.println("\n\n");
		return te;
	}
	
	/**
	 * Methode permettant d'inserer un Objet TypeEvolution passe en parametre et de l'inserer dans la base de donnees
	 * @param te TypeEvolution
	 */
	private static void insertionDtbTypeEvolution (TypeEvolution te) {
		System.out.println("**** affichage de l'Id et du libelle de l'Objet TypeEvolution: " + te.getIdTypeEvol().toString() + " / " 
				+ te.getLibelleTypeEvol() + " ****");
		
		if (te.getIdTypeEvol() != 0 && te.getLibelleTypeEvol() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				
				// creation et instanciation de la chaine de la requete
				String requete = "CALL PROC_Insert_TypeEvol (" + te.getIdTypeEvol() + ", '" 
						+ te.getLibelleTypeEvol() + "') ;";
				
				// execution de la procedure stockee
				statement.executeUpdate(requete);
				
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertTypeEvolution ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur l'objet AvoirType est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Type_Evolution, de la BDD dans la liste de type TypeEvolution passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbTypeEvolution (ArrayList<TypeEvolution> listeTE) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeTE
		listeTE.removeAll(listeTE);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_TypeEvol_OrdreA ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				String libTE = resultat.getString("lib");
				Integer idTE = resultat.getInt("id");
								
				/*---** Traiter ici les valeurs recuperees **---*/
				// Instanciation d'un objet TypeEvolution contenant les valeurs recuperees 
				TypeEvolution te = new TypeEvolution(idTE, libTE);
				
				// Ajout de la relation AvoirType a la liste listeTE
				listeTE.add(te);
			}
			System.out.println("Nb relation TypeEvolution dans listeTE : " + listeTE.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectTypeEvolution ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}
	
	/* Methodes renvoyants a la classe Evolution */
	/**
	 * Methode pemettant de creer et inserer un Objet Evolution a la liste passee en parametre 
	 * seulement si la chaine passee en parametre est conforme et si elle n'existe pas deja dans la liste
	 * @param libelleTypeEvol String
	 * @return Evolution
	 */
	public static Evolution ajoutPkmEvolution (Pokemon sousEvol, Pokemon surEvol, TypeEvolution typeEvol,
			ArrayList<Evolution> lstAjout) {
		Boolean estConforme;
		Boolean estDouble = false;
		// Declaration de l'objet TypeEvolution vide
		Evolution e = new Evolution();
		String lib = null;
		
		// Test de longueur et non nullite des parametres
		if((sousEvol.getNumPokedexMondialPkm() + "/" + surEvol.getNumPokedexMondialPkm()).length() == 7 && sousEvol.getNomPokemon() != null 
				&& surEvol.getNomPokemon() != null && typeEvol.getLibelleTypeEvol() != null) {
			estConforme = true;
			
			lib = sousEvol.getNumPokedexMondialPkm() + "/" + surEvol.getNumPokedexMondialPkm();
			
			System.out.println("Le format des parametres de l'Evolution : " + lib.toString() 
			+ " sont valides " + estConforme.toString());
			
			// Boucle test afin de savoir si l'Evolution est deja presente dans la liste (ne bloque pas le traitement si aucun elements n'est present dans la liste)
			for (Evolution ev : lstAjout) {
				System.out.println("ev.getLibelleTypeEvol() == " + ev.getLibelleEvolution());
				if (ev.getPkmSousEvolution().getNumPokedexMondialPkm().equals(sousEvol.getNumPokedexMondialPkm()) && 
						ev.getPkmSurEvolution().getNumPokedexMondialPkm().equals(surEvol.getNumPokedexMondialPkm())) {
					// affichage du message d'erreur et passage du Booleen estDouble a true
					estDouble = true;
					System.out.println("Erreur il existe deja un Objet Evolution : " + lib);
				}
			}
		} else {
			estConforme = false;
			System.out.println("Le format des parametres Evolution " + sousEvol.getNumPokedexMondialPkm() + "/" + 
					surEvol.getNumPokedexMondialPkm() + " est invalide " + estConforme.toString());
		}
		
		// Si les tests sont conforme on fait l'ajout de l'Evolution
		if (estConforme == true) {
			ArrayList<Integer> lstId = new ArrayList<Integer>();
			
			// Declaration et instanciation a 1 d'un entier id (instancier a 1 car valeur par defaut s'il n'y a pas d'Objet dans la liste cible)
			int id = 1;
			
			if (lstAjout.size() != 0) {
				// remplissage d'une liste d'Entiers avec touts les Id de la liste typeEvolListe
				for (Evolution ev : lstAjout) {
					lstId.add(ev.getIdEvolution());
				}
				id = Collections.max(lstId) + 1;
			}
			
			// Instanciation de la variable Evolution
			e = new Evolution(id, lib, sousEvol, surEvol, typeEvol);
			
			if(estDouble == false) {
				// ajout de cette variable a la liste passee en parametre
				lstAjout.add(e);
				
				// Insertion de l'Evolution dans la Base de Donnees
				insertionDtbEvolution(e);
			}
			
		} else {
			System.out.println("L'objet Evolution n'as pas pas ete ajoute");
		}
		
		System.out.println("**** affichage du nb d'elements dans la liste : " + (lstAjout.size()) + " ****");
		
		System.out.println("\n\n");
		return e;
	}
	
	/**
	 * Methode permettant d'inserer un Objet Evolution passe en parametre et de l'inserer dans la base de donnees
	 * @param te TypeEvolution
	 */
	private static void insertionDtbEvolution (Evolution ev) {
		System.out.println("**** affichage du libelle de l'Objet Evolution : " + ev.getLibelleEvolution().toString() + " ****");
		
		if (ev.getLibelleEvolution() != null) {
			// Ouverture de la connection a la base de donnees et recuperation de l'objet 
			Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
			
			try { // essai de la parcelle de code
				// Creation de l'objet gerant les requetes
				Statement statement = conn.createStatement();
				
				// creation et instanciation de la chaine de la requete
				String requete = "CALL PROC_Insert_Evolution (" + ev.getIdEvolution() + ", '" + ev.getLibelleEvolution() + "', '" 
						+ ev.getPkmSousEvolution().getNumPokedexMondialPkm() + "', '" + ev.getPkmSurEvolution().getNumPokedexMondialPkm() + "', " 
						+ ev.getTypeEvolution().getIdTypeEvol() + ") ";
				
				// execution de la procedure stockee
				statement.executeUpdate(requete);
				
			} catch (SQLException e) { // erreur dans le "try"
				System.out.println("*********** exception insertEvolution ***** : " + e.getMessage() + " ***********");
			}
			
			// Fermeture de la connection a la base de donnees
			ConnDtbMySqlOutils.CloseDtbConnection();
		} else {
			System.out.println("*** Erreur l'objet Evolution est null l'insertion dans la BDD n'as donc pas ete faite ***");
		}
	}
	
	/**
	 * Permet de recuperer les donnees de la table Type_Evolution, de la BDD dans la liste de type TypeEvolution passee en parametre
	 * @param listeAnnee
	 */
	public static void recupDtbEvolution (ArrayList<Evolution> listeE) {
		// Declaration d'un Objet ResultSet afin de recuperer les elements de la requete
		ResultSet resultat;
		
		// Suppression de touts les elements de listeTE
		listeE.removeAll(listeE);
		
		// Ouverture de la connection a la base de donnees et recuperation de l'objet 
		Connection conn = ConnDtbMySqlOutils.OpenDtbConnection();
		
		try { // essai de la parcelle de code
			// Creation de l'objet gerant les requetes
			Statement statement = conn.createStatement();
			
			// execution de la procedure stockee et stockage des resultats dans resultat
			resultat = statement.executeQuery( "CALL PROC_Select_Evolution_ordASousEvol ()" );
			
			// Recuperation des donnees du resultat de la requete de lecture 
			while ( resultat.next() ) {
				
				int id = resultat.getInt("id");
				String libE = resultat.getString("lib");
				String sousEvolNum = resultat.getString("sous_evol");
				String surEvolNum = resultat.getString("sur_evol");
				Integer typeEvolInt = resultat.getInt("typeEvol");
				
				Pokemon sousEvol = new Pokemon();
				Pokemon surEvol = new Pokemon();
				TypeEvolution typeEvol = new TypeEvolution();
				
				for (TypeEvolution te: DataList.typeEvolListe) {
					if (te.getIdTypeEvol() == typeEvolInt) {
						typeEvol = te;
						break;
					}
				}
				
				int c = 0; 
				
				// on defile la pkmListe pour assigner les deux pkm selon leurs numero si le compteur 'c' est a 2 on break la boucle
				for (Pokemon pkm: DataList.pkmListe) {
					if (pkm.getNumPokedexMondialPkm().equals(sousEvolNum)) {
						sousEvol = pkm;
						c ++;
					}
					if (pkm.getNumPokedexMondialPkm().equals(surEvolNum)) {
						surEvol = pkm;
						c ++;
					}
					if (c >= 2) {
						break;
					}
				}
				
				/*---** Traiter ici les valeurs recuperees **---*/
				// Instanciation d'un objet TypeEvolution contenant les valeurs recuperees 
				Evolution e = new Evolution(id, libE, sousEvol, surEvol, typeEvol);
				
				// Ajout de la relation AvoirType a la liste listeTE
				listeE.add(e);
			}
			System.out.println("Nb d'Objets dans listeE : " + listeE.size());
			
		} catch (SQLException e) { // erreur dans le "try"
			System.out.println("***** exception SelectTypeEvolution ***** : " + e.getMessage() + " *****");
		}
		
		// Fermeture de la connection a la base de donnees
		ConnDtbMySqlOutils.CloseDtbConnection();
	}
}
