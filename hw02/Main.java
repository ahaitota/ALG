package alg;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        String[] params = reader.readLine().split(" ");
        String[] weights = reader.readLine().split(" ");
        String[] edges = new String[3];
        String line = new String();
 
        int verticeCount = Integer.parseInt(params[0]);
        int boxesCount = Integer.parseInt(params[1]);
 
        int[] boxes = new int[boxesCount];
        for (int i = 0; i < boxesCount; ++i) {
            boxes[i] = Integer.parseInt(weights[i]);
        }
 
        int[][] graph = new int[verticeCount][verticeCount];
 
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            edges = line.split(" ");
            graph[Integer.parseInt(edges[0])][Integer.parseInt(edges[1])] = Integer.parseInt(edges[2]); 
        }
 
        storage storage = new storage(graph, boxes);
        storage.solve(0, 0, storage.entrance);
        storage.print();
        reader.close();
    }
}
