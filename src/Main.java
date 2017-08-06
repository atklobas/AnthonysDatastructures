import java.util.Random;
import java.util.Stack;

import collections.ArrayStack;

import collections.PriorityQueue;

public class Main {
	public static final int TEST_ELEMENTS=21;
	public static void main(String[] args){
		
		/*/Stack<Object> stack =new Stack<Object>();
		/*/ArrayStack<Object> stack =new ArrayStack<Object>();/**/
		///stack.push(1);

		//stack.push(2);

		//stack.push(3);

		//stack.push(4);
		stack.push(5);
		stack.printArray();
		stack.remove(5);
		stack.printArray();
		while(!stack.isEmpty()){
			//stack.printArray();
			//System.out.println(stack.search(5));
			
			System.out.println(stack.pop());
			
		}
		
		
		//Main.testHeapSort();
	}
	
	
	
	
	
	
	/* This is not a particularly good heap sort as it's space complexity is O(n) 
	 * 
	 * psudocode for heapsort with space complexity O(1) (if done on an array):
	 * 
	 * 1:
	 * move all elements to beginning of array (so there is no null between elements)
	 * while counting number of elements
	 * 
	 * 
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