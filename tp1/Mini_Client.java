package tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Mini_Client {
	
	public static void main(String[] args) {
		Socket socket;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 12000);
			System.out.println("Demande de connexion...");
			
			BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			PrintWriter out = new PrintWriter (socket.getOutputStream());
			
			String message_distant = in.readLine();
			System.out.println(message_distant);
			
			Scanner sc = new Scanner(System.in);
			String message = null;
			
			while(message != "FINISH") {	
					System.out.println("Votre message : ");
					message = sc.nextLine();
					out.write(message);
					out.flush();
			}
			
			socket.close();
		
		
		System.out.println("Vous venez de vous déconnecter.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
