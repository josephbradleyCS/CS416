import java.io.File;
import java.util.Scanner;

/**
 * @author jkbra
 * @version 1.00
 */
public class Sudoku {

    Board board;
    int recalls;
    int backups;
    String name;

    /**
     * System.out.println("");.
     *
     * @param kb System.out.println("");
     */
    public Sudoku(Scanner kb) {
        board = new Board(kb);
        recalls = 0;
        backups = 0;
    }

    /**
     * System.out.println("");.
     *
     * @param loc System.out.println("");
     * @return System.out.println(" ");
     */
    public boolean solve(Location loc) {
        recalls++;
        boolean bool = false;
        if (loc == null) {
            return true;
        }
        int row = loc.getRow();
        int col = loc.getColumn();
        int value = board.get(row, col);
        if (value != 0) {
            bool = solve(loc.next());
        } else {

            for (int number = 1; number < 10 && !bool; number++) {
                //System.out.println("number: " + number);
                board.set(row, col, 0);
                if (board.isAllowed(row, col, number)) {
                    board.set(row, col, number);
                    bool = solve(loc.next());
                }
                // System.out.println(this.getBoard().toString());
            }
            if (!bool) {
                System.out.println("backup:");
                board.set(row, col, 0);
                backups++;
            }
        }
        return bool;
    }


    /**
     * System.out.println("");.
     *
     * @return System.out.println(" ");
     */
    public int getRecursionCount() {
        return recalls;
    }

    /**
     * System.out.println("");.
     *
     * @return System.out.println(" ");
     */
    public int getBackupCount() {
        return backups;
    }

    /**
     * ffffff.
     *
     * @return fffffff
     */
    public Board getBoard() {
        return board;
    }


    /**
     * System.out.println("");.
     *
     * @param args System.out.println("");
     */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the path to the sudoku file:");
        String fName = kb.nextLine();
        File file = new File(fName);
        try {
            Scanner fileKb = new Scanner(file);
            Sudoku thiss = new Sudoku(fileKb);

            Location loc = new Location(0, 0);
            System.out.println("Initial configuration of the sudoku");
            System.out.println(thiss.board.toString());
            if (thiss.solve(loc)) {
                System.out.println("Successful!");
                System.out.println("Final configuration of the sudoku");
                System.out.println(thiss.getBoard().toString());
                System.out.println("Recursion count = " + thiss.getRecursionCount());
                System.out.println("Backup count = " + thiss.getBackupCount());
            } else {
                System.out.println("It didn't work");
                System.out.println(thiss.getBoard().toString());
                System.out.println("Recursion count = " + thiss.getRecursionCount());
                System.out.println("Backup count = " + thiss.getBackupCount());
            }
        } catch (Exception e) {
            System.out.println("Exeption here, main method sudoku");
            e.printStackTrace();
        }

    }


}
