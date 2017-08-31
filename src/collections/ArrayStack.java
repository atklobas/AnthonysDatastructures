package collections;

import java.util.EmptyStackException;

public class ArrayStack<E> extends AbstractArrayList<E>{

	@Override
	public boolean add(E item) {
		this.push(item);
		return false;
	}

	@Override
	public boolean remove(Object item) {
		int index=search(item);
		if(index==-1){
			return false;
		}
		super.remove(this.lastItem-index);
		return true;
	}
	
	public boolean empty(){
		return this.isEmpty();
	}
	@SuppressWarnings("unchecked")
	public E peek(){
		if(!empty()){
			
			return (E)this.dataStore[this.lastItem];
		}
		throw new EmptyStackException();
	}
	@SuppressWarnings("unchecked")
	public E pop(){
		if(!empty()){
			this.lastItem--;
			E ret=(E)this.dataStore[this.lastItem];
			this.dataStore[this.lastItem]=null;
			return ret;
		}
		throw new EmptyStackException();
	}
	public E push(E item){
		this.dataStore[this.lastItem]=item;
		this.lastItem++;
		return item;
	}
	public int search(Object item){
		for(int i=1;i<=this.lastItem;i++){
			if(this.dataStore[this.lastItem-i]==item){
				return i;
			}
		}
		return -1;
	}

	@Override
	public E remove(int index) {
		return super.remove(this.lastItem-index);
	}

}
