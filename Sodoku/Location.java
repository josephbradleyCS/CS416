/**
 * @author jkbra
 * @version 1.00
 */
public class Location {

    int row;
    int column;

    /**
     * System.out.println("");.
     * @param r System.out.println("");
     * @param c System.out.println("");
     */
    public Location(int r, int c) {
        row = r;
        column = c;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public int getRow() {
        return row;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public int getColumn() {
        return column;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public Location next() {
        int row = this.row;
        int col = this.column;
        if (col == 8 && row == 8) {
            return null;
        } else if (col == 8 && row < 8) {
            row++;
            col = 0;
        } else {
            col++;
        }
        return new Location(row, col);
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    @Override
    public String toString() {
        return  row + ", " + column;
    }
}
