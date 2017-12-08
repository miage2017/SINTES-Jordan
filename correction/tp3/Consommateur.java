package correction.tp3;

import java.util.List;

public class Consommateur {
	public Consommateur(List<String> theJobs, String format) {
		// TODO Auto-generated constructor stub
	}

	private void cons(int i) throws InterruptedException{
	      synchronized (nosJobs)
	      {
	         while (nosJobs.size() == 0)
	         {
	            System.out.format("File vide, %s  is waiting\n",  iD);
	            nosJobs.wait();
	            System.out.format("%s is awake now \n",  iD);

	         }
	         String jobDone = nosJobs.remove(0);
	         System.out.format("%s prend le Job %s\n" ,iD, jobDone  );
	         System.out.print("File :");
	         for (String it: nosJobs)
	        	 System.out.format(" %s ",it);
	         System.out.println("");
		 nosJobs.notify();
	      }
	}
}
