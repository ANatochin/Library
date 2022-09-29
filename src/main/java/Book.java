import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    static final AtomicInteger bId = new AtomicInteger();
    private final int bookId;
    private final String bookName;
    private final String author;
    private boolean borrowed;

    public Book(String bookName, String author){
        this.bookName = bookName;
        this.author = author;
        this.borrowed = false;
        this. bookId = bId.incrementAndGet();
    }
    public int getId(){
        return this.bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }
    public void setBorrowed(boolean borrow){
        this.borrowed = borrow;
    }
    public boolean getBorrowed(){
        return this.borrowed;
    }

    @Override
    public String toString(){
        String book = "";
        if(getBorrowed()){
            book += this.getBookName() + ", author: " + this.getAuthor() + " /Book.ID-" + this.getId()+"/" + " !borrowed!";
        } else {
            book = this.getBookName() + ", author: " + this.getAuthor() + " /Book.ID-" + this.getId()+"/";
        }
        return book;
    }
}
