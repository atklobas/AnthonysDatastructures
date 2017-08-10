package collections;

import java.util.NoSuchElementException;
import java.util.Queue;



//wow that is an ugly class declaration
public class PriorityQueue<E extends Comparable<E>> extends Heap<E> implements Queue<E>{

	public PriorityQueue(int InitialSize) {
		super(InitialSize);
	}
	public PriorityQueue() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E element() {
		if(this.lastItem==0){
			throw new NoSuchElementException("No elements currently in this collection");
		}
		return (E)this.dataStore[0];
	}

	@Override
	public boolean offer(E e) {
		return this.add(e);//no capacity restrictions
	}

	@Override
	@SuppressWarnings("unchecked")
	public E peek() {
		return (E)this.dataStore[0];
	}

	@Override
	public E poll() {
		return this.remove(0);
	}

	@Override
	public E remove() {
		if(this.lastItem==0){
			throw new NoSuchElementException("No elements currently in this collection");
		}
		
		//why write the same code twice?
		return this.poll();
	}




}
