package graphs;
import java.util.List;
public interface GraphNode {
	
	/*
	 * I thought of having a list of connections and a method for getting the distance given an
	 * index(fast but feels like it forces an implementation style) or given an object(not always
	 * easy a may lead to internal maps/multiple object comparisons)
	 * 
	 * Another option was to return a map/associative array, if this were php or python I would have
	 * 
	 * I think this leads to the most options for how things work internally 
	 */
	/**
	 * gets the connections to this node
	 * @return a list of connections
	 */
	public List<Connection> getConnections();
	public void setPrevious(GraphNode node);
	public GraphNode getPrevious();
	/**
	 * set's whether this is on the found path;
	 * @param onPath
	 */
	public void setOnPath(boolean onPath);
	public void setScanned(boolean scanned);
	public boolean isScanned();
	public interface Connection{
		public GraphNode getNode();
		public int getCost();
	}
}
