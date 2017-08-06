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
		System.arraycopy(this.dataStore, this.lastItem-index+1, this.dataStore, this.lastItem-index, index-1);
		this.dataStore[this.lastItem-1]=null;
		this.lastItem--;
		return true;
	}
	
	public boolean empty(){
		return this.isEmpty();
	}
	public E peek(){
		if(!empty()){
			return (E)this.dataStore[this.lastItem];
		}
		throw new EmptyStackException();
	}
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

}
