import java.util.Scanner;

/**
 * @author jkbra
 * @version 1.00
 */
public class Board {

    int[][] board;

    /**
     * System.out.println("");.
     *
     * @param kb System.out.println("");
     */
    public Board(Scanner kb) {
        board = readBoard(kb);
    }

    /**
     * System.out.println("");.
     *
     * @param kb System.out.println("");
     * @return System.out.println(" ");
     */
    public static int[][] readBoard(Scanner kb) {
        try {
            int[][] ret = new int[9][9];

            for (int row = 0; row < 9; row++) {
                String line = kb.nextLine();
                char[] rowAsChar = line.toCharArray();
                for (int col = 0; col < 9; col++) {
                    char a = rowAsChar[col];
                    if (a == '-') {
                        ret[row][col] = 0;
                    } else if (Character.isDigit(a)) {
                        ret[row][col] = (int) a - 48;
                    } else {
                        System.out.println("The board is invalid");
                        return null;
                    }
                }
            }
            if (ret[0].length > 9) {
                return null;
            }
            return ret;
        } catch (Exception e) {
            System.out.println("Too short");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * System.out.println("");.
     *
     * @param row System.out.println("");
     * @param col System.out.println("");
     * @return System.out.println(" ");
     */
    public int get(int row, int col) {
        return this.board[row][col];
    }

    /**
     * System.out.println("");.
     *
     * @param row   System.out.println("");
     * @param col   System.out.println("");
     * @param value System.out.println("");
     */
    public void set(int row, int col, int value) {
        this.board[row][col] = value;
    }

    /**
     * System.out.println("");.
     *
     * @param row    System.out.println("");
     * @param number System.out.println("");
     * @return System.out.println(" ");
     */
    public boolean containsInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * System.out.println("");.
     *
     * @param col    System.out.println("");
     * @param number System.out.println("");
     * @return System.out.println(" ");
     */
    public boolean containsInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * System.out.println("");.
     *
     * @param row    System.out.println("");
     * @param col    System.out.println("");
     * @param number System.out.println("");
     * @return System.out.println(" ");
     */
    public boolean containsInBox(int row, int col, int number) {
        while (true) {
            if (row == 0 || row == 6 || row == 3) {
                if (col == 0 || col == 3 || col == 6) {
                    for (int i = 0; i < 3; i++) { // down
                        for (int j = 0; j < 3; j++) { // across
                            if (board[row + j][col + i] == number) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else {
                    col--;
                }
            } else {
                row--;
            }
        }
    }

    /**
     * System.out.println("");.
     *
     * @param row    System.out.println("");
     * @param col    System.out.println("");
     * @param number System.out.println("");
     * @return System.out.println(" ");
     */
    public boolean isAllowed(int row, int col, int number) {
        return  board[row][col] == 0
                && !containsInRow(row, number)
                && !containsInCol(col, number)
                && !containsInBox(row, col, number);
        // return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }


    @Override
    public String toString() {
        Location loc = new Location(0, 0);
        String ret = "";
        for (int i = 0; i < 12; i++) {
            if (i == 0 || i == 4 || i == 8) {
                ret += "+-------+-------+-------+\n";
            } else {
                for (int l = 0; l < 3; l++) {
                    ret += "| " + dash(board[loc.getRow()][loc.getColumn()]) + " ";
                    loc = loc.next();
                    ret += dash(board[loc.getRow()][loc.getColumn()]) + " ";
                    loc = loc.next();
                    ret += dash(board[loc.getRow()][loc.getColumn()]) + " ";
                    loc = loc.next();
                }
                ret += "|\n";
            }
        }
        ret += "+-------+-------+-------+";
        return ret;
    }

    /**
     * System.out.println("").
     *
     * @param a System.out.println("");
     * @return System.out.println();
     */
    public String dash(int a) {
        if (a == 0) {
            return "-";
        }
        return Integer.toString(a);
    }
}
