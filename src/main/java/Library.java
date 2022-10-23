import java.util.*;


public class Library {
    private final List<Book> books;
    private final Map<Reader,List<Book>> registeredReaders;

    public Library(){
        this.books = new ArrayList<>();
        this.registeredReaders = new HashMap<>();
    }

    public void addBook(String bookNameAuthor){
        String[] bookData = bookNameAuthor.split("/");
        books.add(new Book(bookData[0].trim(), bookData[1].trim()));
    }

    public Book getBookByID(int bookId){
       return books.stream().filter(b -> b.getId()==bookId).findFirst().get();
    }
    public void addReader(String name){

        List<Reader> readers = registeredReaders.keySet().stream().filter(r -> r.getName().equals(name)).toList();

        if(!readers.isEmpty()){
            System.out.printf("Reader %s is already in list\n", name);
        } else {
            registeredReaders.put(new Reader(name), new ArrayList<>());
        }
    }

    public void printBookList(){
        books.forEach(System.out::println);
    }
    public void printReadersSet(){
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
                Book book = getBookByID(bookId);
                entry.getValue().add(book);
                book.setBorrowed(true);
            }
        }
    }

    public boolean isBookInLibrary(int bookId){
        return books.stream().anyMatch(book -> book.getId() == bookId);
    }
    public boolean isReaderRegistered(int readerId){
        return getReaders().stream().anyMatch(reader -> reader.getId() == readerId);
    }

    public void returnBookByID(int bookId){
        if (getBookByID(bookId).getBorrowed()) {
            System.out.println("return");
            getBookByID(bookId).setBorrowed(false);

            for (Map.Entry<Reader, List<Book>> entry : getReadersInfo().entrySet()) {
                entry.getValue().removeIf(book->book.getId()==bookId);
            }
        } else {
            System.out.println("This book is in library.");
        }
    }

    public void printBookReader(int bookId) {
        if (getBookByID(bookId).getBorrowed()) {
            for (Map.Entry<Reader, List<Book>> entry : getReadersInfo().entrySet()) {
                for (Book book1 : entry.getValue()) {
                    if (book1.getId() == bookId) {
                        System.out.println(entry.getKey());
                    }
                }
            }
        }
    }

}
