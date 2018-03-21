import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> a = new RandomizedQueue<>();
        int n = 0;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            n++;
            if (n <= k) {
                a.enqueue(item);
            } else {
                int p = StdRandom.uniform(0, n);
                if (p < k) {
                    a.dequeue();
                    a.enqueue(item);
                }
            }
        }

        Iterator<String> list = a.iterator();
        while (list.hasNext()) {
            StdOut.println(list.next());
        }
    }
}