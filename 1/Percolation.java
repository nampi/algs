import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean [] a;
    private int nOpen = 0;
    private final WeightedQuickUnionUF w;
    private final WeightedQuickUnionUF v;
    private final int top;
    private final int bottom;

    public Percolation(int ntmp) {               // create n-by-n grid, with all sites blocked
        if (ntmp <= 0) {
            throw new IllegalArgumentException();
        }
        n = ntmp;
        int len = n * n + 2;
        a = new boolean[len];
        w = new WeightedQuickUnionUF(len);
        v = new WeightedQuickUnionUF(len - 1);
        top = 0;
        bottom = len - 1;
    }

    private boolean checkRange(int row, int col) {
        return row > 0 && row <= n && col > 0 && col <= n;
    }

    private int toScale(int row, int col) {
        return n * (row - 1) + (col - 1) + 1;
    }

    private void connectIfOpen(int pos, int x, int y) {
        if (!checkRange(x, y)) {
            return;
        }
        if (isOpen(x, y)) {
            int p = toScale(x, y);
            w.union(pos, p);
            v.union(pos, p);
        }
    }
    public    void open(int row, int col)     // open site (row, col) if it is not open already
    {
        if (!checkRange(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            int curPos = toScale(row, col);
            a[curPos] = true;
            nOpen++;
            if (row == 1) {
                w.union(top, curPos);
                v.union(top, curPos);
            }
            if (row == n) {
                w.union(curPos, bottom);
            }

            connectIfOpen(curPos, row - 1, col);
            connectIfOpen(curPos, row, col + 1);
            connectIfOpen(curPos, row + 1, col);
            connectIfOpen(curPos, row, col - 1);
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (!checkRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return a[toScale(row, col)];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (!checkRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return v.connected(top, toScale(row, col));
    }
    public     int numberOfOpenSites()        // number of open sites
    {
        return nOpen;
    }

    public boolean percolates()              // does the system percolate?
    {
        return w.connected(top, bottom);
    }

    public static void main(String[] args)   // test client (optional)
    {
        System.out.println("Test");
    }
}