package collections;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author anthony
 * 
 * I noticed that Heap, PriorityQueue, ArrayStack, and ArrayList all use 
 * resizing Object arrays  as a back end and thought: why replicate code? 
 * 
 * the internals will be package protected (no modifier) to reduce 
 * verbosity because it's as good as private ouside the package, and
 * I (or whatever poor soul chose to use this) should have complete
 * understanding of the implementation and not do things that break it
 */
public abstract class AbstractArrayList<E> extends AbstractCollection<E>implements Collection<E> {
	/*
	 * I know using type Object rather than E is potentially bad
	 * but java wont instantiate a type E array, even openjdk does it like this
	 * 
	 * no modifier = package protected, this was done intentionally(for PriorityQueue) 
	 */
	Object[] dataStore;
	int lastItem=0;

	/**
	 * 
	 * @param InitialSize how large to make the intial buffer
	 */
	public AbstractArrayList(int intialSize){
		dataStore = new Object[intialSize];
	}
	public AbstractArrayList(){
		this(10);
	}
	
	
	//this isn't public because it wont necessarily be meaningful in subclasses
	int indexOf(Object toGet){
		int i=0;
		for(Object o:dataStore){
			if(o==toGet){
				return i;
			}
			i++;
		}
		return -1;
	}
	void expandArray(){
		//none of this grow by 1.5, lets do 2 instead
		this.dataStore=Arrays.copyOf(this.dataStore, this.dataStore.length<<1);
	}
	
	@SuppressWarnings("unchecked")
	E remove(int index) {
		E temp=(E)this.dataStore[index];
		System.arraycopy(this.dataStore, this.lastItem-index+1, this.dataStore, this.lastItem-index, index-1);
		this.dataStore[this.lastItem-1]=null;
		this.lastItem--;
		return temp;
	}
	
	
	public void printArray() {
		System.out.println(this.toString());
	}
	public String toString(){
		//this may seem wasteful, but if a null was added last it would be indistinguishable from having not been added
		/**/return Arrays.toString(Arrays.copyOf(this.dataStore,this.lastItem));
		/*/return Arrays.toString(this.dataStore);
		/**/
	}

	@Override
	public void clear() {
		for(int i=0;i<this.lastItem;i++){
			this.dataStore[i]=null;
		}
		this.lastItem=0;
		
	}
	
	
	@Override
	public boolean contains(Object toCheck) {
		for(int i=0;i<this.lastItem;i++){
			if(this.dataStore[i]==toCheck){
				return true;
			}
		}
		return false;
	}
	

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<E>{
		private int index=-1;
		@Override
		public boolean hasNext() {
			return index+1<size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			index++;
			return (E) dataStore[index];
		}
		
		@Override
		public void remove(){
			AbstractArrayList.this.remove(index);
			index--;
		}
		
	}
	

	@Override
	public int size() {
		return this.lastItem;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(this.dataStore, this.lastItem-1);
	}

}
