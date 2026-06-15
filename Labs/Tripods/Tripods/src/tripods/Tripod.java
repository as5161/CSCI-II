package tripods;

/**
 * A Java record for a Tripod
 * @param row row for tripod in the number grid (center position)
 * @param col column for tripod in the number grid
 * @param dir direction the tripod is facing in the number grid
 * @param sum the sum of the three legs of the tripods in the number grid
 *
 * @author RIT CS
 *
 * Output:
 * $ java Tripod
 * t1: Tripod[rolw=1, col=3, dir=WEST, sum=5]
 * t1 row: 1
 * t1 col: 3
 * t1 dir: WEST
 * t1 sum: 5
 */
public record Tripod(int row, int col, Direction dir, int sum) {
    /**
     * Demonstrates usage of an immutable Tripod record.
     *
     * @param args command line (unused)
     */
    public static void main(String[] args) {
        Tripod t1 = new Tripod(1, 3, Direction.WEST, 5);
        System.out.println(("t1: " + t1));
        System.out.println(("t1 row: " + t1.row()));
        System.out.println(("t1 col: " + t1.col()));
        System.out.println("t1 dir: " + t1.dir());
        System.out.println(("t1 sum: " + t1.sum()));

        // The record is immutable, cannot change after creation: t1.row = 10;
    }
}
