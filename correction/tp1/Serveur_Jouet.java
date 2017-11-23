package correction.tp1;

import java.net.*;
import java.io.*;
import java.util.*;

public class Serveur_Jouet {
	
	private static final String Finish=""+(char) 4;; // caractère de fin ctrl-d
	private static ServerSocket  gestionnaire_de_connexion;  // Objet gerant les sockets
	private  final static int port = 12000;         /* Port d'écoute */
	private static Socket ma_connection;      /* file instanciée */
	
	public static void main(String[] args) {
		try {
			gestionnaire_de_connexion = new ServerSocket(port);
			System.out.println("Serveur lancé sur " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.format("Cannot create to the server, port %d may be busy\n", port);
			System.exit(-1);
		}
		
		try{
		    //  Attente d'une bloquante connexion  
		    System.out.println("waiting for connexion") ;
		    ma_connection = gestionnaire_de_connexion.accept();
		    
		    // Connection recupérée, on determine l'ip et le port
		    String c_ip = ma_connection.getInetAddress().toString() ;
		    int c_port= ma_connection.getPort();
		    System.out.format("client admis IP %s  sur le port %d\n", c_ip, c_port);  
		    
		    /* On Associe un tampon pour lire sur  le flux  connection  
		       Input streamreader permet de transformer le flux d'octets en flux de caracteres
		       le second argument et le type d'encodage des caractere --utf-8, isoXXXX etc ... */
		    InputStreamReader isr = new InputStreamReader(ma_connection.getInputStream(), "UTF-8");
		    // Une seconde encapsulation qui permet d'améliorer les perfomances en lisant par blocs -- pour les gros fichiers 
		    BufferedReader flux_entrant = new BufferedReader(isr) ; 
		    System.out.println("Mon Tampon de lecture est attache ");
		    
		    // Stream de sortie,  getOutputStream renvoie un Outputstream sur lequel on peut juste écrire des bit
		    // PrintWriter l'encapsule ce qui permet d'érire comme sur Sys
		    // le second parametre impose l option autoflush .. ce qui evite de faire de forcer l'envoi des messages partout
		    PrintWriter ma_sortie = new PrintWriter(ma_connection.getOutputStream() , true);
		    System.out.println("Mon Tampon pour ecrire  attache ");
		    
		    System.out.format("Pret à servir  IP %s  sur le port %d\n", c_ip, c_port);
		    ma_sortie.format("Hello %s  sur le port %d,  vous etes, pour faire simple, disons Admis\n" ,  c_ip, c_port );  
		    
		    String  message_lu = new String();
		    int line_num =0 ;
		    /* On lit une ligne dans le flux_entrant     La fonction readline est Bloquante
		       La condition du while fait diverses choses
		       elle attend que le client ai ecrit au moins une ligne
		       si la connection est  brisée ou fautive  ce message vaudra null et l'on quitera la boucle while    
		    */
		    while (   (message_lu = flux_entrant.readLine())    != null      ){
			// Si le client demande de terminer 
			    if (message_lu.contains(Finish) ){
				// on termine proprement 
				System.out.format ("[%s] recu, Transmission finie\n",message_lu);
				ma_sortie.println("Vous etes VIRE");
				terminer();
			    }
			    System.out.format( "[%d]--> [%s]]\n", line_num, message_lu);
			    line_num++;
		    }
		    // Si on est ici à priori le client à fermé la connection, sans envoyer finish (pex on peut tuer le processus telnet) 
		    System.out.println( "Client deconnecté, je termine\n" )  ;
		    terminer();
		    
		    
		}
		catch (IOException e) {
		    System.err.println(" Erreur de reception");
		    e.printStackTrace(); terminer();}
		
	     }

	    private static void terminer(){
		try{
				if (ma_connection != null) ma_connection.close();
				if (gestionnaire_de_connexion != null) gestionnaire_de_connexion.close();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
			System.exit(0);
	    }
		
}

