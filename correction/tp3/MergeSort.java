package correction.tp3;

import java.util.*;   // for Random

public class MergeSort {
	private static final Random RAND = new Random(33);   // random number generator
	public static void main(String[] args) throws Throwable {
		int mTaille = 1000000;   // Taille de base
		int maxTrial=5;
		long totalTime=0 ;
		int maxCPU=8;
		for(cpuNum=1; cpuNum<=maxCPU;cpuNum*=2)
		{
		  totalTime=0;
		  for(int cTry=0; cTry< maxTrial; cTry++)
			{
			  int[] a = createRandomArray(mTaille);
			  long begin = System.currentTimeMillis();
			  MultiThreadSort(a);
			  long end= System.currentTimeMillis();
			  totalTime+= end -begin ;
			}
	System.out.printf("%d cpus, %10d elements  =>  %6d ms \n", cpuNum, mTaille, totalTime/maxTrial);
		}
	}
}