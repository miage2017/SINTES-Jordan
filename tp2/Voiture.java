package tp2;

import java.util.Random;

public class Voiture implements Runnable {
	private Parking park;
	private Random r;
	private String nom;
	
	public Voiture(String nom, Parking park) {
		this.nom = nom;
		this.park= park;
		r = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setName(nom);	
		park.enter();

		try {
			Thread.sleep(r.nextInt(10)*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		park.leave();
	}
}
