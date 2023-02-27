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