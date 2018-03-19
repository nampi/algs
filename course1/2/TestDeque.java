import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

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
        assertTrue(b.isEmpty());
    }

    @Test
    public void zeroElementsAfterCreating() {
        assertEquals(0, b.size());
    }

    @Test
    public void shouldThrownExceptionAddFirstNullItem() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Item must not be null");
        b.addFirst(null);
    }

    @Test
    public void shouldThrownExceptionAddLastNullItem() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Item must not be null");
        b.addLast(null);
    }

    @Test
    public void shouldThrownExceptionRemoveFirstInEmptyDeque() {
        expectedEx.expect(NoSuchElementException.class);
        expectedEx.expectMessage("Deque underflow");
        b.removeFirst();
    }

    @Test
    public void shouldThrownExceptionRemoveLastInEmptyDeque() {
        expectedEx.expect(NoSuchElementException.class);
        expectedEx.expectMessage("Deque underflow");
        b.removeLast();
    }
}