import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on deque
    private int s; // first element in deque
    private int f; // last element in deque

    /**
     * Construct an empty deque.
     */
    public Deque() {
        a = (Item[]) new Object[2];
        n = 0;
        s = 0;
        f = 0;
    }

    /**
     * Is the deque empty?
     * @return true if the deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return the number of items on the deque.
     * @return the number of items on the deque
     */
    public int size() {
        return n;
    }

    /**
     * Add the item to the front.
     * @param item the item to add
     * @throws java.lang.IllegalArgumentException if item is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (n != 0) {
            if ((s + a.length - 1) % a.length == f) {
                resize(2 * a.length);
            }
            s = (s + a.length - 1) % a.length;
        }

        n++;
        a[s] = item;
    }

    /**
     * Add the item to the end.
     * @param item the item to add
     * @throws java.lang.IllegalArgumentException if item is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (n != 0) {
            if (s == (f + a.length + 1) % a.length) {
                resize(2 * a.length);
            }
            f = (f + a.length + 1) % a.length;
        }

        n++;
        a[f] = item;
    }

    /**
     * Remove and return the item from the front.
     * @return the item from the front
     * @throws java.util.NoSuchElementException if the deck is empty
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item tmp = a[s];
        n--;
        a[s] = null; // to avoid loitering
        if (n != 0) {
            s = (s + a.length + 1) % a.length;
        }

        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return tmp;
    }

    /**
     * Remove and return the item from the end.
     * @return the item from the end
     * @throws java.util.NoSuchElementException if the deck is empty
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item tmp = a[f];
        n--;
        a[f] = null;
        if (n != 0) {
            f = (f + a.length - 1) % a.length;
        }
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return tmp;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[s];
            s = (s + a.length + 1) % a.length;
        }
        a = copy;
        s = 0;
        f = n - 1;
    }

    /**
     * Returns an iterator over items in order from front to end
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private int cur = s;
        private int number = 0;

        public boolean hasNext() {
            return number < n;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item tmp = a[cur];
            cur = (cur + a.length + 1) % a.length;
            number++;
            return tmp;
        }  
    }

    public static void main(String[] args) {
        // empty
    }
}