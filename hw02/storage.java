package alg;
 
public class storage {
    public final room[] rooms;
    public final room entrance;
    public final int[] boxes;
 
    public int minImbalance = 15000;
    public int minTime;
 
 
    public storage(int[][] graph, int[] boxes) {
        entrance = new room(0);
 
        int roomCount = graph.length;
        this.boxes = boxes;
 
        rooms = new room[roomCount];
        rooms[0] = entrance;
 
        int index = 0;
 
        for (int i = 0; i < roomCount; ++i) {
            for (int j = 0; j < roomCount; ++j) {
 
                if (graph[i][j] != 0) {
                    room room = new room(graph[i][j] + rooms[i].getPrice());
                    index += 1;
                    rooms[index] = room;
                    if (rooms[i].getLeft() == null) {
                        rooms[i].setLeft(room);
                        rooms[i].getLeft().setPrevious(rooms[i]);
                    } else {
                        rooms[i].setRight(room);
                        rooms[i].getRight().setPrevious(rooms[i]);
                    }
                }
 
            }
        }
    }
 
    public void solve(int boxIndex, int currImbalance, room room) {
        if (boxIndex == boxes.length) {
            if (currImbalance == minImbalance) {
                if (minTime > calcTime()) {
                    minTime = calcTime();
                }
            }
 
            if (currImbalance < minImbalance) {
                if (currImbalance + calcTime() <= minImbalance + minTime) {
                     
                }
 
                minImbalance = currImbalance;
                minTime = calcTime();
            }
             
            return;
        }
 
        if (room.isAvailable()) {
            room.addBox(boxes[boxIndex]);
            int newImbalance = calcImbalance();
            solve(boxIndex + 1, newImbalance, rooms[0]); 
            room.removeBox(boxes[boxIndex]);
        }
     
        if (room.getLeft() != null) {
            room nextRoom = room.getLeft();
            solve(boxIndex, currImbalance, nextRoom); 
        }
 
        if (room.getRight() != null) {
            room nextRoom = room.getRight();
            solve(boxIndex, currImbalance, nextRoom); 
        }
 
    }
 
    public int calcImbalance() {
        int imbalance = 0;
        for (room room : rooms) {
            if (room.getLeft() != null) {
                imbalance += Math.abs(room.getTotalWeight() - room.getLeft().getTotalWeight());
            }
 
            if (room.getRight() != null) {
                imbalance += Math.abs(room.getTotalWeight() - room.getRight().getTotalWeight());
            }
        }
 
        return imbalance;
    }
 
    public int calcTime() {
        int time = 0;
        for (room room : rooms) {
            time += room.getTotalPrice();
        }  
 
        return time;
    }
 
    public void print() {
        System.out.println(minImbalance + " " + minTime);
    }
}
