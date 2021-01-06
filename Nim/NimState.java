import java.util.ArrayList;
import java.util.Arrays;

/**
 * System.out.println("");.
 * @author jkbra
 * @version dddd
 */
public class NimState implements State {

    int[] stacks;
    boolean pt;
    int n;


    /**
     * System.out.println("");.
     * @param n System.out.println("");
     * @param pt System.out.println("");
     */
    public NimState(int n, boolean pt) {
        stacks = new int[n];
        this.pt = pt;
        for (int i = 0; i < n; i++) {
            stacks[i] = i + 1;
        }
    }

    /**
     * System.out.println("");.
     * @param stacks System.out.println("");
     * @param pt System.out.println("");
     */
    public NimState(int[] stacks, boolean pt) {
        this.stacks = Arrays.copyOf(stacks, stacks.length);
        this.pt = pt;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public boolean isPlayerTurn() {
        return pt;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public int[] getStacks() {
        return stacks;
    }

    @Override
    public ArrayList<State.Move> findAllMoves() {
        ArrayList<State.Move> alsm = new ArrayList<>();
        for (int i = 0; i < stacks.length; i++) {
            int a = stacks[i];
            if (a >= 3) {
                alsm.add(new NimState.Move(i + 1, 3));
            }
            if (a >= 2) {
                alsm.add(new NimState.Move(i + 1, 2));
            }
            if (a >= 1) {
                alsm.add(new NimState.Move(i + 1, 1));
            }
        }
        return alsm;
    }

    @Override
    public boolean gameOver() {
        boolean ret = true;
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i] != 0) {
                ret = false;
            }
        }
        return ret;
    }

    @Override
    public int getValue() {
        if (!gameOver()) {
            throw new IllegalStateException();
        } else if (isPlayerTurn()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean doMove(State.Move move) {

        NimState.Move m = (NimState.Move) move;
        if (m.stack > stacks.length) {
            return false;
        }
        stacks[m.stack - 1] -= m.num;
        if (stacks[m.stack - 1] < 0) {
            stacks[m.stack - 1] += m.num;
            return false;
        }
        pt = !pt;
        return true;
    }

    @Override
    public void undoMove(State.Move move) {
        NimState.Move m = (NimState.Move) move;
        stacks[m.stack - 1] += m.num;
        pt = !pt;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public String toString() {
        String ret = "";
        if (stacks == null) {
            return null;
        }
        for (int i = 0; i < stacks.length; i++) {
            ret += i + 1 + ": ";
            for (int j = stacks[i]; j > 0; j--) {
                ret += "X";
            }
            ret += "\n";
        }
        return ret;
    }



    /**
     * System.out.println("");.
     */
    public static class Move implements State.Move {

        int num;
        int stack;

        /**
         * System.out.println("");.
         * @param num System.out.println("");
         * @param stack System.out.println("");
         */
        public Move(int stack, int num) {
            this.num = num;
            this.stack = stack;
        }

        /**
         * System.out.println("");.
         * @param o System.out.println("");
         * @return System.out.println("");
         */
        public boolean equals(Object o) {
            if (o instanceof NimState.Move) {
                NimState.Move a = (NimState.Move) o;
                return a.num == num && a.stack == stack;
            }
            return false;
        }

        /**
         * System.out.println("");.
         * @return System.out.println("");
         */
        public String toString() {
            return "Taking " + num + " from stack " + stack;
        }
    }
}
