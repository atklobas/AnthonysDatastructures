package collections;

public class UpdatablePriorityQueue extends PriorityQueue<UpdatablePriorityQueue.Container>{
	
	public static abstract class Container implements Comparable<Container>{
		private int index=0;
		private UpdatablePriorityQueue queue=null;
		public final void updated(){
			queue.update(this);
		}
	}
	
	void percolateUp(int index){
		int parentIndex=(index-1)/2;
		
		if(((Container)dataStore[index]).compareTo((Container)dataStore[parentIndex])<0){
			
			Object temp=dataStore[index];
			dataStore[index]=dataStore[parentIndex];
			dataStore[parentIndex]=temp;
			
			//update the tracking info
			((Container)dataStore[index]).index=index;
			((Container)dataStore[parentIndex]).index=parentIndex;
			
			percolateUp(parentIndex);
		}
		
	}
	
	void percolateDown(int index){
		//don't want to deal with null, so swap null with last item
		if(this.dataStore[index]==null){
			this.lastItem--;
			this.dataStore[index]=this.dataStore[this.lastItem];
			this.dataStore[this.lastItem]=null;
		}
		int child1=2*index+1;
		int child2=2*index+2;
		int swapindex=-1;
		
		//make sure the first child is a candidate to swap with
		if(child1<this.dataStore.length&&this.dataStore[child1]!=null){
			swapindex=child1;
		}
		
		//make sure the second child is a candidate to swap with
		if(child2<this.dataStore.length&&this.dataStore[child2]!=null){
			//if they are both canidates, if either will work the smaller will, so use that one
			swapindex=((Container)dataStore[child1]).compareTo((Container)dataStore[child2])<0?child1:child2;
		}
		
		//one of the children is a candidate, and less than current, swap 'em
		if(swapindex!=-1&&((Container)dataStore[swapindex]).compareTo((Container)dataStore[index])<0){
			Object temp=dataStore[index];
			dataStore[index]=dataStore[swapindex];
			dataStore[swapindex]=temp;
			
			//update the tracking info
			((Container)dataStore[index]).index=index;
			((Container)dataStore[swapindex]).index=swapindex;
			
			percolateDown(swapindex);
		}
	}
	void update(Container item){
		if(item.equals(dataStore[item.index])){
			this.percolateUp(item.index);
			this.percolateDown(item.index);
		}
	}
	@Override
	public boolean add(Container toAdd) {
		
		toAdd.queue=this;
		toAdd.index=this.lastItem;
		if(this.lastItem>=this.dataStore.length){
			this.expandArray();
		}
		this.dataStore[this.lastItem]=toAdd;
		this.percolateUp(this.lastItem);
		this.lastItem++;
		return true;
	}
	

}
