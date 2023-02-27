import java.util.*;
import java.util.concurrent.TimeUnit;

public class Search {
    public class Position{
        int x;
        int y;
        int g;
        int h;
        int num_explored;
        Position previous_position;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.num_explored=0;
            this.previous_position = null;
        }

        public int get_f(){
            return g+h;
        }
    }

    private int[][] maze;
    private Position start_position;
    private Position goal_position;
    private PriorityQueue<Position> unexplored;
    private ArrayList<Position> explored;

    public Search(int[][] maze, int start_x, int start_y, int goal_x, int goal_y) {
        this.maze = maze;
        this.start_position = new Position(start_x, start_y);
        this.goal_position = new Position(goal_x, goal_y);
        this.start_position.h = manhattan_distance(start_position, goal_position);
        this.unexplored = new PriorityQueue<>(Comparator.comparing(Position::get_f));
        this.unexplored.add(start_position);
        this.explored = new ArrayList<>();
    }

    public ArrayList<Position> search(){
        while(!unexplored.isEmpty()){
            Position current_position = unexplored.poll();

            if(current_position.x == goal_position.x && current_position.y == goal_position.y) {
                ArrayList<Position> solution = new ArrayList<>();
                solution = get_solution(current_position);

                print_path(solution);

                return solution;
            }

            explored.add(current_position);

            for(Position next_position : get_next_position(current_position)) {
                if(next_position.num_explored>0)
                    break;

                if (explored.contains(next_position))
                    continue;

                int expected_g = current_position.g + 1;

                if(!unexplored.contains(next_position) || expected_g < next_position.g){
                    next_position.g = expected_g;
                    next_position.h = manhattan_distance(next_position, goal_position);
                    next_position.num_explored++;
                    next_position.previous_position = current_position;
                    unexplored.add(next_position);
                }
            }

            print_path(explored);
            System.out.println("(" + current_position.x + "," + current_position.y + ")");

            /*try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
        return null;
    }

    private int manhattan_distance(Position pos1, Position pos2){
        return Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y);
    }

    private ArrayList<Position> get_next_position(Position current_position){
        ArrayList<Position> next_positions = new ArrayList<>();
        // Up, down, left, right movement
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for(int[] direction : directions){
            int next_x = current_position.x + direction[0];
            int next_y = current_position.y + direction[1];

            if (next_x >= 0 && next_x < maze[0].length && next_y >= 0 && next_y < maze.length)
                if(maze[next_y][next_x]==0 || maze[next_y][next_x]==2 || maze[next_y][next_x]==3) {
                    Position next_position = new Position(next_x, next_y);
                    next_positions.add(next_position);
                }
        }

        if(next_positions.size()==0)
            next_positions.add(current_position);

        return next_positions;
    }

    private ArrayList<Position> get_solution(Position current_position){
        ArrayList<Position> path = new ArrayList<>();

        while(current_position != null){
            path.add(0, current_position);
            current_position = current_position.previous_position;
        }

        return path;
    }

    private void print_path(ArrayList<Position> path){
        for(Position position : path)
            if(maze[position.y][position.x]==0)
                maze[position.y][position.x] = 9;

        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                switch(maze[i][j]){
                    case 0:
                        System.out.print(". ");
                        break;
                    case 1:
                        System.out.print("# ");
                        break;
                    case 2:
                        System.out.print("S ");
                        break;
                    case 3:
                        System.out.print("G ");
                        break;
                    case 9:
                        System.out.print("* ");
                        break;
                }
            }

            System.out.println();
        }

        for(int i=0; i<path.size(); i++){
            System.out.print("(" + path.get(i).x + "," + path.get(i).y + ")");

            if(i<path.size()-1){
                System.out.print("->");
            }
        }
    }
}
