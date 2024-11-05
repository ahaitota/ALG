public package alg;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        String[] str = reader.readLine().split(" ");
        int fieldSize = Integer.parseInt(str[0]);
        int parkSize = Integer.parseInt(str[1]);
        int rocksCount = Integer.parseInt(str[3]);
 
        int[][] buffer = new int[fieldSize][fieldSize];
 
        for (int i = 0; i < fieldSize; i++) {
            String[] gridRow = reader.readLine().split(" ");
            for (int j = 0; j < fieldSize; j++) {
                buffer[i][j] = Integer.parseInt(gridRow[j]);
            }
        }
 
        int[][] forestSum = new int[fieldSize + 1][fieldSize + 1];
        int[][] rocksSum = new int[fieldSize + 1][fieldSize + 1];
 
        for (int i = 1; i <= fieldSize; i++) {
            for (int j = 1; j <= fieldSize; j++) {
                forestSum[i][j] = forestSum[i - 1][j] + forestSum[i][j - 1] - forestSum[i - 1][j - 1];
                rocksSum[i][j] = rocksSum[i - 1][j] + rocksSum[i][j - 1] - rocksSum[i - 1][j - 1];
 
                if (buffer[i - 1][j - 1] == 1) {
                    forestSum[i][j]++;
                }
                if (buffer[i - 1][j - 1] == 2) {
                    rocksSum[i][j]++;
                }
            }
        }
 
        int forestMax = 0;
 
        for (int i = 0; i <= fieldSize - parkSize; i++) {
            for (int j = 0; j <= fieldSize - parkSize; j++) {
                int totalForest = forestSum[i + parkSize][j + parkSize] - forestSum[i][j + parkSize] -
                        forestSum[i + parkSize][j] + forestSum[i][j];
                int totalRocks = rocksSum[i + parkSize][j + parkSize] - rocksSum[i][j + parkSize] -
                        rocksSum[i + parkSize][j] + rocksSum[i][j];
 
                if (totalRocks >= rocksCount) {
                    forestMax = Math.max(forestMax, totalForest);
                }
            }
        }
 
        System.out.println(forestMax);
        reader.close();
    }
} 
