package alg;
 
import java.util.LinkedList;
import java.util.Queue;
 
public class Pole {
    public Node start = null;
    public Node finish = null;
 
    public int rows;
    public int cols;
    public int colorCount;
 
    public int totalPathLen;
 
    public Node[][] grid;
 
    public Pole (Node[][] grid, int rows, int cols, int colorCount) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.colorCount = colorCount;
 
        start = grid[rows - 1][0];
        finish = grid[0][cols - 1];
 
        build();
 
        this.totalPathLen = bfs(start, 0, 0);
    }
 
    private void build() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j != 0) {
                    grid[i][j].appendNeighbour(0, grid[i][j - 1]);
                }
                if (i != 0) {
                    grid[i][j].appendNeighbour(1, grid[i - 1][j]);
                }
                if (j != cols - 1) {
                    grid[i][j].appendNeighbour(2, grid[i][j + 1]);
                }
                if (i != rows - 1) {
                    grid[i][j].appendNeighbour(3, grid[i + 1][j]);
                }
            }
        }
    }
 
    private int bfs(Node cell, int len, int colorCount) {
        Queue<Node> queue = new LinkedList<Node>(); 
        Queue<Integer> states = new LinkedList<>();
 
        cell.visited[colorCount] = true;
        queue.add(cell);
        states.add(colorCount);
 
        while(!queue.isEmpty()) {
            cell = queue.remove();
            int state = states.remove();
            colorCount = state;
 
            if (cell == finish) {
                return cell.getPathLength();
 
            }
 
            if (cell.value < 0) {
                colorCount = -1 * cell.getValue();
                state = colorCount;
            }
 
            for (int i = 0; i < 4; ++i) {
                if (cell.neighbours[i] != null
                && (cell.neighbours[i].value == state || cell.neighbours[i].value <= 0)) {
                    if (cell.neighbours[i].visited[colorCount] == false) {
                        cell.neighbours[i].visited[colorCount] = true;
                        queue.add(cell.neighbours[i]);
                        states.add(state);
                        cell.neighbours[i].pathLen = cell.pathLen + 1;
                    }
                }
            }
        }
 
        return 0;
 
    }
}
