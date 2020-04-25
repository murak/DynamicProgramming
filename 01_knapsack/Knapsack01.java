import java.util.Arrays;

public class Knapsack01 {

    private int[] w; // item weight array
    private int[] p; // item price/value array
    private int[][] T; // Bottom up computation table.

    public Knapsack01(final int[] w, final int[] p, final int N, final int C) {
        this.w = w;
        this.p = p;
        this.T = new int[N+1][C+1];
    }

    /**
     * Returns maximum profit by chosing from N items into a bag of capacity C.
     * @param N - number of items given.
     * @param C - capacity of the bag.
     */
    private int profit(int N, int C) {
        /* Initialize first colum with zero */
        for (int i = 0; i < N + 1; i++) {
            T[i][0] = 0;
        }

        /* Initialize first row with zero */
        for (int j = 0; j < C + 1; j++) {
            T[0][j] = 0;
        }

        /* Populate remaining cells in the table from left to right and top to down order */
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                if (w[i-1] <= j) {
                    T[i][j] = Math.max(p[i-1] + T[i-1][j-w[i-1]], T[i-1][j]);
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }

        /* return maximum profit computed */
        return T[N][C];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 4, 6};
        int[] p = {1, 3, 5, 7};
        int N = w.length;
        int C = 7;

        Knapsack01 knapsack = new Knapsack01(w, p, N, C);
        int maximumProfit = knapsack.profit(N, C);

        System.out.println("Weight = " + Arrays.toString(w));
        System.out.println("Price = " + Arrays.toString(p));
        System.out.println("Bag capacity = " + C);
        System.out.println("maximumProfit = " + maximumProfit);
    }

}
