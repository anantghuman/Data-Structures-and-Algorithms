public class MazeSolverQueue extends MazeSolver {

    private MyQueue workList;
    public MazeSolverQueue(Maze maze) {
        super(maze);
    }

    @Override
    void makeEmpty() {
        workList = new MyQueue();
        workList.clear();
    }

    @Override
    boolean isEmpty() {return workList.isEmpty();}

    @Override
    void add(Square s) {
        workList.offer(s);
    }

    @Override
    Square next() {
        return (Square) workList.poll();
    }
}
