package collections;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> extends AbstractArrayList<E> implements List<E>{

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
		int index=this.getIndexOf(toRemove);
		if(index>=0){
			this.dataStore[index]=null;
			return true;
		}
		return false;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
