package ShortestPath;

/**
 * Class for constructing the path by backtracking
 */
public class GraphNode {
    // Tracking the previous node
    public GraphNode prev = null;
    public final int x;
    public final int y;

    /**
     * Constructor
     * @param x xloc of the node
     * @param y yloc of the node
     */
    public GraphNode(int x, int y){
        this.x = x;
        this.y = y;
    }
}
