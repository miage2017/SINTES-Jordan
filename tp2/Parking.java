package tp2;

import java.util.Random;

public class Parking {
	int capacite;
	
	public Parking (int cap) {
		this.capacite=cap;
	}
	
	public synchronized void enter() {
		System.out.println(Thread.currentThread().getName() + " arrive");
		while (capacite <=0) {
			System.out.println(Thread.currentThread().getName() + " attend...");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (capacite>0)
			capacite --;
			System.out.println(Thread.currentThread().getName() + " entre dans le parking, il reste " + capacite + " place(s)");
	}
	
	public void leave() {
		System.out.println(Thread.currentThread().getName() + " repart");
		capacite++;
	}

	
	public static void main(String[] args) {
		Parking park = new Parking(10);
		Random r = new Random();
		
		
		
		for (int i=0; i<20; i++) {
			try {
				Thread.sleep(r.nextInt(5)*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			new Thread(new Voiture("voiture " +i, park)).start();
		}
		
	}

	
}
