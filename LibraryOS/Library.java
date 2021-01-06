import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * println("");.
 *
 * @author jkbra
 * @version 1.01
 */
public class Library {

    // instance variables
    private HashMap<String, Patron> hmp = new HashMap<>();
    private ArrayList<Book> catalog = new ArrayList<Book>();
    private ArrayList<Book> searched = new ArrayList<Book>();
    private boolean okToPrint;
    private LibraryCalendar due = new LibraryCalendar();
    private boolean open;
    private Patron current = null;


    /**
     * println("");.
     */
    public Library() {
        Scanner kb;
        try {
            File file = new File("C:\\Users\\jkbra\\Desktop\\basicMath\\Library2\\src\\Books.txt");
            kb = new Scanner(file);
            okToPrint = true;

            while (kb.hasNextLine()) {
                String line = kb.nextLine();
                for (int i = 0; i < line.length() - 1; i++) {
                    if (line.charAt(i) == ':') {
                        catalog.add(new Book(line.substring(0, i - 1), line.substring(i + 2)));
                    }
                }
            }
        } catch (Exception e) {
            println("Execption in file scanner");
        }
        this.hmp = new HashMap<>();
    }

    /**
     * println("");.
     *
     * @param collection println("");
     */
    public Library(ArrayList<Book> collection) {
        okToPrint = false;
        this.catalog = collection;
    }

    /**
     * println("");.
     *
     * @param args println("");
     */
    public static void main(String[] args) {
        Library l = new Library();
        l.start();
    }

    /**
     * println("");.
     *
     * @param message println("");
     */
    public void print(String message) {
        if (okToPrint) {
            System.out.print(message);
        }
    }

    /**
     * println("");.
     *
     * @param message println("");
     */
    public void println(String message) {
        if (okToPrint) {
            System.out.println(message);
        }
    }

    /**
     * println("");.
     */
    public void start() {
        Scanner kb = new Scanner(System.in);
        while (true) {
            String input = kb.nextLine();
            println("Enter input");
            switch (input.split(" ")[0]) {
                case "open":
                    open();
                    println("The Library has been opened");
                    break;
                case "issueCard":
                    String newPatronName = input.split(" ")[1];
                    Patron p = issueCard(newPatronName);
                    if (p == null) {
                        println("Non one has been issued a card");
                        break;
                    }
                    println(p.getName() + "has been issued a card");
                    break;
                case "serve":
                    String patronName = input.split(" ")[1];
                    serve(patronName);
                    println("Currently serving " + patronName);
                    break;
                case "checkIn":
                    int[] arr = new int[0];
                    ArrayList<Integer> arl = new ArrayList<>();
                    ArrayList<Book> arlBook;
                    while (kb.hasNextInt()) {

                        while (kb.hasNextInt()) {
                            arl.add(kb.nextInt());
                        }
                        arr = new int[arl.size()];
                        for (int i = 0; i < arl.size() - 1; i++) {
                            arr[i] = arl.get(i);
                        }
                    }
                    kb.nextLine();
                    arlBook = checkIn(arr);
                    println("");
                    break;
                case "search":
                    String part = input.split(" ")[1];
                    searched = search(part);
                    break;
                case "checkOut":
                    ArrayList<Book> checkedOut = checkOut();
                    if (checkedOut.equals(new ArrayList<>())) {
                        println("no books are to be checked out");
                    } else {
                        println("The books have been checked out");
                    }
                    break;
                case "close":
                    close();
                    break;
                case "quit":
                    quit();
                    break;

            }
        }
    }

    /**
     * println("");.
     *
     * @return println(" ");
     */
    public ArrayList<OverdueNotice> open() {
        open = true;
        this.searched = null;
        this.current = null;
        due.advance();
        ArrayList<OverdueNotice> ret = createOverdueNotices();
        if (ret == null || ret.equals(null)) {
            return new ArrayList<>();
        }
        return ret;


    }

    /**
     * println("");.
     *
     * @return println();
     */
    public ArrayList<OverdueNotice> createOverdueNotices() {
        ArrayList<OverdueNotice> ret = new ArrayList<OverdueNotice>();
        for (Patron p : hmp.values()) {
            if (!p.getBooks().isEmpty()) {
                System.out.print(p.getBooks().get(0).getDueDate());
                System.out.println(p);
                System.out.println(due.getDate());
                //testing
                OverdueNotice od = new OverdueNotice(p, due.getDate());
                if (od.getDue() <= due.getDate() - 1) {
                    ret.add(od);
                }
            }
        }
        return ret;
    }

    /**
     * println("");.
     *
     * @param name println("");
     * @return println(" ");
     */
    public Patron issueCard(String name) {
        try {
            if (name.equals("") || hmp.containsKey(name) || !this.open) {
                return null;
            }
            Patron p = new Patron(name, this);
            this.hmp.put(p.getName(), p);
            return p;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * println("");.
     *
     * @param name println("");
     * @return println(" ");
     */
    public Patron serve(String name) {
        if (!this.open) {
            return null;
        }
        if (name.equals("")) {
            return null;
        }
        if (hmp.containsKey(name)) {
            this.current = hmp.get(name);
            printCurrentsBooks();
            searched = current.getBooks();
            return this.current;
        }
        return null;
    }

    /**
     * System.out.println("");.
     */
    public void printCurrentsBooks() {
        int i = 1;
        for (Book bk : current.getBooks()) {
            println(i + ": " + bk.toString());
            i++;
        }
    }

    /**
     * println("");.
     *
     * @param bookNumbers println("");
     * @return println(" ");
     */
    public ArrayList<Book> checkIn(int... bookNumbers) {
        ArrayList<Book> ret = new ArrayList<>();
        if (open) {
            if (bookNumbers.length > 0) {
                if (!searched.isEmpty()) {
                    for (int i : bookNumbers) {
                        Book bk = searched.get(i - 1);
                        bk.checkIn();
                        catalog.add(bk);
                        ret.add(bk);
                    }
                } else {
                    println("There are no books checked out");
                    return null;
                }
            } else {
                println("there are no books to be checked in");
                return null;
            }
        } else {
            println("The library isnt open");
            return null;
        }
        return ret;
    }


    /**
     * println("");.
     *
     * @param bookNumbers println("");
     * @return println();
     */
    public ArrayList<Book> checkOutOld(int... bookNumbers) {
        if (this.current == null) {
            println("There isn't a person");
            return null;
        } else if (!open) {
            println("Its closed");
            return null;
        } else if (searched == null) {
            println("Searched is empty");
            return null;
        }


        ArrayList<Book> checkedOut = new ArrayList<>();
        // -------------------for loop----------------------------
        for (int i = 0; i < bookNumbers.length - 1; i++) {
            Book bk = searched.get(bookNumbers[i]);
            if (checkedOut.contains(bk)) {
                println("You already checked out that book");
                return null;
            }
            if (checkedOut.size() <= 3) {
                bk.checkOut(bk.getDueDate());
                checkedOut.add(bk);
                catalog.remove(bk);
            } else {
                println("You can't check out any more books");
                return checkedOut;
            }

        }
        // --------------------for loop----------------------
        return checkedOut;

    }

    /**
     * println("");.
     *
     * @param bookNumbers println("");
     * @return println();
     */
    public ArrayList<Book> checkOut(int... bookNumbers) {
        if (this.current == null) {
            System.out.println("There isn't a person");
            return null;
        } else if (!this.open) {
            System.out.println("Its closed");
            return null;
        } else if (searched == null) {
            System.out.println("Searched is empty");
            return null;
        }


        ArrayList<Book> checkedOut = new ArrayList<>();
        int books = 0;
        // -------------------for loop----------------------------
        for (int i = 0; i < bookNumbers.length - 1; i++) {
            Book bk = searched.get(i);
            if (checkedOut.contains(bk)) {
                System.out.println("You already checked out that book");
                return null;
            } else if (bk.isAvailable()) {
                if (books <= 3) {
                    bk.checkOut(bk.getDueDate());
                    checkedOut.add(bk);
                    books++;
                } else {
                    System.out.println("You can't check out any more books");
                    return checkedOut;
                }
            }
        }
        // --------------------for loop----------------------
        if (checkedOut.isEmpty()) {

            return null;
        }
        return checkedOut;

    }

    /**
     * println("");.
     *
     * @param part println("");
     * @return println();
     */
    public ArrayList<Book> searchOld(String part) {
        if (!this.open) {
            return null;
        }
        if (part == null) {
            return null;
        }
        ArrayList<Book> arl = new ArrayList<>();
        if (!catalog.isEmpty()) {
            for (int i = 0; i < catalog.size() - 1; i++) {
                Book bk = catalog.get(i);
                if (bk.getTitle().contains(part) || (bk.getAuthor().contains(part))) {
                    arl.add(bk);
                }
            }
        } else {
            println("No books in catalog.");
            return null;
        }
        searched = arl;
        return arl;
    }

    /**
     * System.out.println("");.
     * * search books System.out.println("");
     *
     * @param part System.out.println("");
     * @return System.out.println(" ");
     */
    public ArrayList<Book> search(String part) {
        searched = new ArrayList<>();
        String plc = part.toLowerCase();
        if (!this.open) {
            return null;
        }
        if (plc.length() > 3) {
            for (Book bk : catalog) {
                boolean booli = false;
                if (bk.getAuthor().toLowerCase().contains(plc)
                        || (bk.getTitle().toLowerCase().contains(plc))) {
                    for (Book book : searched) {
                        if ((book.getAuthor().equals(bk.getAuthor())) && book.getTitle().equals(bk.getTitle())) {
                            booli = true;
                        }
                    }
                    if (!booli) {
                        searched.add(bk);
                    }

                }
            }
        } else {
            println("You have to search at least 4 characters miss librarian");
        }
        if (searched.isEmpty()) {
            return null;
        }
        return searched;
    }

    /**
     * println("");.
     */
    public void close() {
        this.open = false;
        this.searched = null;
    }

    /**
     * println("");.
     */
    public void quit() {
        System.exit(0);
    }


    /**
     * println("");.
     */
    @Override
    public String toString() {
        return catalog.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

}
