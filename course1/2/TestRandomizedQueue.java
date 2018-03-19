import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRandomizedQueue {
    private RandomizedQueue<Integer> b = new RandomizedQueue<>();
    private int x = 15;
    private int y = 34;

    @Test
    public void emptyAfterCreating() {
        assertEquals(true, b.isEmpty());
    }

    @Test
    public void zeroElementsAfterCreating() {
        assertEquals(0, b.size());
    }

    @Test
    public void addOneElements() {
        b.enqueue(x);
        assertFalse(b.isEmpty());
        assertEquals(1, b.size());
    }

    @Test
    public void addTwoElements() {
        b.enqueue(x);
        b.enqueue(y);
        assertFalse(b.isEmpty());
        assertEquals(2, b.size());
    }

    @Test
    public void addOneRemoveOne() {
        b.enqueue(x);
        assertEquals(new Integer(x), b.dequeue());
        assertTrue(b.isEmpty());
        assertEquals(0, b.size());
    }

    @Test
    public void addOneSampleOne() {
        b.enqueue(x);
        assertEquals(new Integer(x), b.sample());
        assertFalse(b.isEmpty());
        assertEquals(1, b.size());
    }

    @Test
    public void iteratorHasNoNextInEmptyQueue() {
        Iterator<Integer> iter = b.iterator();
        assertFalse(iter.hasNext());
    }

    @Test
    public void iteratorHasNextInOneElemQueue() {
        b.enqueue(x);
        Iterator<Integer> iter = b.iterator();
        assertTrue(iter.hasNext());
    }

    @Test
    public void iterateThroughCollectionOfOneElem() {
        b.enqueue(x);
        for (Iterator<Integer> iter = b.iterator(); iter.hasNext();) {
            assertEquals(new Integer (x), iter.next());
        }
    }
}
