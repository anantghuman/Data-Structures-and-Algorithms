import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {

    Queue<Note> notes;

    Melody(Queue<Note> song) {
        notes = song;
    }

    public Queue<Note> getNotes() {
        return notes;
    }

    double getTotalDuration() {
        Queue<Note> temp = new LinkedList<>();
        int size = notes.size();
        for (int i = 0; i < size; i++) {
            temp.offer(notes.peek());
            notes.offer(notes.poll());
        }
        boolean isRepeating = false;
        double time = 0;
        while (!temp.isEmpty()) {
            if (temp.peek().isRepeat()) {
                isRepeating = true;
                time += temp.poll().getDuration() * 2;
                while (isRepeating) {
                    if (!temp.peek().isRepeat())
                        time += temp.poll().getDuration() * 2;
                    else {
                        time += temp.poll().getDuration() * 2;
                        isRepeating = false;
                    }
                }
            } else
                time += temp.poll().getDuration();
        }
        return time;
    }

    public String toString() {
        String c = "";
        for (int i = 0; i < notes.size(); i++) {
            c += notes.peek() + "\n";
            notes.offer(notes.poll());
        }
        return c;
    }

    void changeTempo(double tempo) {
        int size = notes.size();
        for (int i = 0; i < size; i++) {
            notes.peek().setDuration(tempo * notes.peek().getDuration());
            notes.offer(notes.poll());
        }
    }

    void reverse() {
        Stack<Note> temp = new Stack<>();
        while (!notes.isEmpty()) {
            temp.push(notes.poll());
        }
        while (!temp.isEmpty()) {
            notes.offer(temp.pop());
        }
    }

    void append(Melody other) {
        while (!other.notes.isEmpty()) {
            this.notes.offer(other.notes.poll());
        }
    }

    void play() {
        Queue<Note> repeat = new LinkedList<>();
        Queue<Note> temp = new LinkedList<>();
        boolean isRepeating = false;
        int size = notes.size();
        for (int i = 0; i < size; i++) {
            if (!isRepeating && !repeat.isEmpty()){
                while (!repeat.isEmpty()) {
                    repeat.poll().play();
                }
            }
            if (notes.peek().isRepeat())
                isRepeating = !isRepeating;
            if (isRepeating || notes.peek().isRepeat()) {
                repeat.offer(notes.peek());
                notes.peek().play();
                notes.offer(notes.poll());
            }
            else {
                    notes.peek().play();
                    notes.offer(notes.poll());
                }
            } while(!repeat.isEmpty())
                repeat.poll().play();
        }
}
