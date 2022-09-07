import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class Library {
    private List<Book> books;
    private Map<Reader,List<Book>> registeredReaders;

    public Library(){
        this.books = new ArrayList<>();
        this.registeredReaders = new HashMap<>();
    }

    public void addBook(String name, String author){

        books.add(new Book(books.size()+1, name, author));

    }

    // TODO below method by using Stream API
    public Book getBookByID(int bookId){
        Book book = null;
        for(Book b : books){
            if(b.getId() == bookId){
                book = b;
            }
        }
        return book;
    }
    public void addReader(String name){

        registeredReaders.put(new Reader(registeredReaders.size()+1, name), new ArrayList<>());

    }

    public void printBookList(){
//        for(Book book : books){
//            System.out.println(book);
//        }
        books.forEach(System.out::println);
    }
    public void printReadersSet(){

//        for(Reader reader : registeredReaders.keySet()){
//            System.out.println(reader);
//        }
        registeredReaders.keySet().forEach(System.out::println);

    }

    public List<Book> getBooks(){
        return books;
    }
    public Set<Reader> getReaders(){
        return registeredReaders.keySet();
    }
    public Map<Reader, List<Book>> getReadersInfo(){
        return registeredReaders;
    }

    public void addBorrowedBook(int readerId, int bookId){
        for(Map.Entry<Reader, List<Book>> entry : registeredReaders.entrySet()){
            if(entry.getKey().getId() == readerId){
                entry.getValue().add(this.getBookByID(bookId));
                this.getBookByID(bookId).setBorrowed(true);
            }
        }
    }

    public boolean isBookInLibrary(int bookId){
        for(Book book : books){
            if(book.getId() == bookId){
                return true;
            }
        }
        return false;
    }
    public boolean isReaderRegistered(int readerId){
        for (Reader reader : this.getReaders()){
            if(reader.getId() == readerId){
                return true;
            }
        }
        return false;
    }

    public void returnBookByID(int bookId){
        if (this.getBookByID(bookId).getBorrowed()) {
            System.out.println("return");
            this.getBookByID(bookId).setBorrowed(false);

            for (Map.Entry<Reader, List<Book>> entry : this.getReadersInfo().entrySet()) {
                entry.getValue().removeIf(book->book.getId()==bookId);

            }
        }
    }

    public void printBookReader(int bookId) {
        if (this.getBookByID(bookId).getBorrowed()) {
            for (Map.Entry<Reader, List<Book>> entry : this.getReadersInfo().entrySet()) {
                for (Book book1 : entry.getValue()) {
                    if (book1.getId() == bookId) {
                        System.out.println(entry.getKey());
                    }
                }
            }
        }
    }


}
