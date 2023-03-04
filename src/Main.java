import java.util.ArrayList;
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
        

        int maze[][] = mazeInput.getMaze();
        int x1 = -1, x2 = -1, y1 = -1, y2 = -1;

        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j] == 2) {
                    x1 = j;
                    y1 = i;
                } if (maze[i][j] == 3) {
                    x2 = j;
                    y2 = i;
                }

                System.out.print(maze[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("Start: X = " + x1 + " Y = " + y1);
        System.out.println("End: X = " + x2 + " Y = " + y2);
        System.out.println("Manhattan distance: " + (Math.abs(x1 - x2) + Math.abs(y1 - y2)) + "\n");

        Search search = new Search(maze, x1, y1, x2, y2);

        if(search.search()==null)
            System.out.print("No path found.");

        search.finalExploredCount();

        System.out.println();
        System.out.print("Type any key to end program: ");
        String next = scan.nextLine();

        if (next != null)
            System.out.println("The program has ended.");

        scan.close();
    }
}