package collections;

import java.util.NoSuchElementException;
import java.util.Queue;

//without modifying this class/package every 'object' in datastore can be guaranteed to be type E
@SuppressWarnings("unchecked")

//wow that is an ugly class declaration
public class PriorityQueue<E extends Comparable<E>> extends Heap<E> implements Queue<E>{

	public PriorityQueue(int InitialSize) {
		super(InitialSize);
	}
	public PriorityQueue() {
		super();
	}

	@Override
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
	public E peek() {
		return (E)this.dataStore[0];
	}

	@Override
	public E poll() {
		E ret=(E)this.dataStore[0];
		
		// removes first element, then restores heap property
		this.dataStore[0]=null;
		this.percolateDown(0);
		return ret;
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
