package tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_multi {
	
	/*
	 * Le serveur marche en séquencielle (il faut que le 2ème client attende 
	 * que le 1er se déconnecte pour que ses écritures soient prises en comptes.
	 */
	
	ServerSocket serv;

	public Serveur_multi(int port) {
		ServerSocket socketserver;
		try {
			this.serv = new ServerSocket (port);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void Run() {
		try {
			Socket ma_connexion=this.serv.accept();
			this.Service_Client(ma_connexion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Service_Client(Socket ma_connexion) {
		try {
			InputStreamReader isr = new InputStreamReader(ma_connexion.getInputStream(), "UTF-8");
			BufferedReader flux_entrant = new BufferedReader(isr);
			PrintWriter out= new PrintWriter(ma_connexion.getOutputStream());
			out.println("Vous êtes dans le serveur");
			out.flush();
			System.out.println("--Un client vient de se connecter--");
			
			String nouveau_message=null;
			
			while ((nouveau_message = flux_entrant.readLine())!=null) {
				if(nouveau_message.equalsIgnoreCase("FINISH")) {
					ma_connexion.close();
					System.out.println("--Le client s'est déconnecté.--");
					this.Run();
					return;	
				}
				System.out.println("Client: " + nouveau_message);		
			}	
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Serveur_multi serv = new Serveur_multi(12000);
		serv.Run();
	}
}

