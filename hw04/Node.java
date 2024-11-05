package alg;
 
public class Node {
    public Node[] neighbours;
    public int value;
 
    public boolean visited[];
    public int pathLen;
 
    public Node(int value, int colorCount) {
        this.value = value;
        this.visited = new boolean[colorCount + 1];
        this.pathLen = 0;
        this.neighbours = new Node[] {null, null, null, null};
    }
 
    public void appendNeighbour(int index, Node cell) {
        this.neighbours[index] = cell;
    }
 
    public int getValue() {
        return this.value;
    }
 
    public int getPathLength() {
        return this.pathLen;
    }
}
