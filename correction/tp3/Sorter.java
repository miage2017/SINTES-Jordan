package correction.tp3;

public class Sorter implements Runnable {
    private int[] a;
    private int AvailCPU;
    private String branch;
    public Sorter(int[] a, int AvailCPU, String branch) {
		this.a = a;
		this.AvailCPU = AvailCPU;
		this.branch=branch;
    }

    public void run()  {
	try{
	    System.out.format("Thread %s sorting an array with size %d\n", branch , a.length);
	    MySort.mSort(a, AvailCPU, branch);
	    System.out.format("Thread %s done\n", branch);

	}
	catch(Exception e) {;}
    }
}