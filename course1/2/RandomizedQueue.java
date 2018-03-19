import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    private int ind = -1;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 2;
        a = (Item[]) new Object[n];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return ind == -1;
    }

    // return the number of items on the randomized queue
    public int size() {
        return ind + 1;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i <= ind; i++) {
            copy[i] = a[i];
        }
        a = copy;
        n = capacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }

        if (ind + 1 >= n) {
            resize(2 * n);
        }
        ind++;
        a[ind] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (ind == -1) {
            throw new NoSuchElementException("Queue underflow");
        }

        if (ind > 0 && ind == n / 4) {
            resize(n / 2);
        }

        int index = StdRandom.uniform(0, ind+1);
        Item tmp = a[index];
        a[index] = a[ind];
        a[ind] = null;
        ind--;
        return tmp;
   }

   // return a random item (but do not remove it)
   public Item sample() {
       if (ind == -1) {
           throw new NoSuchElementException("Queue underflow");
       }
       return a[StdRandom.uniform(0, ind+1)];
   }

   // return an independent iterator over items in random order
   public Iterator<Item> iterator() {
       return new RandomizedQueueIterator();
   }

   private class RandomizedQueueIterator implements Iterator<Item> {
        int size = ind + 1;
        private final int[] index = StdRandom.permutation(size);
        private int cur = 0;
        public boolean hasNext() {
            return cur < size;
        }
        public void remove() {
           throw new UnsupportedOperationException("Not yet implemented");
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue underflow");
            }
            return a[index[cur++]];
        }
   }
   public static void main(String[] args) {
        // empty
    }
}