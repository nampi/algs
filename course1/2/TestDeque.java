import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestDeque {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private Deque<Integer> b = new Deque<>();
    private int x = 15;

    @Test
    public void addFirstRemoveLast() {
        b.addFirst(x);
        assertEquals(new Integer(x), b.removeLast());
    }

    @Test
    public void addFirstRemoveFirst() {
        b.addFirst(x);
        assertEquals(new Integer(x), b.removeFirst());
    }

    @Test
    public void addLastRemoveFirst() {
        b.addLast(x);
        assertEquals(new Integer(x), b.removeFirst());
    }

    @Test
    public void addLastRemoveLast() {
        b.addLast(x);
        assertEquals(new Integer(x), b.removeLast());
    }

    @Test
    public void emptyAfterCreating() {
        assertEquals(true, b.isEmpty());
    }

    @Test
    public void zeroElementsAfterCreating() {
        assertEquals(0, b.size());
    }

    @Test
    public void shouldThrownExceptionAddFirstNullItem() {
        expectedEx.expect(java.lang.IllegalArgumentException.class);
        b.addFirst(null);
    }

    @Test
    public void shouldThrownExceptionAddLastNullItem() {
        expectedEx.expect(java.lang.IllegalArgumentException.class);
        b.addLast(null);
    }

    @Test
    public void shouldThrownExceptionRemoveFirstInEmptyDeck() {
        expectedEx.expect(java.util.NoSuchElementException.class);
        b.removeFirst();
    }

    @Test
    public void shouldThrownExceptionRemoveLastInEmptyDeck() {
        expectedEx.expect(java.util.NoSuchElementException.class);
        b.removeLast();
    }
}