import java.util.ArrayList;


/**
 * System.out.println("");.
 * @author jkbra
 * @version 1.01

 */
public class GameTree {

    // NimState m = new NimState(3, true);

    Node root;

    /**
     * System.out.println("");.
     * @param s System.out.println("");
     */
    public GameTree(State s) {
        root = buildTree(s, new NimState.Move(1, 0));
    }

    /**
     * System.out.println("");.
     * @return System.out.println(" ");
     */
    public Node getRoot() {
        return root;
    }

    /**
     * System.out.println("");.
     * @param state System.out.println("");
     * @param m System.out.println("");
     * @return System.out.println(" ");
     */
    public Node buildTree(State state, State.Move m) {
        Node n = new Node(m);
        if (state.gameOver()) {  // leaf node
            if (state.getValue() == 1) {
                n.value = 1;
            } else if (state.getValue() == -1) {
                n.value = -1;
            } else {
                n.value = 0;
            }
        } else { // non-leaf node
            for (State.Move aa : state.findAllMoves()) {
                state.doMove(aa);
                n.aln.add(buildTree(state, aa));
                state.undoMove(aa);
                if (!state.isPlayerTurn()) {
                    n.value = -1;
                    for (Node ff : n.aln) {
                        if (ff.value > n.value) {
                            n.value = ff.value;
                            n.sean = ff;
                            break;
                        }
                        n.sean = ff;
                    }
                } else {
                    n.value = 1;
                    for (Node ff : n.aln) {
                        if (ff.value < n.value) {
                            n.value = ff.value;
                            n.sean = ff;
                            break;
                        }
                        n.sean = ff;
                    }
                }
            }
        }
        return n;
    }


    /**
     * System.out.println("");.
     * @return System.out.println(" ");
     */
    public int getSize() {
        if (root == null) {
            return 0;
        }
        return sizey(root) + 1;
    }

    /**
     * System.out.println("");.
     * @param n  System.out.println("");
     * @return System.out.println(" ");
     */
    public int sizey(Node n) {
        int ret = 0;
        for (int i = 0; i < n.aln.size(); i++) {
            ret += 1 + sizey(n.aln.get(i));
        }
        return ret;
    }

    /**
     * System.out.println("");.
     */
    class Node {


        ArrayList<Node> aln = new ArrayList<>();
        State.Move m;
        int value;
        Node sean;

        /**
         * System.out.println("");.
         * @param m System.out.println("");
         */
        Node(State.Move m) {
            this.m = m;
        }

        /**
         * System.out.println("");.
         * @return System.out.println(" ");
         */
        public int getValue() {
            return value;
        }

        /**
         * System.out.println("");.
         * @return System.out.println(" ");
         */
        public State.Move getMove() {
            return m;
        }

        /**
         * System.out.println("");.
         * @return System.out.println(" ");
         */
        public Node getBestChild() {
            return sean;
        }

        /**
         * System.out.println("");.
         * @param m System.out.println("");
         * @return System.out.println(" ");
         */
        public Node findNode(State.Move m) {
            if (aln.isEmpty()) {
                return null;
            }
            int a = aln.get(0).value;
            Node d = aln.get(0);
            for (Node n : aln) {
                if (m.equals(n.getMove())) {
                    d = n;
                }
            }
            return d;
        }

        /**
         * System.out.println("");.
         * @return System.out.println(" ");
         */
        public String getPrediction() {
            if (getValue() == 1) {
                return "computer";
            } else if (getValue() == -1) {
                return "player";
            } else {
                return "no one";
            }
        }

        /**
         * System.out.println("");.
         * @param m System.out.println("");
         * @return System.out.println(" ");
         */
        public Node findChild(State.Move m) {
            for (Node n : aln) {
                if (n.m.equals(m)) {
                    return n;
                }
            }
            return null;
        }
    }
}
