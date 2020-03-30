package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Adèle
 * Classe ConnDtbMySqlOutils gerant la connexion et la deconnexion a la base de donnee
 */
public class ConnDtbMySqlOutils {
	// Creation d'un Objet conn de type connection
	public static Connection conn = null;
	
	/**
	 * Permet d'ouvrir la connexion vers une BDD et de retourner l'Objet Connection correspondant
	 * @return Connection
	 */
	public static Connection OpenDtbConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(ConnDtbMySql.myDbUrl, ConnDtbMySql.myUser, ConnDtbMySql.myPswd);
			
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Ouverture du driver : " + dm.getDriverName());
				System.out.println("\n");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			conn = null;
		} catch (ClassNotFoundException  ce) {
			ce.printStackTrace();
			conn = null;
		} 
		/* finally {
			if (conn != null) {
				try {
					// Femeture de la connection
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		} */
		
		return conn;
	}

	/**
	 * Permet de Fermer la connexion a la BDD
	 */
	public static void CloseDtbConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Fermeture de la connection au serveur");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
