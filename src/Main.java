import java.util.Random;

import collections.PriorityQueue;

public class Main {
	public static final int TEST_ELEMENTS=21;
	public static void main(String[] args){
		Main.testHeapSort();
	}
	/*
	 *  
	 */
	public static void testHeapSort(){
		PriorityQueue<Integer> heap=new PriorityQueue<Integer>();
		Random rand= new Random();
		
		//Add random elements to structure, and print out like a list;
		for(int i=0;i<TEST_ELEMENTS;i++){
			int toadd=rand.nextInt(200);
			heap.add(toadd);
			if(i>0){
				System.out.print(", ");
			}
			System.out.print(toadd);
		}
		System.out.println();
		
		//look at the pretty tree
		heap.printTreeView();
		
		//print out elements in order(hopefully)
		for(int i=0,prev=0;i<TEST_ELEMENTS;i++){
			int pop=heap.poll();
			if(pop<prev){
				System.err.printf("Error %d < $d at %d \n",pop, prev, i);
			}
			if(i>0){
				System.out.print(", ");
			}
			System.out.print(pop);
			prev=pop;
		}
		System.out.println();
	}
}
