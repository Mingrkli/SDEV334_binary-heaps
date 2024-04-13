package test;

import structures.IntegerPQ;

public class QueueTests {
    public static void main(String[] args) {
        IntegerPQ queue = new IntegerPQ();
        int[] elementsToAdd = {78, 45, 13, 22, 150, 99, 1, 4};

        // add to the queue
        for (int element : elementsToAdd) {
            queue.add(element);
        }

        System.out.println(queue);

        // While the queue is NOT empty
        // Everytime we go into this loop, one of the elements are going to be removed from the queue,
        // the element would be saved the last index would go to the root and sink down to the position
        while (!queue.isEmpty()) {
            int element = queue.remove();

            System.out.println(element);
        }
    }
}
