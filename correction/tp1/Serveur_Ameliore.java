package correction.tp1;

import java.net.*;
import java.io.*;

public class Serveur_Ameliore {
	ServerSocket mon_connecteur;  // serveur de socket du serveur am�lior�
	/* Port d'�coute */
	private int port;
	final String Finish = "" + (char) 4;  //Signal de fin de connection aussi nomm� EOT  ctrl-d

	public Serveur_Ameliore(int cport) throws IOException {
		port = cport;
		this.mon_connecteur = new ServerSocket(port); 		//Creation du gestionnaire de socket 
		System.out.format("Serveur Am�lior�  lanc� sur le  port %d\n", port);
	}
	private  void terminer(Socket ma_connection){
	    if (ma_connection != null)       	
	    {
		    try 	{
			ma_connection.close(); 
			System.out.format("Socket fermee \n"); 
		    }
		    catch ( IOException e )   { System.out.println("weird, nawak .... \n ");}     // do nothiing } 
		}}
		
	public void run() {
		Socket ma_connection = null;  		// file instanci�e pour commmuniquer avec le client
		while (true) {
			// // /* Attente bloquante connexion */
			try {
				ma_connection = this.mon_connecteur.accept();
			} catch (IOException e) {
				System.out.println("Impossible de d�tacher une socket  : " + e);
				System.exit(-1);
			}
			int c_port = ma_connection.getPort();
			String c_ip = ma_connection.getInetAddress().toString();
			System.out.format("Un client est arriv� avec IP : %s sur le port %d\n", c_ip, c_port);
						/* On traite le client que l'on a associ� */
			try {
				Service_Client(ma_connection);
			} catch (IOException e) {
				System.out.println("Erreur de Service Client  : " + e);
				System.exit(-1);	}
		}
	}
	private Boolean Service_Client(Socket la_connection) throws IOException {
		/* On Associe une file d'entr�e a la connection */
		InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
		/* On transforme cette file en file avec tampon */
		BufferedReader flux_entrant = new BufferedReader(isr);
		System.out.println("Tampon entree attache ");
		// On r�cup�re la file de sortie
		PrintWriter ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
		System.out.println("Sortie attach�e");
		
		String clientName = la_connection.getRemoteSocketAddress().toString();
		System.out.format("Pr�t � servir le Client %s\n", clientName);

		String message_lu = new String();
		int line_num = 0;
		/*
		 * On lit le flux_entrant ligne � ligne ATTENTION : La fonction readline
		 * est Bloquante readline retourne null si il y a souci avec la
		 * connexion On s arrete aussisi connexion_non_terminee est vraie
		 */
		ma_sortie.format("Bonjour %s j attends tes donn�es  \n",clientName);
		while  ((message_lu = flux_entrant.readLine()) != null) {
			System.out.format("%d: ->  [%s]\n", line_num, message_lu);
			line_num++;
			/* si on recoit Finish on clot et annonce cette terminaison */
			if (message_lu.contains(Finish)) {
				System.out.println("Reception de  " + Finish
						+ " -> Transmission finie");
				// On ferme la connection
				System.out.format("Je clos la connection  %s :\n",clientName);
				terminer(la_connection);
				return (true);
			}
		}
		return false;

	}
	public static void main(String[] args) {
		/* On cr�e puis lance le serveur */
		Serveur_Ameliore Mon_serveur = null;
		if (args.length != 1) {
			System.err.println("usage: java "+ Serveur_Ameliore.class.getCanonicalName()
							+ " serverPort");
			System.exit(-1);
		}
		try {
			Mon_serveur = new Serveur_Ameliore(Integer.parseInt(args[0]));
		} catch (NumberFormatException e) {
			System.out.println("Format du port incorrect \n:  format exception for "
							+ e.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Impossible de cr�er le  socket server : " + e);
			System.exit(-1);
		}
		Mon_serveur.run();
	}
}
