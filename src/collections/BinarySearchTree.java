package collections;

import collections.BinaryTree.Node;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	@Override
	public boolean add(E item) {
		if(root.item==null){
			root=generateNode(item);
		}else{
			subAdd(root, item);
		}
		size++;
		return true;
	}
	void subAdd(Node node, E item) {
		if(item.compareTo(node.item)<0){//item is less than current node;
			if(node.left.item!=null){
				subAdd(node.left,item);
			}else{
				node.left=generateNode(item);
			}
		}else{//greaterthan/equal
			if(node.right.item!=null){
				subAdd(node.right,item);
			}else{
				node.right=generateNode(item);
			}
		}
		setNodeHeights(node);
	}
	Node generateNode(E item){
		Node node=new Node();
		node.left=new Node();
		node.right=new Node();
		node.item=item;
		node.height=1;
		return node;
	}
	/*
	 * I couldn't figure out a way to make this method type safe, but the API says:
	 * ClassCastException - if the type of the specified element is incompatible with this collection
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		return getElement((Comparable<E>)o)!=null;
	}
	@SuppressWarnings("unchecked")
	public boolean remove(Object o){
		return remove(getElement((Comparable<E>)o));
	}
	
	//yep package protected polymorphism, ain't that a beaut.
	boolean remove(Node n){
		if(n!=null){
			if(n.left==null&&n.right==null){//if leaf, make null node
				n.item=null;
				n.left=n.right=null;
			}else if(n.left.item==null){//if only right child, make this node the right child
				n.item=n.right.item;
				n.left=n.right.left;
				n.right=n.right.right;
			}else if(n.right.item==null){//if only left child, make this node the left child
				n.item=n.left.item;
				n.right=n.left.right;
				n.left=n.left.left;
			}else{//remove min node on right side, and store its value, (if tree it well formed, it is guarenteed to remain well formed)
				n.item=removeMinimum(n.right);
			}
			setNodeHeights(n);
			return true;
		}
		return false;
	}
	
	Node getElement(Comparable<E> item){
		Node n=root;
		while(n.item!=null){
			if(item.equals(n.item)){
				return n;
			}
			if(item.compareTo(n.item)<0){
				n=n.left;
			}else{
				n=n.right;
			}
		}
		return null;
		
	}
	
	E removeMinimum(Node n){
		while(n.item!=null){
			if(n.left.item!=null){
				n=n.left;
			}else{
				E ret=n.item;
				remove(n);
				return ret;
			}
		}
		return null;
	}
	
	
	
	
}
