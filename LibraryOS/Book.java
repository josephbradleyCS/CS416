/**
 * @author jkbra
 * @version 1.01
 */
public class Book {
    // documentation says you need these
    private String title;
    private String author;
    private int dueDate;

    // i used these too to make stuff easier and more complicated
    private boolean isAvailable;
    private int bookNum;

    /**
     *  System.out.println("");.
     * @param bookTitle System.out.println("");
     * @param author System.out.println("");
     */ // The constructor. Saves the provided information. When created, this book is not checked out.
    public Book(String bookTitle, String author) {
        this.title = bookTitle;
        this.author = author;
        this.isAvailable = true;
        this.dueDate = -1;
    }

    /**
     *
     * @return System.out.println("");
     */ // Returns the book's title
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return System.out.println("");
     */ // Returns the book's author
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return  System.out.println("");
     */ // Returns the date (as an integer) that this book is due
    public int getDueDate() {
        return dueDate;
    }

    /**
     *
     * @return System.out.println("");
     */ // Returns the status of the book (whether available or not)
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * System.out.println("");.
     * @param date System.out.println("");
     */ //Check whether the book is available. If it is, then check it out by setting the status of this
    // book to unavailable and the due date to the specified date. That's all. It is not this Book's
    // job to remove itself from the Library's collection of available books.
    public void checkOut(int date) {
        if (this.isAvailable()) {
            this.isAvailable = false;
            this.dueDate = date;
        }
    }

    /**
     * System.out.println("");.
     */ // Sets the status of this Book to available and the due date to -1. That's all. It is not this
    // Book's job to return itself to the Library's collection of available books.
    public void checkIn() {
        this.isAvailable = true;
        this.dueDate = -1;
    }

    /**
     *
     * @return System.out.println("");
     */ // Returns a string of the form title  by author.
    @Override
    public String toString() {
        return title + " by " + author;
    }

    /**
     *
     * @param dueDate System.out.println("");
     */
    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * System.out.println("").
     * @return System.out.println();
     */
    public int getBookNum() {
        return bookNum;
    }

    /**
     * System.out.println("").
     * @param bookNum System.out.println("").
     */
    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

}
