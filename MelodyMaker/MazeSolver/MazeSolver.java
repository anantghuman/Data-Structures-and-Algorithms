import java.util.List;

public abstract class MazeSolver {

    protected Maze maze;
    private boolean solved;
    private boolean solvable;

    public MazeSolver(Maze maze){
        this.maze = maze;
        makeEmpty();
        solvable = true;
    }

    abstract void makeEmpty();
    abstract boolean isEmpty();
    abstract void add(Square s);
    abstract Square next();

    boolean isSolved(){
            return solved;
    }

    void step(){
        if(isEmpty()) {
            solvable = false;
            solved = true;
            return;
        }
        Square next = next();

        if(next.getType() == Square.EXIT){
            solved = true;
            return;
        }

        List<Square> a = maze.getNeighbors(next);
        for (Square i: a){
            if(i.getType() != Square.WALL && i.getStatus() == Square.UNKNOWN) {
                i.setStatus(Square.WORKING);
                add(i);
            }
        }

        next.setStatus(Square.EXPLORED);
    }

    String getPath(){
        if(!solvable)
            return "not solvable";
        else if(solved)
            return "solved";
        else
            return "not solved";
    }

    void solve(){
    }

    public Maze getMaze() {
        return maze;
    }
}
