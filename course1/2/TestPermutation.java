import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestPermutation {
    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = {"1"};
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("permutation4.txt"));
        System.setIn(fips);
        Permutation.main(args);
        System.setIn(original);
    }
}
