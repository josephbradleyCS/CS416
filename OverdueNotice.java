/**
 * @author jkbra
 * @version 1.01
 */
public class OverdueNotice {

    private Patron p;
    private int due;

    /**
     *
     * @param patron System.out.println("");
     * @param duee System.out.println("");
     */ // Constructs an overdue notice for the given Patron. (What it actually does is save the
    // patron in an instance variable, and saves today's date in another instance variable.)
    public OverdueNotice(Patron patron, int duee) {
        this.p = patron;
        this.due = duee;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public Patron getP() {
        return p;
    }

    /**
     * System.out.println("");.
     * @param p System.out.println("");
     */
    public void setP(Patron p) {
        this.p = p;
    }

    /**
     * System.out.println("");.
     * @return System.out.println("");
     */
    public int getDue() {
        return due;
    }

    /**
     *  System.out.println("");.
     * @return System.out.println();
     */
    @Override
    public String toString() {
        return "Your books are over due, "
                + "you currently have these books: "
                + p.getBooks();
    }
}
