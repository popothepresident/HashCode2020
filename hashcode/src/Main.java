import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int signupCtr = 0;
    public static int numberOfDays;
    public static List<Library> sortedLibraries = new ArrayList<>();
    public static List<Library> signedUpLibraries = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("../c_incunabula.txt"));
        PrintStream printStream = new PrintStream(new File("../c_output.txt"));

        int numberOfBooks = scanner.nextInt();
        int numberOfLibraries = scanner.nextInt();
        numberOfDays = scanner.nextInt();
        List<Library> libraries = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < numberOfBooks; ++i) {
            books.add(new Book(i, scanner.nextInt()));
        }

        for (int i = 0; i < numberOfLibraries; ++i) {
            libraries.add(new Library(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), i));
            Library currentLibrary = libraries.get(i);
            for (int j = 0; j < currentLibrary.getNumberOfBooks(); ++j) {
                Book book = books.get(scanner.nextInt());
                currentLibrary.addBook(book);
            }
        }

        while((numberOfDays - signupCtr) > 0 && !libraries.isEmpty()) {
            Library biggestLibrary = LibrarySorter.getBiggestLibrary(libraries);
            sortedLibraries.add(biggestLibrary);
            libraries.remove(biggestLibrary);
            signedUpLibraries.add(biggestLibrary);
            signupCtr += biggestLibrary.getSignUpDays();
        }

        printStream.println(signedUpLibraries.size());
        for (Library library : signedUpLibraries) {
            printStream.println(library.getIndex() + " " + library.getNumberOfBooksScanned());
            List<Book> booksScanned = library.getBooksScanned();
            for (Book book : booksScanned) {
                printStream.print(book.getIndex() + " ");
            }
            printStream.println();
        }
    }
}
