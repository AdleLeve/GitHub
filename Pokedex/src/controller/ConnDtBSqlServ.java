package controller;

/**
 * @author Adèle
 * Interface ConnDtBSqlServ stockant les constantes relatives a la connexion a la base de donnee
 */
public interface ConnDtBSqlServ {
	final String adrServ = "127.0.0.1";
	final String nomServ = "MSSQLSERVER1";
	final String dtbname = "pokedex";
	final String user = "sa";
	final String pswd = "Ad76000";
	
	public final String dbUrl = "jdbc:sqlserver://" + adrServ + "\\" + nomServ + 
			";databaseName=" + dtbname + ";user=" + user + ";password=" + pswd; 
}
