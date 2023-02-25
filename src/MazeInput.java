import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeInput {
    private int[][] maze;

    public void FileInput (String fileName) throws FileNotFoundException{
        //           windows: src\\
        //               mac: src/
        File file = new File("src/" + fileName);
        Scanner scan = new Scanner(file);

        int mazeSize = scan.nextInt();
        maze = new int[mazeSize][mazeSize];
        scan.nextLine();

        for (int i = 0; i < mazeSize; i++) {
            String row = scan.nextLine();
            for (int j = 0 ; j < mazeSize; j++) {
                if (row.charAt(j) == 'S') {
                    maze[i][j] = 2;
                }
                else if (row.charAt(j) == 'G') {
                    maze[i][j] = 3;
                }
                else if (row.charAt(j) == '.') {
                    maze[i][j] = 0;
                }
                else if (row.charAt(j) == '#') {
                    maze[i][j] = 1;
                }
            }
        }
        scan.close();
    }

    public int[][] getMaze() {
        return maze;
    }
 

}
