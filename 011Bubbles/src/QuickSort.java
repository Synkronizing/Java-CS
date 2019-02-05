import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	public void quicksort(int[] A, char[]B) {
		quicksort(A,B,0,A.length-1);
	}
	private void quicksort(int[] A,char[] B,int low, int high) {
		if(low < high+1) {
			int p = partition(A,B, low, high);
			quicksort(A,B, low, p-1);
			quicksort(A,B, p+1,high);
		}
	}
	private void swap(int[] A,char[]B, int index1,int index2) {
		int temp = A[index1];
		char temp2 = B[index1];
		A[index1]=A[index2];
		A[index2]= temp;
		B[index1]=B[index2];
		B[index2]= temp2;
		
	}
	//returns random pivot index between low and high inclusive.
	private int getPivot(int low, int high) {
		Random rand = new Random();
		return rand.nextInt((high - low)+1)+low;
	}
	//moves all n < pivot to left of pivot and all n > pivot 
	// to the right of pivot, then returns pivot index.
	private int partition(int[] A,char[]B, int low, int high) {
		swap(A,B, low, getPivot(low,high));
		int border = low +1;
		for(int i = border;i<= high;i++) {
			if(A[i]< A[low]) {
				swap(A,B, i, border++);
			}
		}
		swap(A,B, low, border-1);
		return border-1;
	}
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		int[] A = {9,0,1,3,4,5,2,9,8,7,6,5,9,1,0,9};
		char[] B = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
		System.out.println(Arrays.toString(A));
		qs.quicksort(A,B);
		for(int i=0;i<A.length;i++) {
			System.out.println(B[i]+" :"+A[i]);
		}
	}
	
}
