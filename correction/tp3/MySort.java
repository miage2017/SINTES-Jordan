package correction.tp3;

import java.util.Arrays;

public class MySort {

public static void mSort(int[] a, int AvailCPU, String branch) throws InterruptedException  {
    if (a.length >= 2) {
	// Decoupe le tableau en 2 left and right
	int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
	int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
	
	// Only one cpu we perform a recursive but sequential  sort
	if (AvailCPU <= 1)
	    {
		mSort(left, AvailCPU, branch+"0");
		mSort(right, AvailCPU, branch+"1");
	    }
	// If 2++ Cpu we create a thread to sort left and right
	else
	    {
		Thread lThread = new Thread(new Sorter(left,  AvailCPU / 2, branch+"0" ));
		Thread rThread = new Thread(new Sorter(right, AvailCPU / 2, branch+"1" ));
		
		lThread.start();
		rThread.start();
		
		lThread.join();
		rThread.join();
	    } 
	// fusionne les tableaux left et right
	// La fusion est sequentielle 
	fusion(left, right, a);
    }
}
}
