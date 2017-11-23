package tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_Jouet {
	/*
	 * connexion au serveur 127.0.0.1 12000 (sur powershell)
	 * ne pas oublier de fermer la console a chaque lancement de serveur
	 * lancer d'abord le java avant de se connecter sur le shell
	 */
	
	public static void main(String[] args) {
		
		int port = 12000;
		
		try {
				ServerSocket socketserver = new ServerSocket (port);
				System.out.println("Le serveur est à l'écoute du port " + socketserver.getLocalPort());
				Socket ma_connexion=socketserver.accept();
				System.out.println("--Un client est connecté--");
			
				//recoit message
				InputStreamReader isr = new InputStreamReader(ma_connexion.getInputStream(), "UTF-8");
				BufferedReader flux_entrant = new BufferedReader(isr);
				
				String nouveau_message=null;
				
				while ((nouveau_message = flux_entrant.readLine())!=null) {
					if(nouveau_message.equalsIgnoreCase("FINISH")) {
						socketserver.close();
						System.out.println("--Le client s'est déconnecté.--");
						return;	
					}
					System.out.println("Client: " + nouveau_message);	
				}	
				
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
}

