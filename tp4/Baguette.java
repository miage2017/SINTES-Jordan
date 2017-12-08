package tp4;


public class Baguette {
	
	private boolean pris;
	
	public Baguette() {
		pris=false;
	}
	
	public synchronized void prendre () {
		pris=true;
	}
	
	public synchronized void depose () {
		pris=false;
	}

	public boolean isPris() {
		return pris;
	}

	public void setPris(boolean pris) {
		this.pris = pris;
	}
	
	
}
