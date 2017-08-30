package collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import collections.BinaryTree.Node;

/**
 * 
 * @author anthony
 * 
 * Didn't know what strategy to use for the basic binary tree, so why not random add location?
 * 
 * 
 * 
 * 
 * There are 3 immediate ways I can think to structure trees like this:
 * 
 * (1) tree class directly contains item references to left and right tree 
 * 
 * (2) tree class that contains and acts as a tracker for a smart node class that handles implementation details
 * 
 * (3) tree class that handles all implementation details and the node acts as a struct
 * 
 * 
 * 
 * @param <E> the type of thing stored in this
 */
public class BinaryTree<E> extends AbstractCollection<E>implements Collection<E>{
	private static Random rand=new Random(); 
	//these are package protected so I don't have to copy and paste a bunch of code
	Node root;
	int size=0;
	class Node{
		E item;
		Node left;
		Node right;
		int height=0;
		public String toString(){
			printTreeView();
			return this.item+"";
		}
	}
	public BinaryTree(){
		root=new Node();
	}
	
	
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	/*In this implementation a node with a null item counts as no node
	 * this simplifies some checks and makes removing current node possible without
	 * finding/linking to its parent node
	 */
	@Override
	public boolean add(E item) {
		subAdd(root, item);
		size++;
		return true;
	}

	private void subAdd(Node node, E item) {
		
		if(node.item==null){
			node.left=new Node();
			node.right=new Node();
			node.item=item;
		}else{
			subAdd(rand.nextBoolean()?node.left:node.right,item);
		}
		setNodeHeights(node);
	}
	
	void setNodeHeights(Node node){
		if(node.item==null){
			node.height=0;
		}else{
			node.height=Math.max(node.left.height, node.right.height)+1;
		}
	}
	public void printTree(){
		printInorder(root);
	}
	private void printInorder(Node n){
		if(n.item!=null){
			printInorder(n.left);
			System.out.println(n.item);
			printInorder(n.right);
		}
	}
	
	public void printTreeView(){
		int depth=getDepth(root);
		int tabs=(int)(Math.pow(2, depth-1));
		for(int i=1;i<=depth;i++){
			for(int j=1;j<tabs;j++){
				System.out.print("\t");
			}
			printDepth(i,tabs*2,root);
			System.out.println();
			tabs=tabs>>1;
		}
	}
	private void printDepth(int depth,int tabs,Node n){
		if(depth>1){
			if(n!=null){
				printDepth(depth-1,tabs,n.left);
				for(int i=0;i<tabs;i++){
					System.out.print("\t");
				}
				printDepth(depth-1,tabs,n.right);
			}else{
				printDepth(depth-1,tabs,null);
				for(int i=0;i<tabs;i++){
					System.out.print("\t");
				}
				printDepth(depth-1,tabs,null);
			}
		}else{
			//if the node doesn't exist at all, print DNE, if it's empty print null
			if(n==null){
				System.out.print("DNE");
			}else{
				System.out.print(n.height+","+n.item);
			}
		}
	}
	//this acutally gets the height, (kind of)
		int getDepth(Node n){
			int depth=-1;
			
			if(n.item!=null){
				depth=getDepth(n.left);
				int temp=getDepth(n.right);
				depth=depth>temp?depth:temp;
			}
			return depth+1;
		}

}
