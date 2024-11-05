package alg;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }
 
    public static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
        int rows = Integer.parseInt(params[0]);
        int cols = Integer.parseInt(params[1]);
 
        Sector[][] dpLR = new Sector[rows][cols];
        Sector[][] dpRL = new Sector[rows][cols];
        Sector[][] dpTotal = new Sector[rows][cols];
 
        for (int i = 0; i < rows; ++i) {
            String[] gridRow = br.readLine().split(" ");
            for (int j = 0; j < cols; ++j) {
                int currValue = Integer.parseInt(gridRow[j]);
                dpLR[i][j] = new Sector(0, currValue, currValue == 0);
                dpRL[i][j] = new Sector(0, currValue, currValue == 0);
                if (currValue != 0) {
                    dpTotal[i][j] = dpLR[i][j];
                }
            }
        }
 
        int maxValue = 0;
        int minDist = Integer.MAX_VALUE;
 
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dpLR[i][j].canWalk) {
                    int myVal = myValue(i, j, rows, cols, dpLR);
                    if (i == 0) {
                        if (j == 0) {
                            dpLR[i][j].value = myVal;
                            dpLR[i][j].dist = 1;
 
                        } else {
                            if (myVal >= dpLR[i][j - 1].value() + myVal) {
                                dpLR[i][j].value = myVal;
                                dpLR[i][j].dist = 1;
 
                            } else {
                                dpLR[i][j].value = dpLR[i][j - 1].value() + myVal;
                                dpLR[i][j].dist = dpLR[i][j - 1].dist() + 1;
                            }
                        }
 
                    } else {
                        if (j == 0) {
                            dpLR[i][j].value = dpTotal[i - 1][j].value() + myVal;
                            dpLR[i][j].dist = dpTotal[i - 1][j].dist() + 1;
 
                        } else {
                            if (dpTotal[i - 1][j].value() > dpLR[i][j - 1].value()) {
                                dpLR[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                dpLR[i][j].dist = dpTotal[i - 1][j].dist() + 1; 
 
                            } else if (dpTotal[i - 1][j].value() < dpLR[i][j - 1].value()) {
                                dpLR[i][j].value = dpLR[i][j - 1].value() + myVal;
                                dpLR[i][j].dist = dpLR[i][j - 1].dist() + 1;
 
                            } else {
                                if (dpTotal[i - 1][j].dist() < dpLR[i][j - 1].dist()) {
                                    dpLR[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                    dpLR[i][j].dist = dpTotal[i - 1][j].dist() + 1;
                                     
                                } else if (dpTotal[i - 1][j].dist() > dpLR[i][j - 1].dist()) {
                                    dpLR[i][j].value = dpLR[i][j - 1].value() + myVal;
                                    dpLR[i][j].dist = dpLR[i][j - 1].dist() + 1;
                                     
                                } else {
                                    if (dpTotal[i - 1][j].dist() == Integer.MAX_VALUE) {
                                        dpLR[i][j].value = 0;
                                        dpLR[i][j].dist = Integer.MAX_VALUE;
                                        dpLR[i][j].canWalk = false;
                                    } else {
                                        dpLR[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                        dpLR[i][j].dist = dpTotal[i - 1][j].dist() + 1;
                                    }
                                }
                            }
                        }
                    }
 
                    dpLR[i][j].checked = true;
                    if (dpRL[i][j].checked) {
                        if (dpLR[i][j].value() > dpRL[i][j].value()) {
                            dpTotal[i][j] = dpLR[i][j];
 
                        } else if (dpLR[i][j].value() < dpRL[i][j].value()) {
                            dpTotal[i][j] = dpRL[i][j];
 
                        } else {
                            if (dpLR[i][j].dist() < dpRL[i][j].dist()) {
                                dpTotal[i][j] = dpLR[i][j];
 
                            } else {
                                dpTotal[i][j] = dpRL[i][j]; 
                            }
                        }
 
                        if (i == rows - 1) {
                            if (dpTotal[i][j].value() > maxValue) {
                                maxValue = dpTotal[i][j].value();
                                minDist = dpTotal[i][j].dist();
                 
                            } else if (dpTotal[i][j].value() == maxValue) {
                                if (dpTotal[i][j].dist() < minDist) {
                                    minDist = dpTotal[i][j].dist();  
                                }
                            }
                        }
                    }
                } 
 
 
                int indexRight = cols - j - 1;
                if (dpRL[i][indexRight].canWalk) {
                    int myVal = myValue(i, indexRight, rows, cols, dpRL);
                    if (i == 0) {
                        if (indexRight == cols - 1) {
                            dpRL[i][indexRight].value = myVal;
                            dpRL[i][indexRight].dist = 1;
 
                        } else {
                            if (myVal >= dpRL[i][indexRight + 1].value() + myVal) {
                                dpRL[i][indexRight].value = myVal;
                                dpRL[i][indexRight].dist = 1;
 
                            } else {
                                dpRL[i][indexRight].value = dpRL[i][indexRight + 1].value() + myVal;
                                dpRL[i][indexRight].dist = dpRL[i][indexRight + 1].dist() + 1;
                            }
                        }
 
                    } else {
                        if (indexRight == cols - 1) {
                            dpRL[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                            dpRL[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;
 
                        } else {
                            if (dpTotal[i - 1][indexRight].value() > dpRL[i][indexRight + 1].value()) {
                                dpRL[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                dpRL[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1; 
 
                            } else if (dpTotal[i - 1][indexRight].value() < dpRL[i][indexRight + 1].value()) {
                                dpRL[i][indexRight].value = dpRL[i][indexRight + 1].value() + myVal;
                                dpRL[i][indexRight].dist = dpRL[i][indexRight + 1].dist() + 1;
 
                            } else {
                                if (dpTotal[i - 1][indexRight].dist() < dpRL[i][indexRight + 1].dist()) {
                                    dpRL[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                    dpRL[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;
                                     
                                } else if (dpTotal[i - 1][indexRight].dist() > dpRL[i][indexRight + 1].dist()) {
                                    dpRL[i][indexRight].value = dpRL[i][indexRight + 1].value() + myVal;
                                    dpRL[i][indexRight].dist = dpRL[i][indexRight + 1].dist() + 1;
                                     
                                } else {
                                    if (dpTotal[i - 1][indexRight].dist() == Integer.MAX_VALUE) {
                                        dpRL[i][indexRight].value = 0;
                                        dpRL[i][indexRight].dist = Integer.MAX_VALUE;
                                        dpRL[i][indexRight].canWalk = false;
                                    } else {
                                        dpRL[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                        dpRL[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;
                                    }
                                }
                            }   
                        }
                    }
 
                    dpRL[i][indexRight].checked = true;
                    if (dpLR[i][indexRight].checked) {
                        if (dpLR[i][indexRight].value() > dpRL[i][indexRight].value()) {
                            dpTotal[i][indexRight] = dpLR[i][indexRight];
 
                        } else if (dpLR[i][indexRight].value() < dpRL[i][indexRight].value()) {
                            dpTotal[i][indexRight] = dpRL[i][indexRight];
 
                        } else {
                            if (dpLR[i][indexRight].dist() < dpRL[i][indexRight].dist()) {
                                dpTotal[i][indexRight] = dpLR[i][indexRight];
 
                            } else {
                                dpTotal[i][indexRight] = dpRL[i][indexRight]; 
                            }
                        }
 
                        if (i == rows - 1) {
                            if (dpTotal[i][indexRight].value() > maxValue) {
                                maxValue = dpTotal[i][indexRight].value();
                                minDist = dpTotal[i][indexRight].dist();
                 
                            } else if (dpTotal[i][indexRight].value() == maxValue) {
                                if (dpTotal[i][indexRight].dist() < minDist) {
                                    minDist = dpTotal[i][indexRight].dist();  
                                }
                            }
                        }
                    }
                }
            }
        }
 
        System.out.println(maxValue + " " + minDist);
    }
 
    public static int myValue(int i, int j, int rows, int cols, Sector[][] table) {
        int sum = 0;
 
        if (j != 0) {
            if (table[i][j - 1].canWalk == false) {
                sum += table[i][j - 1].value;
            }
        }
 
        if (i != 0) {
            if (table[i - 1][j].canWalk == false) {
                sum += table[i - 1][j].value;
            }
        }
 
        if (j != cols - 1) {
            if (table[i][j + 1].canWalk == false) {
                sum += table[i][j + 1].value;
            }
 
        }
 
        if (i != rows - 1) {
            if (table[i + 1][j].canWalk == false) {
                sum += table[i + 1][j].value;
            }
 
        }
 
        return sum;
    }
}
