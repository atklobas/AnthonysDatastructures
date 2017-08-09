package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E> implements java.util.Queue<E>{
	private class Link{
		private Link(E item){
			this.item=item;
		}
		private Link next;
		private final E item;
		
	}
	private Link head=null;
	private Link tail=null;
	
	
	//queue methods

	@Override
	public boolean add(E item) {
		if(head==null){
			head=tail=new Link(item);
		}else{
			tail.next=new Link(item);
			tail=tail.next;
		}
		
		return true;
	}
	@Override
	public boolean offer(E item) {
		this.add(item);
		return true;
	}

	@Override
	public E element() {
		if(this.head==null){
			throw new NoSuchElementException();
		}
		return head.item;
	}
	@Override
	public E peek() {
		if(head!=null){
			return head.item;
		}
		return null;
	}

	@Override
	public E poll() {
		if(head!=null){
			E temp=head.item;
			head=head.next;
			return temp;
		}
		return null;
	}

	@Override
	public E remove() {
		if(this.head==null){
			throw new NoSuchElementException();
		}
		//was tempted to just call poll here
		E temp=head.item;
		head=head.next;
		return temp;
	}
	
	
	
	
	
	//collections methods
	

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.head==null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
