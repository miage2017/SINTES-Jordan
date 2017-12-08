package correction.tp3;

import java.util.ArrayList;
import java.util.List;
public class PC {
	public static void main(String[] args) {
	    List<String> theJobs = new ArrayList<String>(); 
	    Thread theTask ;
	    for (int i=0; i<5; i++){
		Consommateur consume = new Consommateur(theJobs, String.format("C_%d",i));
		theTask=new Thread(consume); 
		theTask.start();
		Producteur produce = new Producteur(theJobs, String.format("P_%d",i));
		theTask =new Thread(produce); 
		theTask.start();		
	    }
	}
}