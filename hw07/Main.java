package alg;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
    public static int count = 0;
    public static String[] commands = null;
 
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        solution(reader);
        reader.close();
    }
 
    public static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
 
        count = Integer.parseInt(params[0]);
        int diff = Integer.parseInt(params[2]) - Integer.parseInt(params[1]);
 
        commands = new String[count];
 
        String[] letters = {"A", "B", "C", "D"};
 
        for (int i = 0; i < count; ++i) {
            String command = br.readLine();
            commands[i] = command;
        }
 
        long[][] array = new long[diff][count];
 
        for (int i = 0; i < diff; ++i) {
            for (int j = 0; j < count; ++j) {
                for (int k = 0; k < letters.length; ++k) {
                    String commandToCheck = commands[j] + letters[k];
                    int index = checkString(commandToCheck.substring(1));
                    if (index != -1) {
                        if (i == 0) {
                            array[i][index] += 1;
                        } else {
                            array[i][index] += 1 * array[i - 1][j];
                        }
                    }
                }
            }
        }
 
        long answer = 0;
 
        for (int i = 0; i < count; ++i) {
            answer += array[diff - 1][i];
        }
 
        System.out.println(answer);
    }
 
    public static int checkString(String str) {   
        for (int i = 0; i < count; ++i) {
            if (commands[i].equals(str)) {
                return i;
            }
        }
 
        return -1;
    }
 
}
