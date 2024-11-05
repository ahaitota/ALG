package alg;
 
public class node {
 
    private node left = null;
    private node right = null;
 
    private int value;
 
    public node(int value) {
        this.value = value;
    }
 
    public node getLeft() {
        return this.left;
    }
 
    public node getRight() {
        return this.right;
    }
 
    public int getValue() {
        return this.value;
    }
 
    public void setLeft(node left) {
        this.left = left;
    }
 
    public void setRight(node right) {
        this.right = right;
    }
 
    public void setValue(int value) {
        this.value = value;
    }
     
}
