import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> a = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            a.enqueue(item);
        }
        Iterator<String> list = a.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(list.next());
        }
    }
}