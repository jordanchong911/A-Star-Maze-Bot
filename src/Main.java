package src;

import java.util.Scanner;

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
        
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        

    }
}