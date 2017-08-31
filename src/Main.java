import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

import collections.*;

public class Main {
	public static final int TEST_ELEMENTS=10;
	public static final int TEST_ELEMENT__MAX_SIZE=10024;
	public static final int RUNS=1;
	private static Random rand= new Random();
	public static void main(String[] args){
		testStack();
		//testArrayList();
		//testCollection(new ArrayList<Integer>());
		
	}
	public static void testArrayList(){
		ArrayList<String> list =new ArrayList<String>(8);
		list.add("the");
		list.add("quick");
		list.add("brown");
		list.add("fox");
		list.add("jumps");
		list.add("over");
		list.add("the");
		list.add("lazy");
		list.add("dog");
		list.printArray();
		
		list.remove("brown");
		list.printArray();
		list.add(2, "red");
		list.printArray();
		ListIterator<String> itr = list.listIterator();
		
		while(itr.hasNext()){
			String temp=itr.next();
					System.out.println(list.size()+" "+temp);
					if(temp=="red"){
						itr.remove();
					}
					if(temp=="lazy"){
						itr.add("sleeping");
					}
			
		}
		list.printArray();
		while(itr.hasPrevious()){
			String temp=itr.previous();
					System.out.println(list.size()+" "+temp);
					if(temp=="lazy"){
						itr.remove();
					}
					if(temp=="fox"){
						itr.set("cat");
					}
		}
		list.printArray();
		Collections.shuffle(list);
		list.printArray();
		for(int i=0;i<15;i++){
			String s=list.get(list.size()-1);
			list.remove(list.size()-1);
			list.add(list.size(), s);
			list.add(list.size(), s);
			list.printArray();
		}
	}
	public static void testTree(){
		System.out.println("_________________________________________________");
		
		BalancedBinarySearchTree<Integer> tree=new BalancedBinarySearchTree<Integer>();
		AVLtest(tree);
		AVLtest(tree);
		AVLtest(tree);
		AVLtest(tree);
		System.out.println("_________________________________________________");
		
		System.out.println("_________________________________________________");
		tree.printTreeView();
		
		tree.add(4);
		System.out.println("______________________1___________________________");
		tree.printTreeView();
		tree.add(7);
		System.out.println("_______________________2__________________________");
		tree.printTreeView();
		tree.add(4);
		System.out.println("______________________3___________________________");
		tree.printTreeView();
		tree.add(6);
		System.out.println("_______________________4__________________________");
		tree.printTreeView();
		tree.add(3);
		tree.add(8);
		tree.add(4);
		tree.add(5);
		
		tree.printTree();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("_______________________5__________________________");
		tree.printTreeView();
		System.out.println("_______________________6__________________________");
		tree.remove(4);
		tree.printTreeView();
		tree.remove(5);
		System.out.println("_______________________7__________________________");
		tree.printTreeView();
	}
	public static void AVLtest(BalancedBinarySearchTree<Integer> tree){
		for(int i=0;i<100;i++){
			tree.add(rand.nextInt(200));
			if(!tree.isAVL())
			System.out.println(tree.isAVL());
		}
		for(int i=0;i<100;i++){
			tree.remove(rand.nextInt(200));
			if(!tree.isAVL())
			System.out.println(tree.isAVL());
		}
	}
	public static void testCollection(Collection<Integer> toTest){
		
		System.out.println("testing add and remove");
		for(int i=0;i<5;i++){
			toTest.add(i);
			System.out.println(toTest);
		}
		
		Iterator<Integer> itr=toTest.iterator();
		while(itr.hasNext()){
			int val=itr.next();
			System.out.print(val+" ");
			if(val==3){
				System.out.print("(removing 3) ");
				itr.remove();
			}
		}
		System.out.println();
		System.out.println(toTest);
		System.out.println("removing 1");
		toTest.remove(1);
		System.out.println(toTest);
		System.out.println("testing clear()");
		toTest.clear();
		
		System.out.println(toTest);
		System.out.println("testing addall");
		toTest.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,4,3,2,1,2,3,4,3,2,1}));
		System.out.println(toTest);
		System.out.println("testing retainAll");
		toTest.retainAll(Arrays.asList(new Integer[]{2,3,4,5}));
		System.out.println(toTest);
		toTest.retainAll(Arrays.asList(new Integer[]{1,2,3}));
		System.out.println(toTest);
		System.out.println("emptying collection");
		toTest.removeAll(toTest);
		System.out.println(toTest);

	}
	public static void testSorts(){
		
		for(String s:new java.util.ArrayList<String>()){
			System.out.println(s);
		}
		
		for(int i=0,elements=10;i<15;i++,elements*=2){
		
		int[] testArray=generateRandom(elements);
		
		System.out.println("Testing with: n="+elements);
		//yes this feels hacky, but lamba methods are nice
		
		benchmarkSort(testArray.clone(),"radixSort",(toSort)->SortAlgorithms.radixSort(toSort));
		benchmarkSort(testArray.clone(),"quickSort",(toSort)->SortAlgorithms.quickSort(toSort));
		benchmarkSort(testArray.clone(),"stableSort",(toSort)->SortAlgorithms.stableSort(toSort));
		benchmarkSort(testArray.clone(),"mergeSort",(toSort)->SortAlgorithms.mergeSort(toSort));
		benchmarkSort(testArray.clone(),"heapSort",(toSort)->SortAlgorithms.heapSort(toSort));
		
		benchmarkSort(testArray.clone(),"bubbleSort",(toSort)->SortAlgorithms.bubbleSort(toSort));
		benchmarkSort(testArray.clone(),"insertionSort",(toSort)->SortAlgorithms.insertionSort(toSort));
		benchmarkSort(testArray.clone(),"selectionSort",(toSort)->SortAlgorithms.selectionSort(toSort));
		
		
		System.out.println("");
		
		
		}
		
	}
	
	private static void testQueue(){
		Queue<Integer> q=new Queue<Integer>();
		//Deque<Integer> q=new Deque<Integer>();
		
		System.out.println("testing add and remove");
		for(int i=0;i<5;i++){
			q.add(i);
			System.out.println(q);
		}
		Iterator<Integer> itr=q.iterator();
		while(itr.hasNext()){
			int val=itr.next();
			System.out.println(val);
			if(val==4){
				System.out.println("removing 4");
				itr.remove();
			}
		}
		System.out.println(q);
		System.out.println("testing poll");
		
		while(!q.isEmpty()){
			System.out.println(q.poll());
		}
		System.out.println(q);
		System.out.println("testing addall");
		q.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,4,3,2,1,2,3,4,3,2,1}));
		System.out.println(q);
		System.out.println("testing retainAll");
		q.retainAll(Arrays.asList(new Integer[]{2,3,4,5}));
		System.out.println(q);
		q.retainAll(Arrays.asList(new Integer[]{1,2,3}));
		System.out.println(q);
		System.out.println("emptying queue");
		while(!q.isEmpty()){
			System.out.println(q.poll());
			System.out.println(q);
		}
		
	}
	
	private interface Sort{
		public void sort(int[] toSort);
	}
	
	public static void benchmarkSort(int[] testArray,String name, Sort algo){
		
		long startTime= System.currentTimeMillis();
		for(int i=0;i<RUNS;i++){
			algo.sort(testArray);
		}
		long endTime= System.currentTimeMillis();
		//System.out.println(Arrays.toString(testArray));
		System.out.println(name+ " , "+(endTime-startTime)+"");
	}
	
	
	
	
	
	public static int[] generateRandom(int length){
		int[] returnArray=new int[length];
		for(int i=0;i<length;i++){
			returnArray[i]=rand.nextInt(TEST_ELEMENT__MAX_SIZE)-TEST_ELEMENT__MAX_SIZE/2;
		}
		return returnArray;
	}
	
	
	
	
	
	
	public static void testStack(){

		/*/Stack<Object> stack =new Stack<Object>();
		/*/
		ArrayStack<String> stack =new ArrayStack<String>();/**/
		stack.push("one");

		stack.push("two");

		stack.push("3");

		stack.push("4");
		stack.push("5");
		stack.printArray();
		stack.remove("5");
		stack.printArray();
		System.out.println("\"two\" is "+stack.search("two")+" deep in stack, removing it");
		stack.remove(stack.search("two"));
		stack.printArray();
		while(!stack.isEmpty()){
			
			System.out.println("\"3\" is "+stack.search("3")+" deep");
			stack.printArray();
			System.out.println("popped "+stack.pop());
			
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
		
		
		//Add random elements to structure, and print out like a list;
		for(int i=0;i<TEST_ELEMENTS;i++){
			int toadd=rand.nextInt(TEST_ELEMENT__MAX_SIZE);
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
