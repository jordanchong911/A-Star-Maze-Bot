import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    public static void main(String[] args) {
        try {
            File file = new File("maze.txt");
            Scanner scanner = new Scanner(file);

            // Count the number of rows and columns in the maze
            int numRows = 0;
            int numCols = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numRows++;
                numCols = line.length();
            }

            // Create a 2D array to store the maze
            char[][] maze = new char[numRows][numCols];

            // Reset the scanner to read the file again
            scanner = new Scanner(file);

            // Populate the maze array
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int col = 0; col < numCols; col++) {
                    maze[row][col] = line.charAt(col);
                }
                row++;
            }

            // Print the maze array to the console
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    System.out.print(maze[i][j]);
                }
                System.out.println();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        
    }
}
