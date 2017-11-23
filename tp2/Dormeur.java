package tp2;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dormeur implements Runnable {
	Array[] number;
	Array[] th;
	
	public Dormeur(){
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Je suis " + Thread.currentThread().getName() + " j'ai fini");
	}
	
	public void set_pred(Thread t) {
		
	}
	
	public static void main(String[] args) {
		Dormeur dorm = new Dormeur();
		
		Thread A = new Thread (dorm);
		Thread B = new Thread (dorm);
		Thread C = new Thread (dorm);
		
		A.setName("Thread A");
		B.setName("Thread B");
		C.setName("Thread C");
		
		try {
			C.start();
			C.join();
			B.start();
			B.join();
			A.start();
			A.join();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
