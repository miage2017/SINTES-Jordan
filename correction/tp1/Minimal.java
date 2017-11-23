package correction.tp1;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Minimal {
	private String hote = "127.0.0.1";
	private int port = 8500;
	private Scanner console_input;
	public Minimal() {}

	public void execute() {
		console_input = new Scanner(System.in);
		Socket laConnection = null;
		try {
			laConnection = new Socket(this.hote, this.port);
			PrintWriter ma_sortie = new PrintWriter(
					laConnection.getOutputStream(), true);
			System.out.format(" Contacting %s on %d\n", hote ,port);
			ma_sortie.println("Hello je suis : spammeur");
			System.out.println("entrer les données");
			while (true) {
				String data = console_input.next();
				ma_sortie.println(data);
				if (data.equals("end")){
					System.out.println("termine");
					laConnection.close();
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.format("Probleme de connection avec serveur fontionne : %s",e);
			System.exit(-1);
		}

	}
	public static void main(String[] args) {
		Minimal test = new Minimal();
		test.execute();
	}
}
