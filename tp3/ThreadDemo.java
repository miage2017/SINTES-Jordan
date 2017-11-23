package tp3;

import java.lang.*;

public class ThreadDemo implements Runnable {
	
	ThreadDemo(){
		Thread currThread = Thread.currentThread();
		Thread t = new Thread(this, "Admin Thread");
		
		System.out.println("current thread = " + currThread);
		System.out.println("thread created = " + t);
		
		t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().interrupted()) {
			
		}
		System.out.println("This is run() method");
	}
	public static void main(String[] args) {
		new ThreadDemo();
		Runnable c = null;
		Thread monThread = new Thread(c);
		monThread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monThread.interrupt();
	}

}
