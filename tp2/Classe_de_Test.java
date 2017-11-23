package tp2;

public class Classe_de_Test implements Runnable {
	String nom = "Toto";
	int minIter= 0;
	int maxIter= 1000;
	boolean direction=true;
	
	public Classe_de_Test(String nom, boolean dir) {
		this.nom=nom;
		this.direction=dir;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.format("Ici le thread %s, je débute!\n", nom);
		if(direction==true) {
			for (int i = 0; i < maxIter; i++) {
				System.out.format("[%s] dit je suis Ici %d\n" , nom, i);
			}
		}
		
		if(direction==false) {
			for (int i = 1000; i > minIter; i--) {
				System.out.format("[%s] dit je suis Ici %d\n" , nom, i);
			}
		}
		System.out.format("Ici le thread %s; je termine!\n", nom);
	}

	
	public static void main(String[] args) {
		String jobName= String.format("Job_%d", 0);
		String jobName2= String.format("Job_%d", 1);
		
		Classe_de_Test objetExec = new Classe_de_Test(jobName, true);
		Classe_de_Test objetExec2 = new Classe_de_Test(jobName2, false);
		
		Thread monJob = new Thread(objetExec);
		Thread monJob2 = new Thread(objetExec2);
		
		System.out.format("Creating thread %s\n", jobName);
		monJob.start();
		System.out.format("Creating thread %s\n", jobName2);
		monJob2.start();
		System.out.format("Thread principal terminé!\n");
	}

}
