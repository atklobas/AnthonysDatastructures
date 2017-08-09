package collections;


import java.util.NoSuchElementException;

/**
 * 
 * This class is simply to help with radix sort,
 * java cannot do: Queue<Integer>[] buckets=new Queue<Integer>[size];
 * so i need to make integers queues none parameterized 
 * 
 * this is copy/pasted from queue
 * 
 * @author anthony
 *
 */
public class IntegerQueue {
	private class Link{
		private Link(int item){
			this.item=item;
		}
		private Link next;
		private final int item;
		
	}
	private Link head=null;
	private Link tail=null;
	
	
	//queue methods
	public boolean add(int item) {
		if(head==null){
			head=tail=new Link(item);
		}else{
			tail.next=new Link(item);
			tail=tail.next;
		}
		
		return true;
	}

	public boolean offer(int item) {
		this.add(item);
		return true;
	}


	public int element() {
		if(this.head==null){
			throw new NoSuchElementException();
		}
		return head.item;
	}
	
	/**
	 * there is no null, -1 is likely to happen so I'll use min value as my control code
	 * @return item or Integer.MIN_VALUE
	 */
	public int peek() {
		if(head!=null){
			return head.item;
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * there is no null, -1 is likely to happen so I'll use min value as my control code
	 * @return item or Integer.MIN_VALUE
	 */
	public int poll() {
		if(head!=null){
			int temp=head.item;
			head=head.next;
			return temp;
		}
		return Integer.MIN_VALUE;
	}


	public int remove() {
		if(this.head==null){
			throw new NoSuchElementException();
		}
		int temp=head.item;
		head=head.next;
		return temp;
	}
	public boolean isEmpty() {
		return this.head==null;
	}

}
