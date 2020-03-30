package controller;

/**
 * @author Adèle
 * Interface ConnDtbMySql stockant les constantes relatives a la connexion a la base de donnee
 */
public interface ConnDtbMySql {
    final String unicode="?useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
	final String myPort = "3308";
	final String myAdrUrl = "127.0.0.1";
	final String myDtbName = "pokedex";
	public final String myUser = "root";
	public final String myPswd = "";
	
	public final String myDbUrl = "jdbc:mysql://" + myAdrUrl + ":" + myPort + "/" + myDtbName + unicode; 
}
