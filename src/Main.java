import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Type in the file name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        fileName = fileName + ".txt";

        MazeInput mazeInput = new MazeInput();

        try {
            mazeInput.FileInput(fileName);
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
        
        /*
         * This stores the indices of the 
         * Start state (x1, y1) 
         * and Goal state (x2, y2)
         */
        int maze[][] = mazeInput.getMaze();
        int x1 = -1, x2 = -1, y1 = -1, y2 = -1;

        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j] == 2) {
                    x1 = i;
                    y1 = j;
                } if (maze[i][j] == 3) {
                    x2 = i;
                    y2 = j;
                }
            }
        }

        System.out.println("Start: X = " + x1 + " Y = " + y1);
        System.out.println("End: X = " + x2 + " Y = " + y2);
        System.out.println("Manhattan distance: " + (Math.abs(x1 - x2) + Math.abs(y1 - y2)) + "\n");

        // Up, down, left, right movement
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        System.out.println("Possible moves from the current position:");

        for(int[] direction : directions){
            int next_x = x1 + direction[0];
            int next_y = y1 + direction[1];

            if(next_x >= 0 && next_y < maze.length && next_y < maze[0].length
                    && maze[next_x][next_y] != 1)
                System.out.println("X = " + next_x + " Y = " + next_y);
        }

        ArrayList<Integer> exploredPositions = new ArrayList<>();
        /* TESTING CODE SA BOT MOVEMENT
         * BUT THIS DOES IT ON A STRAIGHT LINE PA LANG HAHAH
         */
        exploredPositions.add(x1);
        exploredPositions.add(y1);
        do {
            System.out.println("----------------------");
            System.out.print("Type 1 to move: ");
            int next = scan.nextInt();
            System.out.println("----------------------");
            if(next == 1){
                x1++;
            }
            for (int i = 0; i < maze.length; i++){
                for (int j = 0; j < maze.length; j++){
                    if(maze[i][j] == 2){  // hinahanap yung start
                        maze[i][j] = 0;   // leave starting position
                        maze[x1][y1] = 2; // land on next position
                    }
                    System.out.print(maze[i][j] + " ");
                }
                System.out.println();
            }
            exploredPositions.add(x1);
            exploredPositions.add(y1);
        } while (maze[x1][y1] != maze[x2][y2]); // loop will keep on going until the position of S is the same with G
        scan.close();
       
        /*
         * pathRecord int[nMoves][2]
         * number of rows == number of moves
         * 1st column is x
         * 2nd column is y
         * 
         * The indices of every position that the Bot has explored
         * is stored inside pathrecord
         */
        int[][] pathRecord = new int[exploredPositions.size()][2];
        System.out.println("Path record");

        int row = 0, col = 1, i = 0;
        System.out.println("Row  Col");
        while (i < exploredPositions.size() && row < exploredPositions.size() && col < exploredPositions.size()){
            pathRecord[i][0] = exploredPositions.get(row);
            pathRecord[i][1] = exploredPositions.get(col);
            System.out.println(pathRecord[i][0] + " | " + pathRecord[i][1]);
            row += 2;
            col += 2; 
            i++;
        }
        System.out.println("Game done");
     
      
       
    }
}