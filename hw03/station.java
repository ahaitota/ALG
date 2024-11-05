package alg;
 
public class station {
    private final int value;
    private station left;
    private station right;
    private boolean visited = false;
 
    public station(int value){
        this.value = value;
    }
 
    public void setLeft(station left) {
        this.left = left;
    }
 
    public void setRight(station right) {
        this.right = right;
    }
 
    public void setVisited() {
        this.visited = true;
    }
 
    public station getLeft() {
        return this.left;
    }
 
    public station getRight() {
        return this.right;
    }
 
    public boolean getVisited() {
        return this.visited;
    }
 
    public int getValue() {
        return this.value;
    }
}
