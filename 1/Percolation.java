public class Percolation {
   private final int n;
   private final int [][] a;
   private int nOpen = 0;
   private final int [][] ind = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
   public Percolation(int ntmp)                // create n-by-n grid, with all sites blocked
   {
       if (ntmp <= 0) {
           throw new IllegalArgumentException();
       }
       n = ntmp;
       a = new int[n+1][n+1];
   }
   private boolean checkRange(int row, int col) {
       return row > 0 && row <= n && col > 0 && col <= n;
   }
   private void full(int row, int col) {
       a[row][col] = 2;
       for (int i = 0; i < 4; i++) {
           int x = row + ind[0][i];
           int y = col + ind[1][i];
           if (!checkRange(x, y)) continue;
           if (isOpen(x, y) && !isFull(x, y)) {
               full(x, y);
           }
       }       
   }
   public    void open(int row, int col)     // open site (row, col) if it is not open already
   {
       if (!checkRange(row, col)) {
           throw new IllegalArgumentException();
       }
       if (!isOpen(row, col)) {
           a[row][col] = 1;
           nOpen++;
           boolean f = row == 1;
           for (int i = 0; i < 4; i++) {
               int x = row + ind[0][i];
               int y = col + ind[1][i];
               if (!checkRange(x, y)) continue;
               if (isFull(x, y)) {
                   f = true;
                   break;
               }
           }
           if (f) {
               full(row, col);
           }
       }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       if (!checkRange(row, col)) {
           throw new IllegalArgumentException();
       }
       return (a[row][col] == 1) | (a[row][col] == 2);
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (!checkRange(row, col)) {
           throw new IllegalArgumentException();
       }
       return a[row][col] == 2;
   }
   public     int numberOfOpenSites()        // number of open sites
   {
       return nOpen;
   }
   public boolean percolates()              // does the system percolate?
   {
       for (int i = 1; i <= n; i++) {
           if (isFull(n, i)) {
               return true;
           }
       }
       return false;
   }

   public static void main(String[] args)   // test client (optional)
   {
       System.out.println("Test");
   }
}