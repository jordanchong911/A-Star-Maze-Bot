package src;

import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Type in the file name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        fileName = fileName + ".txt";

        MazeInput mazeInput = new MazeInput();

        try {
            mazeInput.FileInput(fileName);
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
        scan.close();

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
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Start: X = " + x1 + " Y = " + y1);
        System.out.println("End: X = " + x2 + " Y = " + y2);
        System.out.println("Manhattan distance: " + (Math.abs(x1 - x2) + Math.abs(y1 - y2)));
    }
}