package correction.tp3;

public  class fragileCounter implements Runnable {
	private volatile Boolean terminer=false; 
	public  void seterminer() { terminer =true;} 
	
	public fragileCounter(){}; 
	public void run() {
		for (int i =0; !terminer; i++ )
			System.out.println(i);
		System.out.println("Ordre de terminer recu, terminer=" + terminer);
	}


	public static void main(String[] args) {
	fragileCounter myCounter= new fragileCounter(); 
	Thread mT= new Thread(myCounter);
	mT.start(); 
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	myCounter.seterminer(); 
}
}