package correction.tp3;

import java.io.*;
import java.net.*;

public class Repartiteur extends ServerSocket {
	private final static int port = 12000; /* Port d'�coute */

	public Repartiteur() throws IOException {
		super(port);
		System.out.println("[Serveur] : Serveur Jouet lanc� sur " + (port));
	}
	public void execute() throws IOException {
		Socket maConnection;
		while (true) {
			System.out.println("[Serveur]:  waiting for connexion");
			maConnection = accept();
			String c_ip = maConnection.getInetAddress().toString();
			int c_port = maConnection.getPort();
			System.out.format("[Serveur] : Arr. Client IP %s sur %d\n", c_ip, c_port);
			System.out.format("[Serveur ]: Creation du thread T_%d\n", c_port);

			new Thread(new ServiceClient(maConnection, "T_" + c_port)).start();
		}
	}
	public static void main(String[] args) throws IOException {
		Repartiteur connectionManager = new Repartiteur();
		connectionManager.execute();
	}

}
