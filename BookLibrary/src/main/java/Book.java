public class Book {
    private int id;
    private String bookName;
    private String author;
    private boolean borrowed;

    public Book(int id,String bookName, String author){
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.borrowed = false;
    }
    public int getId(){
        return this.id;
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
