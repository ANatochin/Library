import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class LibraryHandler {
    private Library library;

    public LibraryHandler(Library library) {
        this.library = library;
    }

    public void execute() {
        this.libraryMenu();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nOption or EXIT :");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            switch (input) {

                case "1":
                    printLibBookList();
                    break;

                case "2":
                    printReadersList();
                    break;

                case "3":
                    addReader(scanner);
                    break;

                case "4":
                    addBook(scanner);
                    break;

                case "5":
                    borrowBook(scanner);
                    break;

                case "6":
                    returnBook(scanner);
                    break;

                case "7":
                    printBorrowedByBookId(scanner);
                    break;

                case "8":
                    printCurrentBookReader(scanner);
                    break;
            }
        }
    }


        public void libraryMenu () {

            System.out.print("\nWelcome to the Library!\n" +
                    "\nPlease, select one of the following actions by typing the option's number and pressing ENTER key\n" +
                    "\t[1] Show all books in the library\n" +
                    "\t[2] Show all readers registered in the library\n" +
                    "\t[3] Register new reader\n" +
                    "\t[4] Add new book\n" +
                    "\t[5] Borrow a book to reader\n" +
                    "\t[6] Return a book to the library\n" +
                    "\t[7] Show all borrowed books by user ID\n" +
                    "\t[8] Show current reader of a book with ID\n" +
                    "\nType \"EXIT\" to stop the program and exit!\n");

        }

        protected void printLibBookList(){
            if (!library.getBooks().isEmpty()) {
                library.printBookList();
            } else {
                System.out.println("No books in library pls add by choosing option [4]");
            }
        }

        protected void printReadersList(){
            if (!library.getReaders().isEmpty()) {
                library.printReadersSet();
            } else {
                System.out.println("No readers registered pls add by choosing option [3]");
            }
        }

        protected void addReader(Scanner scanner){
            System.out.print("Please enter new reader full name: ");
            String readerName = scanner.nextLine();
            this.library.addReader(readerName);
        }

        protected void addBook(Scanner scanner){
            System.out.print("Please enter new book name and author, separated by \"/\"");
            String book = scanner.nextLine();
            String[] bookData = book.split("/");

            this.library.addBook(bookData[0].trim(), bookData[1].trim());
        }

        protected void borrowBook(Scanner scanner){
            System.out.print("Enter reader ID and book ID, separated by \"/\"");
            String inputtedIDs = scanner.nextLine();
            String[] ids = inputtedIDs.split("/");
            int readerId = Integer.parseInt(ids[0].trim());
            int bookId = Integer.parseInt(ids[1].trim());

            if (!this.library.getReaders().isEmpty()) {

                if (!this.library.isReaderRegistered(readerId)) {
                    System.out.println("No reader with this ID, add new reader by choosing option [3]");
                } else if (!this.library.isBookInLibrary(bookId)) {
                    System.out.println("Such book is not available in library, to recheck use option [1]");
                } else if (this.library.getBookByID(bookId).getBorrowed()) {
                    System.out.println("This book is borrowed");
                } else {
                    this.library.addBorrowedBook(readerId, bookId);
                }
            }
        }

        protected void returnBook(Scanner scanner){
            System.out.print("Input book ID: ");
            int bookId = scanner.nextInt();

            if(library.isBookInLibrary(bookId)){
                library.returnBookByID(bookId);
            }else{
                System.out.println("Such book ID is not registered");
            }


        }

        protected void printBorrowedByBookId(Scanner scanner){
            System.out.print("Input reader ID: ");
            int readerId = scanner.nextInt();
            if(library.isBookInLibrary(readerId)){
                for (Map.Entry<Reader, List<Book>> entry : this.library.getReadersInfo().entrySet()) {
                    if (entry.getKey().getId() == readerId) {
                        System.out.println(entry.getKey() + " :\n" + entry.getValue());
                    }
                }
            } else {
                System.out.println("No reader with this ID, add new reader by choosing option [3]");
            }
        }

        protected void printCurrentBookReader(Scanner scanner){
            System.out.print("Input book ID: ");
            int bookId = scanner.nextInt();

            if(library.isBookInLibrary(bookId)){
                library.printBookReader(bookId);
            }else{
                System.out.println("Such book is not available in library, to recheck use option [1]");
            }

        }

}
