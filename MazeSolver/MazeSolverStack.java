public class MazeSolverStack extends MazeSolver{

    MyStack workList;

    public MazeSolverStack(Maze maze){
        super(maze);
        workList = new MyStack();
    }
    @Override
    void makeEmpty() {
        workList = new MyStack();
    }

    @Override
    boolean isEmpty() {
        return workList.size() == 0;
    }

    @Override
    void add(Square s) {
        workList.push(s);
    }

    @Override
    Square next() {
        return workList.pop();
    }
}
