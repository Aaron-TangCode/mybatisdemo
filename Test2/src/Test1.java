import java.util.PriorityQueue;
import java.util.Vector;

public class Test1 {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(3);
        vector.add(7);
        vector.add(2);
        vector.add(5);
        vector.add(1);
        for (Integer i:
             vector) {
            System.out.print(i+" ");
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(2);
        priorityQueue.add(7);
        for (Integer l:
             priorityQueue) {
            System.out.print(l+" ");
        }
        priorityQueue.poll();
        for (Integer l:
                priorityQueue) {
            System.out.print(l+" ");
        }
    }
}
