package src;

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
                //System.out.print(maze[i][j] + " ");
            }

            //System.out.println();
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

        /* TESTING CODE SA BOT MOVEMENT
         * BUT THIS DOES IT ON A STRAIGHT LINE PA LANG HAHAH
         */
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
                    if(maze[i][j] == 2){ // hinahanap yung start
                        maze[i][j] = 0; // leave starting position
                        maze[x1][y1] = 2; // land on next position
                    }
                    System.out.print(maze[i][j] + " ");
                }
                System.out.println();
            }
        } while (maze[x1][y1] != maze[x2][y2]); // loop will keep on going until the position of S is the same with G
        System.out.println("Game done");
        scan.close();
    }
}