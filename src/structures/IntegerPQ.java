package structures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntegerPQ implements IPriorityQueue {
    public static final int INITIAL_CAPACITY = 10;

    // min heap
    private int[] heap;
    private  int size;

    public IntegerPQ () {
        heap = new int[INITIAL_CAPACITY];
    }

    @Override
    public void add(int element) {
        // check if we ran out of room in the array
        if (size == heap.length) {
            resize();
        }

        // Add the element to the next available index
        heap[size] = element;

        // swim up the tree
        swim(size);
        size++;
    }

    private void resize() {
        // doubles the heap length
        int[] newHeap = new int[heap.length * 2];

        // copy elements to the new heap
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }

        // use the new heap
        heap = newHeap;
    }

    private void swim(int index) {
        // given index i, the parent is at (i-1)/2

        // continue until we reach the root
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // are they out of order
            if (heap[index] < heap[parentIndex]) {
                // Saved in a variable since we'll be losing the value if we don't
                swap(index, parentIndex);

                // after swap, if it's out of order; move up the tree
                index = parentIndex;
            }
            else {
                // if their in order, short circuit
                break;
            }
        }
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    @Override
    public int remove() {
        checkEmpty();

        int saved = heap[0];
        // "size - 1" because "size" is the next empty position while "size - 1" is the last occupied position
        heap[0] = heap[size - 1];
        // We will remove it by setting that node position to 0 meaning that there's nothing there
        heap[size - 1] = 0;

        size--;
        // Always sink from the root, and since it's a min-heap, we would want to select the smallest of the children
        sink(0);

        return saved;
    }

    private void sink(int index) {
        // "(size / 2) - 1" will get the highest index with child. Cool formula :D
        int highestIndexWithChild = (size / 2) - 1;

        while (index <= highestIndexWithChild) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;

            // which is the smaller child
            // This will give you the smallest of the 2 elements
            int smallestIndex = leftIndex;
            // "rightIndex < size" means, is there a right child?
            // "heap[rightIndex] < heap[leftIndex]" means, is that right child smaller?
            if (rightIndex < size && heap[rightIndex] < heap[leftIndex]) {
                smallestIndex = rightIndex;
            }

            // swap if out of order
            if (heap[index] > heap[smallestIndex]) {
                swap(index, smallestIndex);

                // move down to the child element
                index = smallestIndex;
            }
            else {
                // stop - short circuit
                break;
            }
        }
    }

    @Override
    public int peek() {
        checkEmpty();

        return heap[0];
    }

    // If the heap is empty
    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Empty heap!");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        return Arrays.toString(heap);
    }
}
