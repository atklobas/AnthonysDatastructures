package graphs;

public interface Graph {
	/**
	 * This should give some estimation of the length of the best path
	 * this should not overestimate the length, if there is no heuristic return 0; 
	 * 
	 * @param source the node being traveled from
	 * @param end the node being traveled to
	 * @return the estimation
	 */
	default public int getHeuristic(GraphNode start, GraphNode end){
		return 0;
	}
	
	/**
	 * Used to get an initial node for traversal problems
	 * @return the node to start traversal problems with
	 */
	public GraphNode getStart();
	/**
	 * Used to get a destination for traversal problems
	 * @return the node to get to in traversal problems
	 */
	public GraphNode getEnd();
}
