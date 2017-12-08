package tp4;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Philosophe implements Runnable {
	
	int idPhilo;
	Baguette gauche;
	Baguette droite;
	
	public Philosophe(int idPhilo, Baguette gauche, Baguette droite) {
		this.idPhilo = idPhilo;
		this.gauche = gauche;
		this.droite = droite;
	}	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			if (!gauche.isPris()) {
				gauche.prendre();
			}
			if (!droite.isPris()) {
				droite.prendre();
			}
		
			if(droite.isPris() && gauche.isPris()) {
				System.out.println("Je mange");
				droite.depose();
				gauche.depose();
			}
		}
		

	}
	
	public static void main(String[] args) {
		int nbMax= 5;
		HashMap<Integer, Baguette> baguettes = new HashMap<>();
		List<Thread> philo = new ArrayList();
		
		for (int i = 0; i < nbMax; i++) 
		{
			baguettes.put(i, new Baguette());
		}
		
		for (int i = 0; i < nbMax; i++) 
		{
			if(i == nbMax)
			{
			
				philo.add(new Thread(new Philosophe(i, baguettes.get(i),  baguettes.get(0))));
			}else {
				philo.add(new Thread(new Philosophe(i, baguettes.get(i),  baguettes.get(i+1))));
			}
		}
		
	
		for(Thread t : philo)
		{
			t.start();
		}
	}



	

}
