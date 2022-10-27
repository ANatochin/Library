import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LibraryHandler libraryHandler = new LibraryHandler(new Library());
        libraryHandler.execute();
    }
}
