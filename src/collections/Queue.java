package collections;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Queue<E> extends AbstractCollection<E> implements java.util.Queue<E>{
	//packageProtected
	class Link{
		private Link(E item){
			this.item=item;
		}
		private Link next;
		//the interview question, remove current item from single linked list
		//the "solution" doesn't work if it's the last element or item is final :P
		private final E item;
		
	}
	private Link head=null;
	private Link tail=null;
	private int count=0;
	
	//queue methods

	@Override
	public boolean add(E item) {
		
		if(head==null){
			head=tail=new Link(item);
		}else{
			tail.next=new Link(item);
			tail=tail.next;
			
		}
		count++;
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
			count--;
			//this leaves head=null tail=something, always check head to see if list is empty
			return temp;
		}
		return null;
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
	public Iterator<E> iterator() {
		return new QueueIterator(head);
	}
	private class QueueIterator implements Iterator<E>{
		Link prev;
		Link current;
		public QueueIterator(Link start){
			prev=new Link(null);
			current= new Link(null);
			prev.next=current;
			current.next=start;
		}
		public boolean hasNext() {
			return prev.next!=null&&current.next!=null;
		}

		@Override
		public E next() {
			if(prev.next!=current.next){//check for removal
				prev=current;
			}
			current=current.next;
			return current.item;
		}
		@Override
		public void remove(){
			if(head==tail){
				head=tail=null;
				count=0;
				return;//dirty, i know
			}else if(prev.next==head){
				Queue.this.remove();
				current.next=head;
				prev.next=current;
				
			}else{
				if(prev.next==tail){
					tail=prev;
				}
				prev.next=prev.next.next;
				count--;
			}
			

			
		}
	}
	@Override
	public void clear() {
		head=tail=null;
		count=0;
		
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
	
	
}
