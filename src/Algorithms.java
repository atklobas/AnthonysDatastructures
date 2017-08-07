import java.util.Arrays;
import java.util.Random;

import collections.PriorityQueue;

public class Algorithms {
	private static Random rand= new Random();
	/**
	 * This implementation:
	 * Runs in O(n log(n)) time and O(n) space
	 * 
	 * This algorithm is stable (if 2 components have the same 
	 * organizational value, they retain their respective order)
	 * 
	 * @param toSort array to be sorted
	 */
	public static void mergeSort(int[] toSort){
		mergeHelper(0,toSort.length,Arrays.copyOf(toSort, toSort.length),toSort);
	}
	private static void mergeHelper(int start,int end,int[] source,int[] dest){
		if(end-start>1){
			int mid=(start+end)/2;
			mergeHelper(start,mid,dest,source);
			mergeHelper(mid,end,dest,source);
			for(int i=start,i1=start,i2=mid;i<end;i++){
				if(i2>=end||(i1<mid &&source[i1]<=source[i2])){
					dest[i]=source[i1];
					i1++;
				}else{
					dest[i]=source[i2];
					i2++;
				}
			}
			
		}
	}
	
	/**
	 * This implementation:
	 * Runs in O(n log(n)) average time, O(n^2) time in worst case
	 * Runs in O(log(n)) average space, O(n) space in worst case
	 * space requirement is because of stack size
	 * 
	 * This algorithm is randomized, so 2 on the same array may
	 * take different amounts of time to complete
	 * 
	 * This algorithm is unstable
	 * 
	 * 
	 * @param toSort array to be sorted
	 */
	public static void quickSort(int[] toSort){
		
		QuickSortHelper(toSort,0,toSort.length);
		
	}
	//coming from outside edges should split large blocks of "same" in half 
	private static void QuickSortHelper(int[] toSort,int start,int end){
		if(end-start>1){
			
			int pivot=toSort[start+rand.nextInt(end-start)];
			
			int i=start;
			int j=end-1;
			while(j>=i){
				if(toSort[i]<pivot){
					i++;
				}else if(toSort[j]>pivot){
					j--;
				}else{
					int swap=toSort[i];
					toSort[i]=toSort[j];
					toSort[j]=swap;
					i++;
					j--;
				}

			}
			if(j>start)QuickSortHelper(toSort,start,j+1);
			if(i<end)QuickSortHelper(toSort,i,end);
		}

		
	}
	
	//if you have a list of the same elements it's O(n^2)
	private static void depQuickSortHelper(int[] toSort,int start,int end){
		if(end-start>1){
			
			int pivotIndex=(start+end)/2;//rand.nextInt(end-start);
			int pivot=toSort[pivotIndex];
			
			toSort[pivotIndex]=toSort[start];
			toSort[start]=pivot;
			int split=start+1;
			for(int i=split;i<end;i++){
				if(toSort[i]<pivot){
				int swap=toSort[split];
				toSort[split]=toSort[i];
				toSort[i]=swap;
				split++;
				}
			}
			
			toSort[start]=toSort[split-1];
			toSort[split-1]=pivot;
			depQuickSortHelper(toSort,start,split-1);
			depQuickSortHelper(toSort,split,end);
		}

		
	}
	
	/**
	 * This implementation:
	 * Runs in O(n log(n)) time and O(log(n)) space
	 * 
	 * I've never implemented Heapsort before, so doing it
	 * in place (for that sweet space efficiency) should be fun
	 * 
	 * This algorithm is unstable
	 * 
	 * This algorithm supposedly has larger constant factors 
	 * and is more likely to cause cache misses than quicksort
	 * 
	 * @param toSort array to be sorted
	 */
	public static void heapSort(int[] toSort){
		//this is just test code, since i already have a heap implemenation
		
		
		//my heap appears about half the speed of java.util, which could be explained by my adding being nlogn not n
		//java.util.PriorityQueue<Integer >heap=new java.util.PriorityQueue<Integer >();
		
		PriorityQueue<Integer> heap=new PriorityQueue<Integer>(10485762*3);
		for(int i:toSort){
			heap.add(i);
		}
		for(int i=0;!heap.isEmpty();i++){
			toSort[i]=heap.poll();
		}
		
	}
	
	
	
	/**
	 * This implementation:
	 * Runs in O(n^2) time and O(l) space
	 * 
	 * famously slow, though every swap is adjacent
	 * which might be useful for something
	 * 
	 * This algorithm is stable
	 * 
	 * @param toSort array to be sorted
	 */
	public static void bubbleSort(int[] toSort){
		for(int i=toSort.length-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(toSort[j]>toSort[j+1]){
					int temp=toSort[j];
					toSort[j]=toSort[j+1];
					toSort[j+1]=temp;
				}
			}
		}
		
		
	}
	
	/**
	 * This implementation:
	 * Runs in O(n^2) time and O(l) space
	 * 
	 * If you asked a random intro to cs student to
	 * write a sorting algorithm you'd get this is
	 * likely what you'd get
	 * 
	 * This algorithm is unstable
	 * 
	 * @param toSort array to be sorted
	 */
	public static void selectionSort(int[] toSort){
		for(int i=0;i<toSort.length;i++){
			int min=toSort[i];
			int index=i;
			for(int j=i;j<toSort.length;j++){
				if(toSort[j]<min){
					min=toSort[j];
					index=j;
				}
			}
			int temp=toSort[i];
			toSort[i]=toSort[index];
			toSort[index]=temp;
		}
	}
	
	/**
	 * This implementation:
	 * Runs in O(n^2) time and O(l) space
	 * 
	 * Considered one of the "better" n^2 sorts.
	 * This takes some advantage of already sorted
	 * data and is used in some libraries "stableSort"
	 * methods with merge sort for the lists/recurive
	 * calls smaller than some n (better constants)
	 * 
	 * This algorithm is stable
	 * 
	 * @param toSort array to be sorted
	 */
	public static void insertionSort(int[] toSort){
		for(int i=1;i<toSort.length;i++){
			for(int j=i;j>0&&toSort[j]<toSort[j-1];j--){
				int temp=toSort[j];
				toSort[j]=toSort[j-1];
				toSort[j-1]=temp;
			}
		}
	}
	

}
