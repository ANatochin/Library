import java.util.*;

public class Library {
    private List<Book> books;
    private Set<Reader> readers;
    private Map<Reader,List<Book>> registeredReaders;

    public Library(){
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
        this.registeredReaders = new HashMap<>();
    }

    public void addBook(String name, String author){
//        if (this.books.isEmpty()){
//            this.books.add(new Book(1, name, author));
//        } else{
//            this.books.add(new Book(this.books.size()+1, name, author));
//        }
        this.books.add(new Book(this.books.size()+1, name, author));
    }
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
//        if(this.readers.isEmpty()){
//            this.readers.add(new Reader(1, name));
//        } else{
//            this.readers.add(new Reader(this.readers.size()+1, name));
//        }
        this.registeredReaders.put(new Reader(this.registeredReaders.size()+1, name), new ArrayList<>());

    }

    public void printBookList(){
        for(Book book : this.books){
            System.out.println(book);
        }
    }
    public void printReadersSet(){
//        for(Reader reader : this.readers){
//            System.out.println(reader);
//        }
        for(Reader reader : registeredReaders.keySet()){
            System.out.println(reader);
        }
        System.out.println(this.registeredReaders);
    }

    public List<Book> getBooks(){
        return this.books;
    }
    public Set<Reader> getReaders(){
//        return this.readers;
        return this.registeredReaders.keySet();
    }
    public Map<Reader, List<Book>> getReadersInfo(){
        return this.registeredReaders;
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
        boolean result = false;
        for(Book book : books){
            if(book.getId() == bookId){
                result = true;
            }
        }
        return result;
    }
    public boolean isReaderRegistered(int readerId){
        boolean result = false;
        for (Reader reader : this.getReaders()){
            if(reader.getId() == readerId){
                result = true;
            }
        }
        return result;
    }


}
