package graphs;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import collections.*;
import collections.UpdatablePriorityQueue.Container;
import graphs.GraphNode.Connection;
public class GraphAlgorithms {
	private static ArrayList<Observer> OBSERVERS=new ArrayList<Observer>();
	public static void addObserver(Observer o){
		OBSERVERS.add(o);
	}
	private static void observe(){
		for(Observer o:OBSERVERS){
			o.observe();
		}
	}
	
	
	
	
	public static void breadthFirstSearch(Graph g){
		Queue<GraphNode> toExplore=new Queue<GraphNode>();
		toExplore.add(g.getStart());
		
		while(!toExplore.isEmpty()){
			GraphNode current=toExplore.poll();
			
			
			List<Connection> connected=current.getConnections();
			current.setScanned(true);
		
			for(Connection c:connected){
				if(!c.getNode().isScanned()){
					toExplore.add(c.getNode());
					c.getNode().setPrevious(current);
					c.getNode().setScanned(true);
					if(c.getNode().equals(g.getEnd())){
						GraphNode back=c.getNode();
						while(back!=null){
							back.setOnPath(true);
							back=back.getPrevious();
							for(Observer o:OBSERVERS){
								o.observe();
							}
						}
						return;
					}
				}
			}
			
		}
		System.out.println("no path found");
		
	}
	

	private static class UpdatableNode extends UpdatablePriorityQueue.Container{
		private GraphNode node;
		private int cost;
		private int heuristic;
		public UpdatableNode(GraphNode node, int cost){
			this.node=node;
			this.cost=cost;
		}
		public UpdatableNode(GraphNode node, int cost, int heuristic){
			this.node=node;
			this.cost=cost;
			this.heuristic=heuristic;
		}
		
		@Override
		public int compareTo(Container o) {
			if(o instanceof UpdatableNode){
				return cost+heuristic-(((UpdatableNode)o).cost+((UpdatableNode)o).heuristic);
			}
			return 0;
		}
		public boolean equals(Object o){
			if(o instanceof UpdatableNode){
				UpdatableNode toCompare=(UpdatableNode)o;
				return node.equals(toCompare.node);
			}
			return false;
		}
		public int hashCode(){
			return node.hashCode();
			
		}
		
	}
	
	//I need to think about this
	public static void Astar(Graph g){
		UpdatablePriorityQueue toExplore=new UpdatablePriorityQueue();
		//wow this feels hacky
		HashMap<UpdatableNode,UpdatableNode> explored=new HashMap<UpdatableNode,UpdatableNode>();
		toExplore.add(new UpdatableNode(g.getStart(),0));
		
		while(!toExplore.isEmpty()){
			UpdatableNode current=(UpdatableNode)toExplore.poll();
			List<Connection> connected=current.node.getConnections();
			
			current.node.setScanned(true);

			
			for(Connection c:connected){
				int cost=c.getCost()+current.cost;
				int heur=(int)(g.getHeuristic(c.getNode(), g.getEnd()));
				System.out.println((cost+heur)+"="+cost+"+"+heur+":"+c.getNode());
				UpdatableNode existing=explored.get(new UpdatableNode(c.getNode(),0));
				if(existing!=null){				
					if(existing.cost>cost){
						existing.cost=cost;
						existing.node.setPrevious(current.node);
						existing.updated();
					}
				}else{
					UpdatableNode node=new UpdatableNode(c.getNode(),cost,heur);
					
					toExplore.add(node);
					explored.put(node, node);
					c.getNode().setPrevious(current.node);
					c.getNode().setScanned(true);
					if(c.getNode().equals(g.getEnd())){
						GraphNode back=c.getNode();
						while(back!=null){
							back.setOnPath(true);
							back=back.getPrevious();
							for(Observer o:OBSERVERS){
								o.observe();
							}
						}
						return;
					}
				}
			}
			
		}
		System.out.println("no path found");
	}
	
	private static class PathNode implements Comparable<PathNode>{
		private GraphNode node;
		private int cost;
		public PathNode(GraphNode node, int cost){
			this.node=node;
			this.cost=cost;
		}
		
		@Override
		public int compareTo(PathNode o) {
			if(o instanceof PathNode){
				return cost-((PathNode)o).cost;
			}
			return 0;
		}
		public boolean equals(Object o){
			if(o instanceof UpdatableNode){
				UpdatableNode toCompare=(UpdatableNode)o;
				return node.equals(toCompare.node);
			}
			return false;
		}
		public int hashCode(){
			return node.hashCode();
			
		}
		
	}
	
	
	public static void Dijkstra(Graph g){
		PriorityQueue<PathNode> toExplore=new PriorityQueue<PathNode>();
		toExplore.add(new PathNode(g.getStart(),0));
		
		while(!toExplore.isEmpty()){
			PathNode current=(PathNode)toExplore.poll();
			List<Connection> connected=current.node.getConnections();
			
			current.node.setScanned(true);
			observe();
			
			for(Connection c:connected){
				int cost=c.getCost()+current.cost;
				int heur=(int)(g.getHeuristic(c.getNode(), g.getEnd()));
				if(!c.getNode().isScanned()){
					PathNode node=new PathNode(c.getNode(),cost);
					toExplore.add(node);
					c.getNode().setPrevious(current.node);
					c.getNode().setScanned(true);
					if(c.getNode().equals(g.getEnd())){
						GraphNode back=c.getNode();
						while(back!=null){
							back.setOnPath(true);
							back=back.getPrevious();
							for(Observer o:OBSERVERS){
								o.observe();
							}
						}
						return;
					}
				}
			}
		}
		System.out.println("no path found");
	}
}
