import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
   private final double [] res;
   private final int size;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       if (n <= 0 || trials <= 0) {
           throw new IllegalArgumentException();
       }
       res = new double[trials];
       size = trials;
       for (int i = 0; i < trials; i++) {
           Percolation p = new Percolation(n);
           while (!p.percolates()) {
               int x = StdRandom.uniform(n) + 1;
               int y = StdRandom.uniform(n) + 1;
               p.open(x, y);
           }
           res[i] = ((double) p.numberOfOpenSites()) / ((double) n*n);
       }
   }
   
   public double mean()                          // sample mean of percolation threshold
   { 
       return StdStats.mean(res);
   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(res);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       return mean() - 1.96 * stddev() / Math.sqrt(size);
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       return mean() + 1.96 * stddev() / Math.sqrt(size);
   }

   public static void main(String[] args)        // test client (described below)
   {
       PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       System.out.println("mean                    = " + p.mean());
       System.out.println("stddev                  = " + p.stddev());
       System.out.println("95% confidence interval = [" +p.confidenceLo() + ", " + p.confidenceHi() + "]");
   }
   
}