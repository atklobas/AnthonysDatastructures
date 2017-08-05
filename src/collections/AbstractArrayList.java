package collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author anthony
 * 
 * I noticed that Heap, PriorityQueue, and ArrayList all use a resizing  
 * Object arrays  as a back end and thought: why replicate code? 
 * 
 * the internals will be package protected (no modifier) to reduce 
 * verbosity because it's as good as private ouside the package, and
 * I (or whatever poor soul chose to use this) should have complete
 * understanding of the implementation and not do things that break it
 */
public abstract class AbstractArrayList<E> implements Collection<E> {
	/*
	 * I know using type Object rather than E is potentially bad
	 * but java wont instantiate a type E array, even openjdk does it like this
	 * 
	 * no modifier = package protected, this was done intentionally(for PriorityQueue) 
	 */
	Object[] dataStore;
	int lastItem=0;

	
	
	
	
	int getIndexOf(Object toGet){
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
	
	
	public void printArray() {
		System.out.println(Arrays.toString(this.dataStore));
		
	}
	
	
	
	
	
	//#################### beyond here are things needed to Implement Collection ####################
	 

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		// this is currently at best n log(n), but theoretically could be n
		boolean success=true;
		for(E toAdd : collection){
			success=success&add(toAdd);
		}
		return success;
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
	public boolean containsAll(Collection<?> collection) {
		for(Object e : collection){
			if(!this.contains(e)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return this.lastItem==0;
	}

	@Override
	public Iterator<E> iterator() {
		//TODO later
		return null;
	}
	

	

	@Override
	public boolean removeAll(Collection<?> collection) {

		for(Object toRemove : collection){
			remove(toRemove);
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO meh
		return false;
	}

	@Override
	public int size() {
		return this.lastItem;
	}

	@Override
	public Object[] toArray() {
		
		return Arrays.copyOf(this.dataStore, this.lastItem-1);
	}

	@Override
	public <T> T[] toArray(T[] dest) {
		int copylength=dest.length<this.lastItem?dest.length:this.lastItem;
		System.arraycopy(this.dataStore, 0, dest, 0, copylength);
		return dest;
	}

}
