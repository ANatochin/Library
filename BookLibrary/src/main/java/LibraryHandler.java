import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class LibraryHandler {
    private Library library;

    public LibraryHandler (Library library){
        this.library = library;
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);

        this.libraryMenu();

        while(true){
            System.out.print("\nOption or EXIT :");
            String input = scanner.nextLine();

            if(input.equals("1")){
                if(!library.getBooks().isEmpty()){
                    library.printBookList();
                } else {
                    System.out.println("No books in library pls add by choosing option [4]");
                }

            }else if(input.equals("2")){

                if(!library.getReaders().isEmpty()){
                    library.printReadersSet();
                } else {
                    System.out.println("No readers registered pls add by choosing option [3]");
                }

            }else if(input.equals("3")){

                System.out.print("Please enter new reader full name: ");
                String readerName = scanner.nextLine();
                this.library.addReader(readerName);

            }else if(input.equals("4")){

                System.out.print("Please enter new book name and author, separated by \"/\"");
                String book = scanner.nextLine();
                String[] bookData = book.split("/");

                this.library.addBook(bookData[0].trim(),bookData[1].trim());

            } else if(input.equals("5")){

                System.out.print("Enter reader ID and book ID, separated by \"/\"");
                String inputtedIDs = scanner.nextLine();
                String[] ids = inputtedIDs.split("/");
                int readerId = Integer.parseInt(ids[0].trim());
                int bookId = Integer.parseInt(ids[1].trim());

                if(!this.library.getReaders().isEmpty()){

                    if(!this.library.isReaderRegistered(readerId)){
                        System.out.println("No reader with this ID, add new reader by choosing option [3]");
                    } else if(!this.library.isBookInLibrary(bookId)){
                        System.out.println("Such book is not available in library, to recheck use option [1]");
                    } else if(this.library.getBookByID(bookId).getBorrowed()){
                        System.out.println("This book is borrowed");
                    }
                    else {
                        this.library.addBorrowedBook(readerId,bookId);
                    }

                }else{
                    System.out.println("No readers registered in library pls add by choosing option [3]");
                }

            }else if(input.equals("6")){

                System.out.print("Input book ID: ");
                int bookId = scanner.nextInt();
                if(this.library.getBookByID(bookId).getBorrowed()){
                    System.out.println("return");
                    this.library.getBookByID(bookId).setBorrowed(false);

                    for(Map.Entry<Reader,List<Book>> entry : this.library.getReadersInfo().entrySet()){
                        for (int i = 0; i < entry.getValue().size(); i++) {
                            if(entry.getValue().get(i).getId() == bookId){
                                entry.getValue().remove(i);
                            }
                        }
                    }
                }

            }else if(input.equals("7")){

                System.out.print("Input reader ID: ");
                int readerId = scanner.nextInt();
                for(Map.Entry<Reader, List<Book>> entry : this.library.getReadersInfo().entrySet()){
                    if(entry.getKey().getId() == readerId){
                        System.out.println(entry.getKey() + " :\n" + entry.getValue());
                    }
                }

            }else if(input.equals("8")){

                System.out.print("Input book ID: ");
                int bookId = scanner.nextInt();
                if(this.library.getBookByID(bookId).getBorrowed()){
                    for(Map.Entry<Reader, List<Book>> entry : this.library.getReadersInfo().entrySet()){
                        for(Book book : entry.getValue()){
                            if(book.getId() == bookId){
                                System.out.println(entry.getKey());
                            }
                        }
                    }
                }
            }else if(input.equalsIgnoreCase("exit")){
                break;
            }
        }
    }

    public void libraryMenu(){

        System.out.print("\nWelcome to the Library!\n" +
                "\nPlease, select one of the following actions by typing the option's number and pressing ENTER key\n"+
                "\t[1] Show all books in the library\n"+
                "\t[2] Show all readers registered in the library\n"+
                "\t[3] Register new reader\n"+
                "\t[4] Add new book\n"+
                "\t[5] Borrow a book to reader\n"+
                "\t[6] Return a book to the library\n"+
                "\t[7] Show all borrowed books by user ID\n"+
                "\t[8] Show current reader of a book with ID\n"+
                "\nType \"EXIT\" to stop the program and exit!\n");

    }
}
