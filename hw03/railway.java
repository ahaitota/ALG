package alg;
 
public class railway {
    public station root;
    public final int[] preorder;
    public int stationCount = 0;
    private int index = 0;
 
    public railway(int[] preorder) {
        this.preorder = preorder;
        int verticeCount = preorder.length;
        root = createRailway(preorder, verticeCount);
        int[] rootInfo = findFastTrack(root);
        stationCount = rootInfo[1];
    }
 
    public final station createRailway(int[] preorder, int bound) {
        if (index == preorder.length || preorder[index] > bound) {
            return null;
        }
 
        station root = new station(preorder[index++]);
        root.setLeft(createRailway(preorder, root.getValue()));
        root.setRight(createRailway(preorder, bound));
 
        return root;
    }
 
    private int[] findFastTrack(station station) {
        if (station == null) {
            return new int[]{0, 0};
        }
 
        int[] leftNode = findFastTrack(station.getLeft());
        int[] rightNode = findFastTrack(station.getRight());
 
        int maxDeep = Math.max(leftNode[0], rightNode[0]) + 1;
        int fastTrack = Math.max(leftNode[1] + rightNode[0], leftNode[0] + rightNode[1]) + 1;
 
        return new int[]{maxDeep, fastTrack};
    }
 
    public final void printAnswer(int result) {
        System.out.println(result);
    }
}
