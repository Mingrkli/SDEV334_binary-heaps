package structures;

public interface IPriorityQueue {
    void add(int element);
    int remove();
    int peek();
    int size();
    boolean isEmpty();
}
