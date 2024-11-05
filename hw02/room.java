package alg;
 
public class room {
    private room left = null;
    private room right = null;
    private room previous = null;
 
    final private int price;
 
    private int beenHere = 0;
    private int boxesTotal = 0;
 
    public room(int price) {
        this.price = price;
    }
     
    public void setLeft(room left) {
        this.left = left;
    }
 
    public void setRight(room right) {
        this.right = right;
    }
 
    public void setPrevious(room previous) {
        this.previous = previous;
    }
 
    public room getLeft() {
        return this.left;
    }
 
    public room getRight() {
        return this.right;
    }
 
    public int getPrice() {
        return this.price;
    }
 
    public void addBox(int weight) {
        this.boxesTotal += weight;
        this.beenHere += 1;
    }
 
    public int getTotalWeight() {
        return this.boxesTotal;
    }
 
    public int getTotalPrice() {
        return this.price * this.beenHere;
    }
 
    public boolean isAvailable() {
        if ((this.left == null || this.left.boxesTotal != 0) && 
            (this.right == null || this.right.boxesTotal != 0) &&
            (this.previous == null || this.previous.getTotalWeight() == 0)) {
            return true;
        }
 
        return false;
    }
 
    public void removeBox(int weight) {
        this.boxesTotal -= weight;
        this.beenHere -= 1;
    }
}
