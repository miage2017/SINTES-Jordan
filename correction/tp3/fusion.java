package correction.tp3;

public class fusion {
	public static void fusion(int[] left, int[] right, int[] a) {
	      int leftI = 0;
	      int rightI = 0;
	      for (int i = 0; i < a.length; i++) {
		 if (rightI >= right.length || (leftI < left.length && left[leftI] < right[rightI])) {
		     a[i] = left[leftI];
		     leftI++;			}
		else {
		    a[i] = right[rightI];
		    rightI++;
		}
	      }
	}
}
