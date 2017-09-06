package image;

import java.awt.image.Raster;
import java.util.List;

import collections.ArrayList;
import graphs.GraphNode;
import graphs.GraphNode.Connection;

public class rasterGraph implements graphs.Graph{
	Raster raster;
	public rasterGraph(Raster raster){
		this.raster=raster;
	}
	
	private class rConnection implements Connection{
		Node n;
		int cost;
		public rConnection(Node n, int cost){
			this.n=n;
			this.cost=cost;
		}
		@Override
		public GraphNode getNode() {
			return n;
		}

		@Override
		public int getCost() {
			return cost;
		}
		
		public String toString(){
			return cost+n.toString();
		}
	}
	private class Node implements GraphNode{
		int index;
		int x, y;
		GraphNode previous=null;
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
			this.index=x+y*raster.getWidth();
		}
		@Override
		public List<Connection> getConnections() {
			ArrayList<Connection> list=new ArrayList<Connection>(4);
			//right angles
			if(isTraversable(x+1,y)){
				list.add(new rConnection(new Node(x+1,y), 100));
			}
			if(isTraversable(x-1,y)){
				list.add(new rConnection(new Node(x-1,y), 100));
			}
			if(isTraversable(x,y+1)){
				list.add(new rConnection(new Node(x,y+1), 100));
			}
			if(isTraversable(x,y-1)){
				list.add(new rConnection(new Node(x,y-1), 100));
			}
			//diagnols
			if(isTraversable(x+1,y+1)){
				list.add(new rConnection(new Node(x+1,y+1), 142));
			}
			if(isTraversable(x-1,y-1)){
				list.add(new rConnection(new Node(x-1,y-1), 142));
			}
			if(isTraversable(x-1,y+1)){
				list.add(new rConnection(new Node(x-1,y+1), 142));
			}
			if(isTraversable(x+1,y-1)){
				list.add(new rConnection(new Node(x+1,y-1), 142));
			}
			
			return list;
		}

		@Override
		public void setPrevious(GraphNode node) {
			this.previous=node;
			
		}

		@Override
		public void setScanned(boolean scanned) {
			
			raster.getDataBuffer().setElem(index, 0xff0000);
			
		}

		@Override
		public boolean isScanned() {
			return raster.getDataBuffer().getElem(index)==0xff0000;
		}
		public boolean equals(Object o){
			if(o instanceof Node){
				Node node=(Node)o;
				return node.x==x&&node.y==y;
			}
			
			return false;
			
		}
		public int hashCode(){
			//i can cheat a little here
			return index;
		}
		public String toString(){
			return "("+x+","+y+")";
		}
		@Override
		public GraphNode getPrevious() {
			return previous;
		}
		@Override
		public void setOnPath(boolean onPath) {
			raster.getDataBuffer().setElem(index, 0xff00);
			
		}
		
	}
	private Node createNode(int x, int y){
		return new Node(x,y);
	}
	private int getIndex(int x, int y){
		return x+y*raster.getWidth();
	}
	private boolean isTraversable(int x, int y){
		if(x>=0&&y>=0&&x<raster.getWidth()&&y<raster.getHeight()){
		return raster.getDataBuffer().getElem(getIndex(x,y))>0x0f0f0f;
		}
		return false;
	}
	@Override
	public GraphNode getStart() {
		return createNode(153,1);
	}

	@Override
	public GraphNode getEnd() {
		return createNode(172,320);
		//return createNode(168,320);
	}
	public int getHeuristic(GraphNode start, GraphNode end){
		if(start instanceof Node&&end instanceof Node){
			Node s=(Node)start;
			Node e=(Node)end;
			int deltaX=Math.abs(s.x-e.x)*100;
			int deltaY=Math.abs(s.y-e.y)*100;
			return (int)Math.sqrt(deltaX*deltaX+deltaY*deltaY);
		}
		return 0;
	}

}
