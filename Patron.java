import java.util.ArrayList;

/**
 * This does stuff.
 * @author jkbrads
 * @version 1.01
 */
public class Patron {

    private String name;
    private Library library;
    private ArrayList<Book> checked = new ArrayList<Book>();


    /**
     * This does stuff.
     * @param name System.out.println("");
     * @param library System.out.println("");
     */ // Constructs a patron with the given name, and no books. The patron must also have a
    // reference to the Library object that he/she uses (therefore, you must have a Library
    // before you can have any Patrons).
    public Patron(String name, Library library) {
        this.name = name;
        this.library = library;
    }


    /**
     * This does stuff.
     * @return System.out.println("");
     */ // Returns the patrons name
    public String getName() {
        return name;
    }

    /**
     * This does stuff.
     * @param book System.out.println("");
     */ // Adds this book to the list of books checked out by this Patron.
    public void take(Book book) {
        checked.add(book);
    }

    /**
     * This does stuff.
     * @param book System.out.println("");
     */ // Removes this Book object from the list of books checked out by this Patron.
    public void giveBack(Book book) {
        checked.remove(book);
    }

    /**
     * This does stuff.
     * @return System.out.println("");
     */ // Returns the list of Book objects checked out to this Patron (may be an empty list).
    public ArrayList<Book> getBooks() {
        return checked;
    }

    /**
     * This does stuff.
     * @return System.out.println("");
     */ // Returns this patron's name. (Yes, this is the same as the getName() method!)
    @Override
    public String toString() {
        return this.getName();
    }
}
