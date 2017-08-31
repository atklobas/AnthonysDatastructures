package collections;


//don't use null with heaps, that's...undefined

//without modifying this class/package every 'object' in datastore can be guaranteed to be type E
//any objects loaded into this need to be able to figure out thier relative order
public class Heap<E extends Comparable<E>> extends AbstractArrayList<E>{
	public static final boolean testing=false;
	
	
	/**
	 * 
	 * @param InitialSize how large to make the intial buffer
	 */
	public Heap(int initialSize){
		super(initialSize);
	}
	public Heap(){
		super();
	}
	@SuppressWarnings("unchecked")
	private void percolateUp(int index){
		
		int parentIndex=(index-1)/2;
		
		if(((E)dataStore[index]).compareTo((E)dataStore[parentIndex])<0){
			if(testing){
			  System.out.println("moving "+index+" to "+parentIndex);
			  this.printTreeView();
			  System.out.println("\n\n\n");
			}
			Object temp=dataStore[index];
			dataStore[index]=dataStore[parentIndex];
			dataStore[parentIndex]=temp;
			percolateUp(parentIndex);
		}
		
	}
	@SuppressWarnings("unchecked")
	private void percolateDown(int index){
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
			swapindex=((E)dataStore[child1]).compareTo((E)dataStore[child2])<0?child1:child2;
		}
		
		//one of the children is a candidate, and less than current, swap 'em
		if(swapindex!=-1&&((E)dataStore[swapindex]).compareTo((E)dataStore[index])<0){
			Object temp=dataStore[index];
			dataStore[index]=dataStore[swapindex];
			dataStore[swapindex]=temp;
			percolateDown(swapindex);
		}
	}
	
	@Override
	public boolean add(E toAdd) {
		if(this.lastItem>=this.dataStore.length){
			this.expandArray();
		}
		this.dataStore[this.lastItem]=toAdd;
		this.percolateUp(this.lastItem);
		this.lastItem++;
		return true;
	}
	@Override
	@SuppressWarnings("unchecked")
	E remove(int index) {
		E toReturn=(E) this.dataStore[index];
		this.dataStore[index]=null;
		this.percolateDown(index);
		return toReturn;
	}
	
	@Override
	public boolean remove(Object toRemove) {
		return this.remove(this.indexOf(toRemove))!=null;
	}


	
	
	public void printTreeView(){
		//depth and tabs could be calculated with binary arithmetic
		int depth=(int) Math.ceil(Math.log(this.lastItem+1)/Math.log(2));
		int tabs=(int)(Math.pow(2, depth-1));
		//System.out.printf("depth=%d\tleafs=%d%n", depth,tabs);
		int index=0;

		for(int i=1,inline=1;i<=depth;i++,inline*=2){
			for(int lineindex=0;lineindex<inline;lineindex++){
				for (int j=lineindex==0?1:0;j<tabs;j++){
					System.out.print("\t");
					if((lineindex)!=0){
						System.out.print("\t");
					}
				}
				if(index<this.dataStore.length){
					System.out.print(this.dataStore[index++]);
				}else{
					//sometimes it's useful to know if it's a stored null, or a index not in the array
					System.out.print("unaloc");
				}
			}
			System.out.println();
			tabs=tabs>>1;
			
		}
		
		
		
	}

}
