//https://www.programiz.com/dsa/avl-tree src
 
package alg;
 
public class tree {
 
    private int opsCount = 0;
    private String[] commands = null;
 
    public uzel root = null;
 
    private int depth = -1;
    private int rotations = 0;
    private int consolidations = 0;
 
    public tree(int opsCount, String[] commands) {
        this.opsCount = opsCount;
        this.commands = commands;
    } 
 
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }
     
    public uzel leftRotate(uzel x) {
        uzel y = x.right;
        uzel child = y.left;
        y.left = x;
        x.right = child;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        updateMarkedNum(x);
        updateMarkedNum(y);
 
        return y;
    }
 
    public uzel rightRotate(uzel y) {
        uzel x = y.left;
        uzel child = x.right;
        x.right = y;
        y.left = child;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        updateMarkedNum(y);
        updateMarkedNum(x);
 
        return x;
    }
 
    public int height(uzel uzel) {
        if (uzel == null) {
            return 0;
        }
 
        return uzel.height;
    }
 
    public int marked(uzel uzel) {
        if (uzel == null) {
            return 0;
        }
 
        return uzel.markedNum;
    }
 
    public int getBalanceFactor(uzel uzel) {
        if (uzel == null) {
            return 0;
        }
 
        return height(uzel.left) - height(uzel.right);
    }
 
    public void updateMarkedNum(uzel uzel) {
        uzel.markedNum = Math.max(marked(uzel.left), marked(uzel.right));
 
        if (uzel.deleted) {
            uzel.markedNum += 1;
 
        }
    }
 
    public uzel adduzel(uzel uzel, int value) {
        if (uzel == null) {
            uzel = new uzel(value);
            return uzel;
        } 
 
        if (uzel.value > value) {
            uzel.left = adduzel(uzel.left, value);
 
        } else if (uzel.value < value) {
            uzel.right = adduzel(uzel.right, value);
 
        } else if (uzel.value == value){
            uzel.deleted = false;
            updateMarkedNum(uzel);
            return uzel;
        }
         
        uzel.height = 1 + max(height(uzel.left), height(uzel.right));
        int balanceFactor = getBalanceFactor(uzel);
 
        if (balanceFactor > 1) {
            if (value < uzel.left.value) {
                rotations += 1;
                return rightRotate(uzel);
                 
            } else if (value > uzel.left.value) {
                rotations += 2;
                uzel.left = leftRotate(uzel.left);
                return rightRotate(uzel);
            }
 
        } else if (balanceFactor < -1) {
            if (value > uzel.right.value) {
                rotations += 1;
                return leftRotate(uzel);
 
            } else if (value < uzel.right.value) {
                rotations += 2;
                uzel.right = rightRotate(uzel.right);
                return leftRotate(uzel);
            }
 
        } else {
            updateMarkedNum(uzel);
        }
 
        return uzel;
    }
 
    public uzel minValue(uzel uzel) {
        uzel current = uzel;
 
        while (current.left != null) {
            current = current.left;
        }
         
        return current;
    }
 
    public uzel maxValue(uzel uzel) {
        uzel current = uzel;
 
        while (current.right != null) {
            current = current.right;
        }
 
        return current;
    }
 
    public uzel removeTemp(uzel uzel, uzel remove) {
        if (remove.value < uzel.value) {
            uzel.left = removeTemp(uzel.left, remove);
 
        } else if (remove.value > uzel.value) {
            uzel.right = removeTemp(uzel.right, remove);
 
        } else { 
            if (uzel.left != null) {
                uzel = uzel.left;
 
            } else if (uzel.right != null) {
                uzel = uzel.right;
 
            } else {
                return null;
            }
        }
 
        uzel.height = 1 + max(height(uzel.left), height(uzel.right));
        int balanceFactor = getBalanceFactor(uzel);
 
        if (balanceFactor > 1) {
            if (getBalanceFactor(uzel.left) >= 0) {
                rotations += 1;
                uzel = rightRotate(uzel);
 
            } else {
                rotations += 2;
                uzel.left = leftRotate(uzel.left);
                uzel = rightRotate(uzel);
            }
 
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(uzel.right) <= 0) {
                rotations += 1;
                uzel = leftRotate(uzel);
 
            } else {
                rotations += 2;
                uzel.right = rightRotate(uzel.right);
                uzel = leftRotate(uzel);
            }
 
        } else {
            updateMarkedNum(uzel);
        }
 
        return uzel;
    }
 
    public uzel removeuzel(uzel uzel) {
        if (uzel == null) {
            return null;
        }
 
        if (uzel.deleted == true && uzel.markedNum == 1) {
            if (uzel.left == null && uzel.right == null) {
                uzel = null;
                return uzel;
 
            } else if (uzel.left != null) {
                uzel max = maxValue(uzel.left);
                uzel.deleted = false;
                uzel.value = max.value;
                uzel.left = removeTemp(uzel.left, max);
 
            } else {
                uzel min = minValue(uzel.right);
                uzel.deleted = false;
                uzel.value = min.value;
                uzel.right = removeTemp(uzel.right, min);
            }
 
        } else if (marked(uzel.left) != 0) {
            uzel.left = removeuzel(uzel.left);
 
        } else if (marked(uzel.right) != 0) {
            uzel.right = removeuzel(uzel.right);
 
        } 
 
        uzel.height = 1 + max(height(uzel.left), height(uzel.right));
        int balanceFactor = getBalanceFactor(uzel);
 
        if (balanceFactor > 1) {
            if (getBalanceFactor(uzel.left) >= 0) {
                rotations += 1;
                uzel = rightRotate(uzel);
 
            } else {
                rotations += 2;
                uzel.left = leftRotate(uzel.left);
                uzel = rightRotate(uzel);
            }
 
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(uzel.right) <= 0) {
                rotations += 1;
                uzel = leftRotate(uzel);
 
            } else {
                rotations += 2;
                uzel.right = rightRotate(uzel.right);
                uzel = leftRotate(uzel);
            }
 
        } else {
            updateMarkedNum(uzel);
        }
 
        return uzel;
    } 
 
    public uzel deleteuzel(uzel uzel, int value) {
        if (uzel == null) {
            return null;
        }
 
        if (value < uzel.value) {
            uzel.left = deleteuzel(uzel.left, value);
 
        } else if (value > uzel.value) {
            uzel.right = deleteuzel(uzel.right, value);
 
        } else {
            uzel.deleted = true;  
        }
 
        updateMarkedNum(uzel);
 
        return uzel;
    }
 
    public void buildGraph() {
        for (int i = 0; i < opsCount; ++i) {
            int command = Integer.parseInt(commands[i]);
 
            if (command > 0) {
                root = adduzel(root, command);
                depth = height(root) - 1;
 
                if (depth != -1 && (Math.floor(depth / 2) + 1) <= root.markedNum) {
                    consolidations += 1;
                    while (root != null && root.markedNum != 0) {
                        root = removeuzel(root);
                    }
                }
 
                depth = height(root) - 1;
                 
            } else {
                root = deleteuzel(root, Math.abs(command));
                depth = height(root) - 1;
 
                if (depth != -1 && (Math.floor(depth / 2) + 1) <= root.markedNum) {
                    consolidations += 1;
                    while (root != null && root.markedNum != 0) {
                        root = removeuzel(root);
                    }
                }
 
                depth = height(root) - 1;
            }
        }
    }
 
    public void printAnswer() {
        System.out.println(depth + " " + rotations + " " + consolidations);
    }
}
