import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestRandomizedQueue {
    private RandomizedQueue<Integer> b = new RandomizedQueue<>();
    private int x = 15, y = 34;

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
        assertEquals(false, b.isEmpty());
        assertEquals(1, b.size());
    }

    @Test
    public void addTwoElements() {
        b.enqueue(x);
        b.enqueue(y);
        assertEquals(false, b.isEmpty());
        assertEquals(2, b.size());
    }

    @Test
    public void addOneRemoveOne() {
        b.enqueue(x);
        assertEquals(new Integer(x), b.dequeue());
        assertEquals(true, b.isEmpty());
        assertEquals(0, b.size());
    }

    @Test
    public void addOneSampleOne() {
        b.enqueue(x);
        assertEquals(new Integer(x), b.sample());
        assertEquals(false, b.isEmpty());
        assertEquals(1, b.size());
    }
}
