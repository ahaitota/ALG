package alg;
 
public class uzel {
 
    public uzel left = null;
    public uzel right = null;
 
    public boolean deleted = false;
    public int markedNum = 0;
 
    public int value;
    public int height = 1; 
 
    public uzel(int value) {
        this.value = value;
    }
}
