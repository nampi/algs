import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on deque
    private int head; // index of the first element in deque
    private int tail; // index of the last element in deque

    /**
     * Construct an empty deque.
     */
    public Deque() {
        a = (Item[]) new Object[2];
        n = 0;
        head = 0;
        tail = 0;
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
     * @throws IllegalArgumentException if item is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        if (n == a.length) {
            resize(2 * a.length);
        }
        if (n != 0) {
            head = (head + a.length - 1) % a.length;
        }
        a[head] = item;
        n++;
    }

    /**
     * Add the item to the end.
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        if (n == a.length) {
            resize(2 * a.length);
        }
        if (n != 0) {
            tail = (tail + a.length + 1) % a.length;
        }
        a[tail] = item;
        n++;
    }

    /**
     * Remove and return the item from the front.
     * @return the item from the front
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        Item tmp = a[head];
        n--;
        a[head] = null; // to avoid loitering
        if (n != 0) {
            head = (head + a.length + 1) % a.length;
        }
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return tmp;
    }

    /**
     * Remove and return the item from the end.
     * @return the item from the end
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        Item tmp = a[tail];
        n--;
        a[tail] = null;
        if (n != 0) {
            tail = (tail + a.length - 1) % a.length;
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
            copy[i] = a[head];
            head = (head + a.length + 1) % a.length;
        }
        a = copy;
        head = 0;
        tail = n - 1;
    }

    /**
     * Returns an iterator over items in order from front to end
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private int cur = head;
        private int number = 0;

        public boolean hasNext() {
            return number < n;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque underflow");
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