package collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<E> extends AbstractCollection<E> implements java.util.Deque<E>{
	private class Link{
		private Link(E item){
			this.item=item;
		}
		private Link(E item, Link next){
			this.item=item;
			this.next=next;
		}
		private Link next;
		private Link last;
		private final E item;
		
	}
	private Link head=null;
	private Link tail=null;
	private int count=0;
	
	
	
	//add and offer
	@Override
	public boolean add(E item) {
		if(head==null){
			head=tail=new Link(item);
			count=1;
		}else{
			count++;
			tail.next=new Link(item);
			tail.next.last=tail;
			tail=tail.next;
		}
		
		return true;
	}
	
	@Override
	public void addFirst(E item) {
		if(head==null){
			this.add(item);
		}else{
			count++;
			head=new Link(item, head);
			head.next.last=head;
		}
	}
	
	@Override
	public void addLast(E e) {
		this.add(e);
		
	}
	
	@Override
	public boolean offer(E item) {
		this.add(item);
		return true;
	}
	
	@Override
	public boolean offerFirst(E e) {
		this.addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		return this.offer(e);
	}
	
	//remove and poll
	@Override
	public E poll() {
		if(head!=null){
			count--;
			E temp=head.item;
			head=head.next;
			if(head!=null){
				head.last=null;
			}else{
				tail=null;
			}
			return temp;
		}
		return null;
	}
	
	@Override
	public E pollLast() {
		if(head!=null){
			count--;
			E temp=tail.item;
			tail=tail.last;
			if(tail!=null){
				tail.next=null;
			}else{
				head=null;
			}
			return temp;
		}
		return null;
	}
	
	@Override
	public E pollFirst() {
		return this.poll();
	}
	
	@Override
	public E remove() {
		E toReturn=poll();
		if(toReturn==null){
			throw new NoSuchElementException();
		}
		return toReturn;
	}
	
	@Override
	public E removeFirst() {
		return remove();
	}
	
	@Override
	public E removeLast() {
		E toReturn=pollLast();
		if(toReturn==null){
			throw new NoSuchElementException();
		}
		return toReturn;
	}
	

	//peek and get
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
	public E getLast() {
		if(this.head==null){
			throw new NoSuchElementException();
		}
		return tail.item;
	}
	@Override
	public E peekLast() {
		if(head!=null){
			return tail.item;
		}
		return null;
	}
	
	@Override
	public E getFirst() {
		return this.element();
	}


	@Override
	public E peekFirst() {
		return this.peek();
	}
	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public void push(E e) {
		addFirst(e);
		
	}

	
	@Override
	public boolean addAll(Collection<? extends E> collection) {
		for(E element:collection){
			this.add(element);
		}
		return true;
	}
	
	@Override
	public void clear() {
		head=tail=null;
		count=0;
		
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new DequeDescendingIterator(tail);
	}
	private class DequeDescendingIterator extends DequeIterator{
		public DequeDescendingIterator(Link end) {
			super(end);
		}
		@Override
		public E next() {
			E temp=current.item;
			current=current.last;
			return temp;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new DequeIterator(head);
	}
	private class DequeIterator implements Iterator<E>{
		Link current;
		Link last=null;
		public DequeIterator(Link start){
			current=start;
		}
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public E next() {
			last=current;
			current=current.next;
			return last.item;
		}
		@Override
		public void remove(){
			if(last.last!=null){
				last.last.next=last.next;
			}else{
				Deque.this.head=last.next;
			}
			if(last.next!=null){
				last.next.last=last.last;
			}else{
				Deque.this.tail=last.last;
			}
			count--;
		}
	}




	@Override
	public boolean removeFirstOccurrence(Object o) {
		return this.remove(o);
	}


	@Override
	public boolean removeLastOccurrence(Object o) {
		Link current=this.tail;
		for(int i=0;i<this.count;i++){
			if(o==current.item){
				if(current.last!=null){
					current.last.next=current.next;
				}
				if(current.next!=null){
					current.next.last=current.last;
				}
				return true;
			}
			current=current.last;
		}
		return false;
	}

	@Override
	public int size() {
		return count;
	}
	public String toString(){
		String toRet="<";
		for(E element:this){
			toRet+=element+", ";
		}
		if(toRet.length()>1){
			toRet=toRet.substring(0, toRet.length()-2);
		}
		toRet+=">";
		return toRet;
		
	}

	 public boolean retainAll(Collection<?> c) {
		         boolean modified = false;
		         Iterator<E> e = iterator();
		         while (e.hasNext()) {
		             if (!c.contains(e.next())) {
		                 e.remove();
		                 modified = true;
		             }
		         }
		         return modified;
		   }
	
	 public boolean contains(Object o) {
		          Iterator<E> e = iterator();
		         if (o==null) {
		             while (e.hasNext())
		                 if (e.next()==null)
		                     return true;
		         } else {
		             while (e.hasNext())
		                 if (o.equals(e.next()))
		                     return true;
		         }
		         return false;
		     }

}
