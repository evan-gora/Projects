import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the method below as described in final question
 * 
 * @author john1819
 *
 */
public class StackQueueQuestion2 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Queue<Integer> theQueue = new LinkedList<Integer>();
        // 3, 8, 17, 9, 17, 8, 3
        theQueue.add(3);
        theQueue.add(8);
        theQueue.add(17);
        theQueue.add(9);
        theQueue.add(17);
        ;
        theQueue.add(8);
        theQueue.add(3);

        System.out.println("The queue before " + theQueue);
        theQueue = rearrange(theQueue);
        System.out.println("The queue after " + theQueue);

        System.out.println();
        System.out.println("Test 2");
        // second test
        theQueue.clear();
        // 3, 8, 17, 9, 4, 17, 8, 3]
        theQueue.add(3);
        theQueue.add(8);
        theQueue.add(16);
        theQueue.add(9);
        theQueue.add(4);
        theQueue.add(17);
        ;
        theQueue.add(7);
        theQueue.add(4);
        System.out.println("The queue before " + theQueue);
        theQueue = rearrange(theQueue);
        System.out.println("The queue after " + theQueue);

    }

    public static Queue<Integer> rearrange(Queue<Integer> theQueue) {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ArrayList<Integer> evenInts = new ArrayList<Integer>();
        ArrayList<Integer> oddInts = new ArrayList<Integer>();
        // Remove all elements from the queue and add them to their respective array lists.
        while (!theQueue.isEmpty()) {
            if (theQueue.peek() % 2 == 0) {
                evenInts.add(theQueue.remove());
            } else {
                oddInts.add(theQueue.remove());
            }
        }
        // Add all the even numbers and then add all of the odd numbers to the ints array.
        for (int i = 0; i < evenInts.size(); i++) {
            ints.add(evenInts.get(i));
        }
        for (int j = 0; j < oddInts.size(); j++) {
            ints.add(oddInts.get(j));
        }
        
        // Add all values from the ints array list back to the queue.
        for (Integer i : ints) {
            theQueue.add(i);
        }

        return theQueue;
    }

}
