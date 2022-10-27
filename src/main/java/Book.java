import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    static final AtomicInteger bId = new AtomicInteger();
    private final int id;
    private final String name;
    private final String author;
    private boolean borrowed;

    public Book(String bookName, String author){
        this.name = bookName;
        this.author = author;
        this.borrowed = false;
        this.id = bId.incrementAndGet();
    }
    public int getId(){
        return id;
    }

    public String getBookName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
    public void setBorrowed(boolean borrow){
        this.borrowed = borrow;
    }
    public boolean getBorrowed(){
        return borrowed;
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

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof Book)){
            return false;
        }
        Book compared = (Book)o;

        return compared.getId()==this.getId() && compared.getBookName().equals(this.getBookName())
                && compared.getAuthor().equals(this.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,author);
    }

}
