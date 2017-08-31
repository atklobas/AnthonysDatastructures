package collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class ArrayList<E> extends AbstractArrayList<E> implements List<E>{
	public ArrayList(int initialSize){
		super(initialSize);
	}
	public ArrayList(){
		super();
	}
	@Override
	public boolean add(E toAdd) {
		if(this.lastItem>=this.dataStore.length){
			this.expandArray();
		}
		this.dataStore[this.lastItem]=toAdd;
		this.lastItem++;
		return true;
	}

	@Override
	public boolean remove(Object toRemove) {
		return this.remove(this.indexOf(toRemove))!=null;
	}

	@Override
	public void add(int index, E element) {
		if(index<0||index>this.size()){
			throw new IndexOutOfBoundsException();
		}
		if(this.lastItem>=this.dataStore.length){
			this.expandArray();
		}
		
		if(index!=this.lastItem){
			System.arraycopy(this.dataStore, index, this.dataStore, index+1, this.lastItem-index);
		}
		this.dataStore[index]=element;
		this.lastItem++;
		
		
		
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		
		for(E element:c){
			this.add(index++, element);
		}
		return true;
	}

	@Override
	public E get(int index) {
		return (E) this.dataStore[index];
	}

	@Override
	public int indexOf(Object o) {
		return super.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		for(int i=this.size()-1;i>=0;i--){
			if(o.equals(this.dataStore[i])){
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new ArrayListIterator(index);
	}
	private class ArrayListIterator implements ListIterator<E>{
		int current;
		int seen=-1;
		public ArrayListIterator(int startIndex){
			this.current=startIndex;
		}
		@Override
		public void add(E e) {
			ArrayList.this.add(seen, e);
			current++;
			
		}
		@Override
		public boolean hasNext() {
			return current<ArrayList.this.lastItem;
		}
		@Override
		public boolean hasPrevious() {
			return current>0;
		}
		@Override
		public E next() {
			seen=current;
			current++;
			return (E) ArrayList.this.dataStore[seen];
		}
		@Override
		public int nextIndex() {
			return current;
		}
		@Override
		public E previous() {
			current--;
			seen=current;
			return (E) ArrayList.this.dataStore[seen];
		}
		@Override
		public int previousIndex() {
			return current-1;
		}
		@Override
		public void remove() {
			current--;
			ArrayList.this.remove(seen);
			
			
		}
		@Override
		public void set(E e) {
			ArrayList.this.dataStore[seen]=e;
			
		}
		
		
	}

	public E remove(int index){
		return super.remove(index);
	}

	@Override
	public E set(int index, E element) {
		if(index<0||index>=this.size()){
			throw new IndexOutOfBoundsException();
		}
		E ret=(E) this.dataStore[index];
		this.dataStore[index]=element;
		return ret;
		
		
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if(fromIndex<0||fromIndex>=this.size()){
			throw new IndexOutOfBoundsException("fromIndex out of bounds");
		}if(toIndex<0||toIndex>=this.size()){
			throw new IndexOutOfBoundsException("toIndex out of bounds");
		}
		ArrayList<E> ret=new ArrayList<E>();
		ret.dataStore=Arrays.copyOfRange(this.dataStore, fromIndex, toIndex);
		return ret;
	}

}
