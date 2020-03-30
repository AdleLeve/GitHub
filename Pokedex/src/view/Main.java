package view;

import model.*;
import controller.Outils;
import controller.DataList;
/**
 * Classes Main
 */
public class Main {

	public static void main(String[] args) {
		
		testRecupLstBDD();
		
		testInsertLstBDD();
	}
	
	private static void testInsertLstBDD() {
		/****---	Annee	---****/
		// Instanciations des Objets Annee et ajout a la liste des annees & Insertion dans la BDD des Objets Annee
		Annee annee0 = Outils.ajoutAnneeListe("1996");
		Annee annee1 = Outils.ajoutAnneeListe("1997");
		Annee annee2 = Outils.ajoutAnneeListe("1998");
		Annee annee3 = Outils.ajoutAnneeListe("1999");
		Annee annee4 = Outils.ajoutAnneeListe("2000");
		Annee annee5 = Outils.ajoutAnneeListe("2001");
		Annee annee6 = Outils.ajoutAnneeListe("2002");
		Annee annee7 = Outils.ajoutAnneeListe("2003");
		Annee annee8 = Outils.ajoutAnneeListe("2019");
		Annee annee9 = Outils.ajoutAnneeListe("2004");
		Annee annee10 = Outils.ajoutAnneeListe("2005");
		Annee annee11 = Outils.ajoutAnneeListe("2006");
		Annee annee12 = Outils.ajoutAnneeListe("2007");
		Annee annee13 = Outils.ajoutAnneeListe("2008");
		Annee annee14 = Outils.ajoutAnneeListe("2009");
		Annee annee15 = Outils.ajoutAnneeListe("2010");
		Annee annee16 = Outils.ajoutAnneeListe("2011");
		Annee annee17 = Outils.ajoutAnneeListe("2012");
		Annee annee18 = Outils.ajoutAnneeListe("2013");
		Annee annee19 = Outils.ajoutAnneeListe("2014");
		Annee annee20 = Outils.ajoutAnneeListe("2015");
		Annee annee21 = Outils.ajoutAnneeListe("2016");
		Annee annee22 = Outils.ajoutAnneeListe("2017");
		Annee annee23 = Outils.ajoutAnneeListe("2018");
		Annee Eannee1 = Outils.ajoutAnneeListe("1996"); // test d'erreur
		Annee Eannee2 = Outils.ajoutAnneeListe("198F"); // test d'erreur
		Annee Eannee3 = Outils.ajoutAnneeListe("19934"); // test d'erreur
		Annee Eannee4 = Outils.ajoutAnneeListe("199"); // test d'erreur
		
		System.out.println("\n");
		
		/****---	Generation		---****/
		// Instanciations des Objets Generation et ajout a la liste des generations & Insertion dans la BDD des Objets Generation
		Generation g01 = Outils.ajoutGenerationListe(1, annee0);
		Generation g02 = Outils.ajoutGenerationListe(2, annee3);
		Generation g03 = Outils.ajoutGenerationListe(3, annee6);
		Generation g04 = Outils.ajoutGenerationListe(4, annee11);
		Generation g05 = Outils.ajoutGenerationListe(5, annee15);
		Generation g06 = Outils.ajoutGenerationListe(6, annee18);
		Generation g07 = Outils.ajoutGenerationListe(5, annee21);
		Generation g08 = Outils.ajoutGenerationListe(6, annee8);
		Generation Eg01 = Outils.ajoutGenerationListe(1, annee1);
		Generation Eg02 = Outils.ajoutGenerationListe(101, annee1);
		Generation Eg03 = Outils.ajoutGenerationListe(-1, annee1);
		
		System.out.println("\n");
		
		/****---	Type	---****/
		// Instanciations des Objets Type et ajout a la liste des types & Insertion dans la BDD des Objets Type
		Type t01 = Outils.ajoutTypeListe("Normal", g01);
		Type t02 = Outils.ajoutTypeListe("Feu", g01);
		Type t03 = Outils.ajoutTypeListe("Eau", g01);
		Type t04 = Outils.ajoutTypeListe("Plante", g01);
		Type t05 = Outils.ajoutTypeListe("Sol", g01);
		Type t06 = Outils.ajoutTypeListe("Psy", g01); 
		Type t07 = Outils.ajoutTypeListe("Poison", g01);
		Type t08 = Outils.ajoutTypeListe("Electrik", g01);
		Type t09 = Outils.ajoutTypeListe("Spectre", g01);
		Type t10 = Outils.ajoutTypeListe("Roche", g01);
		Type t11 = Outils.ajoutTypeListe("Vol", g01);
		Type t12 = Outils.ajoutTypeListe("Combat", g01);
		Type t13 = Outils.ajoutTypeListe("Insect", g01);
		Type t14 = Outils.ajoutTypeListe("Dragon", g01);
		Type t15 = Outils.ajoutTypeListe("Glace", g01);
		Type t16 = Outils.ajoutTypeListe("Acier", g02);
		Type t17 = Outils.ajoutTypeListe("Tenebre", g02);
		Type t18 = Outils.ajoutTypeListe("Fee", g06);

		System.out.println("\n");
		
		/****---	Images	---****/
		// Declaration et Instanciation d'une Chaine contenant l'extension ".png"
		String png = ".png";

		// Instanciations des Objets Images et ajout a la liste des images & Insertion dans la BDD des Objets Images 
		Images i01 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/1");
		Images i02 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/2");
		Images i03 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/3");
		Images i04 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/4");
		Images i05 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/5");
		Images i06 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/6");
		Images i07 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/7");
		Images i08 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/8");
		Images i09 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/9");
		Images i10 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/10");
		Images i11 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/11");
		Images i12 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/12");
		Images i13 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/13");
		Images i14 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/14");
		Images i15 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/15");
		Images i16 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/16");
		Images i17 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/17");
		Images i18 = Outils.ajoutImageListe(png, "http://localhost/imgpkm/18");
		
		/* */		// automatisation de la retree des url
		for (int i = 19; i <= 649; i++) {
			String url = "http://localhost/imgpkm/" + i;
			
			Outils.ajoutImageListe(png, url);
			
		}	/* */

		System.out.println("\n");
		
		/****---	Pokemon		---****/
		// Instanciations des Objets Pokemon et ajout a la liste des pokemons & Insertion dans la BDD des Objets Pokemon
		Pokemon p01 = Outils.ajoutPkmListe("001", "Bulbizarre", "Bulbizarre passe son temps à faire la sieste sous le soleil. Il y a une graine sur son dos. Il absorbe les rayons du soleil pour faire doucement pousser la graine.", i01, false, g01);
		Pokemon p02 = Outils.ajoutPkmListe("002", "Herbizarre", "Un bourgeon a poussé sur le dos de ce Pokémon. Pour en supporter le poids, Herbizarre a dû se muscler les pattes. Lorsqu il commence à se prélasser au soleil, ça signifie que son bourgeon va éclore, donnant naissance à une fleur.", i02, false, g01);
		Pokemon p03 = Outils.ajoutPkmListe("003", "Florizarre", "Une belle fleur se trouve sur le dos de Florizarre. Elle prend une couleur vive lorsqu elle est bien nourrie et bien ensoleillée. Le parfum de cette fleur peut apaiser les gens.", i03, false, g01);
		Pokemon p04 = Outils.ajoutPkmListe("004", "Salamèche", "La flamme qui brûle au bout de sa queue indique l humeur de ce Pokémon. Elle vacille lorsque Salamèche est content. En revanche, lorsqu il s énerve, la flamme prend de l importance et brûle plus ardemment.", i04, false, g01);
		Pokemon p05 = Outils.ajoutPkmListe("005", "Reptincel", "Reptincel lacère ses ennemis sans pitié grâce à ses griffes acérées. S il rencontre un ennemi puissant, il devient agressif et la flamme au bout de sa queue s embrase et prend une couleur bleu clair.", i05, false, g01);
		Pokemon p06 = Outils.ajoutPkmListe("006", "Dracaufeu", "Dracaufeu parcourt les cieux pour trouver des adversaires à sa mesure. Il crache de puissantes flammes capables de faire fondre n importe quoi. Mais il ne dirige jamais son souffle destructeur vers un ennemi plus faible.", i06, false, g01);
		Pokemon p07 = Outils.ajoutPkmListe("007", "Carapuce", "La carapace de Carapuce ne sert pas quà le protéger. La forme ronde de sa carapace et ses rainures lui permettent d améliorer son hydrodynamisme. Ce Pokémon nage extrêmement vite.", i07, false, g01);
		Pokemon p08 = Outils.ajoutPkmListe("008", "Carabaffe", "Carabaffe a une large queue recouverte d une épaisse fourrure. Elle devient de plus en plus foncée avec l âge. Les éraflures sur la carapace de ce Pokémon témoignent de son expérience au combat.", i08, false, g01);
		Pokemon p09 = Outils.ajoutPkmListe("009", "Tortank", "Tortank dispose de canons à eau émergeant de sa carapace. Ils sont très précis et peuvent envoyer des balles d eau capables de faire mouche sur une cible située à plus de 50 m.", i09, false, g01);
		Pokemon p10 = Outils.ajoutPkmListe("010", "Chenipan", "Chenipan a un appétit d ogre. Il peut engloutir des feuilles plus grosses que lui. Les antennes de ce Pokémon dégagent une odeur particulièrement entêtante.", i10, false, g01);
		Pokemon p11 = Outils.ajoutPkmListe("011", "Chrysacier", "La carapace protégeant ce Pokémon est dure comme du métal. Chrysacier ne bouge pas beaucoup. Il reste immobile pour préparer les organes à l intérieur de sa carapace en vue d une évolution future.", i11, false, g01);
		Pokemon p12 = Outils.ajoutPkmListe("012", "Papilusion", "Papilusion est très doué pour repérer le délicieux nectar qu il butine dans les fleurs. Il peut détecter, extraire et transporter le nectar de fleurs situées à plus de 10 km de son nid.", i12, false, g01);
		Pokemon p13 = Outils.ajoutPkmListe("013", "Aspicot", "L odorat d Aspicot est extrêmement développé. Il lui suffit de renifler ses feuilles préférées avec son gros appendice nasal pour les reconnaître entre mille.", i13, false, g01);
		Pokemon p14 = Outils.ajoutPkmListe("014", "Coconfort", "Coconfort est la plupart du temps immobile et reste accroché à un arbre. Cependant, intérieurement, il est très actif, car il se prépare pour sa prochaine évolution. En touchant sa carapace, on peut sentir sa chaleur.", i14, false, g01);
		Pokemon p15 = Outils.ajoutPkmListe("015", "Dardagnan", "Dardargnan est extrêmement possessif. Il vaut mieux ne pas toucher son nid si on veut éviter d avoir des ennuis. Lorsqu ils sont en colère, ces Pokémon attaquent en masse.", i15, false, g01);
		Pokemon p16 = Outils.ajoutPkmListe("016", "Roucool", "Roucool a un excellent sens de l orientation. Il est capable de retrouver son nid sans jamais se tromper, même s il est très loin de chez lui et dans un environnement qu il ne connaît pas.", i16, false, g01);
		Pokemon p17 = Outils.ajoutPkmListe("017", "Roucoups", "Roucoups utilise une vaste surface pour son territoire. Ce Pokémon surveille régulièrement son espace aérien. Si quelqu un pénètre sur son territoire, il corrige l ennemi sans pitié d un coup de ses terribles serres.", i17, false, g01);
		Pokemon p18 = Outils.ajoutPkmListe("018", "Roucarnage", "Ce Pokémon est doté d un plumage magnifique et luisant. Bien des Dresseurs sont captivés par la beauté fatale de sa huppe et décident de choisir Roucarnage comme leur Pokémon favori.", i18, false, g01);
		
		System.out.println("\n");
		
		/****---	AvoirType	---****/
		// Instanciations des Objets AvoirType et ajout a la liste d'objets AvoirTypes & Insertion dans la BDD des Objets AvoirType
		Outils.ajoutPkmAvoirType(p01, t04);
		Outils.ajoutPkmAvoirType(p02, t04);
		Outils.ajoutPkmAvoirType(p03, t04);
		Outils.ajoutPkmAvoirType(p01, t07);
		Outils.ajoutPkmAvoirType(p02, t07);
		Outils.ajoutPkmAvoirType(p03, t07);
		Outils.ajoutPkmAvoirType(p04, t02);
		Outils.ajoutPkmAvoirType(p05, t02);
		Outils.ajoutPkmAvoirType(p06, t02);
		Outils.ajoutPkmAvoirType(p06, t11); 
		Outils.ajoutPkmAvoirType(p07, t03);
		Outils.ajoutPkmAvoirType(p08, t03);
		Outils.ajoutPkmAvoirType(p09, t03);
		Outils.ajoutPkmAvoirType(p10, t13);
		Outils.ajoutPkmAvoirType(p11, t13);
		Outils.ajoutPkmAvoirType(p12, t13);
		Outils.ajoutPkmAvoirType(p12, t11);
		Outils.ajoutPkmAvoirType(p13, t13);
		Outils.ajoutPkmAvoirType(p13, t07);
		Outils.ajoutPkmAvoirType(p14, t13);
		Outils.ajoutPkmAvoirType(p14, t07);
		Outils.ajoutPkmAvoirType(p15, t13);
		Outils.ajoutPkmAvoirType(p15, t07);
		Outils.ajoutPkmAvoirType(p16, t01);
		Outils.ajoutPkmAvoirType(p16, t11);
		Outils.ajoutPkmAvoirType(p17, t01);
		Outils.ajoutPkmAvoirType(p17, t11);
		Outils.ajoutPkmAvoirType(p18, t01);
		Outils.ajoutPkmAvoirType(p18, t11);
		
		System.out.println("\n");

		/****---	TypeEvolution	---****/
		// Instanciations des Objets TypeEvolution et ajout a la liste des types d'evolutions & Insertion dans la BDD des Objets TypeEvolution
		TypeEvolution te0 = Outils.ajoutPkmTypeEvolution("Montée de Niveau", DataList.typeEvolListe);
		TypeEvolution te1 = Outils.ajoutPkmTypeEvolution("Tenue d Objet", DataList.typeEvolListe);
		TypeEvolution te2 = Outils.ajoutPkmTypeEvolution("Échange", DataList.typeEvolListe);
		TypeEvolution te3 = Outils.ajoutPkmTypeEvolution("Pierre Évolutive", DataList.typeEvolListe);
		TypeEvolution te4 = Outils.ajoutPkmTypeEvolution("Lieux", DataList.typeEvolListe);
		TypeEvolution te5 = Outils.ajoutPkmTypeEvolution("Horraire", DataList.typeEvolListe);
		TypeEvolution te6 = Outils.ajoutPkmTypeEvolution("Connaissance de Capacité", DataList.typeEvolListe);
		TypeEvolution Ete1 = Outils.ajoutPkmTypeEvolution("gggggggggggggggggggggggggggggggggg", DataList.typeEvolListe); // test d'erreur taille
		TypeEvolution Ete2 = Outils.ajoutPkmTypeEvolution("Tenue d Objet", DataList.typeEvolListe); // test d'erreur double
		TypeEvolution Ete3 = Outils.ajoutPkmTypeEvolution("MoNtée de NiVeAU", DataList.typeEvolListe); // test d'erreur double
		TypeEvolution Ete4 = Outils.ajoutPkmTypeEvolution("1998", DataList.typeEvolListe); // test d'erreur Entier
		
		System.out.println("\n");
		
		/****---	Evolution	---****/
		// Instanciations des Objets Evolution et ajout a la liste des evolutions & Insertion dans la BDD des Objets Evolution
		Evolution e01 = Outils.ajoutPkmEvolution(p01, p02, te0, DataList.evolutionListe);
		Evolution e02 = Outils.ajoutPkmEvolution(p02, p03, te0, DataList.evolutionListe);
		Evolution e03 = Outils.ajoutPkmEvolution(p04, p05, te0, DataList.evolutionListe);
		Evolution e04 = Outils.ajoutPkmEvolution(p05, p06, te0, DataList.evolutionListe);
		Evolution e05 = Outils.ajoutPkmEvolution(p07, p08, te0, DataList.evolutionListe);
		Evolution e06 = Outils.ajoutPkmEvolution(p08, p09, te0, DataList.evolutionListe);
		Evolution e07 = Outils.ajoutPkmEvolution(p10, p11, te0, DataList.evolutionListe);
		Evolution e08 = Outils.ajoutPkmEvolution(p11, p12, te0, DataList.evolutionListe);
		Evolution e09 = Outils.ajoutPkmEvolution(p13, p14, te0, DataList.evolutionListe);
		Evolution e10 = Outils.ajoutPkmEvolution(p14, p15, te0, DataList.evolutionListe);
		Evolution e11 = Outils.ajoutPkmEvolution(p16, p17, te0, DataList.evolutionListe);
		Evolution e12 = Outils.ajoutPkmEvolution(p17, p18, te0, DataList.evolutionListe);
	}
	
	private static void testRecupLstBDD() {
		// Recuperation du contenu de la base de donnee dans les listes de l'Iterface DataList
		Outils.recupDtbAnnee(DataList.anneeListe);
		Outils.recupDtbGeneration(DataList.generationListe);
		Outils.recupDtbType(DataList.typeListe);
		Outils.recupDtbImages(DataList.imagesListe);
		Outils.recupDtbPokemon(DataList.pkmListe);
		Outils.recupDtbAvoirType(DataList.avoirTypeListe);
		Outils.recupDtbTypeEvolution(DataList.typeEvolListe);
		Outils.recupDtbEvolution(DataList.evolutionListe);
		
		// Affichage dans la console du nombre d'Objets dans les differentes listes
		System.out.println("Nb Annee dans anneeListe : " + DataList.anneeListe.size());
		System.out.println("Nb Generation dans generationListe : " + DataList.generationListe.size());
		System.out.println("Nb Type dans typeListe : " + DataList.typeListe.size());
		System.out.println("Nb Images dans imagesListe : " + DataList.imagesListe.size());
		System.out.println("Nb Pokemon dans pkmListe : " + DataList.pkmListe.size());
		System.out.println("Nb AvoirType dans avoirTypeListe : " + DataList.avoirTypeListe.size());
		System.out.println("Nb TypeEvolution dans typeEvolListe : " + DataList.typeEvolListe.size());
		System.out.println("Nb Evolutions dans evolutionListe : " + DataList.evolutionListe.size());
		
		// Defilement des differents Objets presents dans les listes de l'Interface DataList
		for (Annee annee: DataList.anneeListe) {
			System.out.println("\nNumero annee : " + annee.getNumAnnee());
		}
		for (Generation g: DataList.generationListe) {
			System.out.println("\nNumero type : " + g.getNumGeneration());
		}
		for (Type type: DataList.typeListe) {
			System.out.println("\nId type : " + type.getIdType());
		}
		for (Images i: DataList.imagesListe) {
			System.out.println("\nUrl image : " + i.getUrlComplete());
		}
		for (Pokemon p: DataList.pkmListe) {
			System.out.println("\nNumero Pokemon : " + p.getNumPokedexMondialPkm());
		}
		for (AvoirType at: DataList.avoirTypeListe) {
			System.out.println("\nRelation Pokemon / Type : " + at.getPkmAvoirType().getNumPokedexMondialPkm() + " / " + at.getTypeAvoirType().getLibType());
		}
		for (TypeEvolution te: DataList.typeEvolListe) {
			System.out.println("\nLibelle du Type d'Evolution : " + te.getLibelleTypeEvol());
		}
		for (Evolution e: DataList.evolutionListe) {
			System.out.println("\nLibelle de l'Evolution : " + e.getLibelleEvolution());
		}
	}
}
