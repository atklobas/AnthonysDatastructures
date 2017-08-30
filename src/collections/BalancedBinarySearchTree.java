package collections;



public class BalancedBinarySearchTree<E extends Comparable<E>> extends BinarySearchTree<E>{
	/*
	 * I feel if java could have multiple layers of indirection this could be logic method with
	 * left/right just being calls that set it up :(
	 * 
	 * but instead it's copy/pasted :'(
	 */
	private void rotateRight(Node n){
		//swap items;
		E swap=n.item;
		n.item=n.left.item;
		n.left.item=swap;
		//rearrange links
		Node swapped=n.left;
		Node transferred=n.left.right;
		n.left=swapped.left;
		swapped.right=n.right;
		swapped.left=transferred;
		n.right=swapped;
		setNodeHeights(swapped);
		setNodeHeights(n);
	}
	private void rotateLeft(Node n){
		//swap items;
		E swap=n.item;
		n.item=n.right.item;
		n.right.item=swap;
		//rearrange links
		Node swapped=n.right;
		Node transferred=n.right.left;
		n.right=swapped.right;
		swapped.left=n.left;
		swapped.right=transferred;
		n.left=swapped;
		setNodeHeights(swapped);
		setNodeHeights(n);
	}
	
	void subAdd(Node node, E item) {
		super.subAdd(node, item);
		balanceTree(node);
		
	}
	
	private void balanceTree(Node node){
		if(Math.abs(node.left.height-node.right.height)>1){
			if(node.left.height>node.right.height){//left case
				if(node.left.right.height>node.left.left.height){//left right case
					rotateLeft(node.left);
				}
				rotateRight(node);			
			}else{//right case
				if(node.right.left.height>node.right.right.height){//left right case
					rotateRight(node.right);
				}
				rotateLeft(node);
			}
			
		}
		
	}
	
	//still feels wrong man
	@SuppressWarnings("unchecked")
	public boolean remove(Object o){
		return remove((Comparable<E>)o,root);
	}
	//This would be easier if i had parent links, but that wouldn't be any fun
	private boolean remove(Comparable<E> item,Node n){
		if(n.item!=null){
			boolean success=false;
			if(item.equals(n.item)){
				success=remove(n);
			}else if(item.compareTo(n.item)<0){
				success=remove(item,n.left);
			}else{
				success=remove(item,n.right);
			}
			if(n.item!=null){//current node might have been removed
				balanceTree(n);
			}
			setNodeHeights(n);
			return success;
		}
		return false;
	}
	
	
	E removeMinimum(Node n){
		if(n.left.item!=null){
			E ret=removeMinimum(n.left);
			balanceTree(n);
			setNodeHeights(n);
			return ret;
		}else{
			E ret=n.item;
			remove(n);
			return ret;
		}
	}
	public boolean isAVL(){
		return isAVL(root);
	}
	private boolean isAVL(Node node){
		if(node.item!=null){
			return isAVL(node.left)&&isAVL(node.right)&&Math.abs(getDepth(node.left)-getDepth(node.right))<2;
		}
		return true;
	}
	
	

}
